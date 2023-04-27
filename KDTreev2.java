package prj3;

import java.util.Arrays;
import java.util.Comparator;


public class KDTreev2 {
    
    Node root;

    public KDTreev2(Point[] points) {
        
        int l = points.length;
        Point[] px = new Point[l];
        Point[] py = new Point[l];
        Point[] pz = new Point[l];
        
        for(int i = 0; i < l; i++) {
            px[i] = points[i];
            py[i] = points[i];
            pz[i] = points[i];
        }
        
        Arrays.sort(px, (a, b) -> a.x - b.x);
        Arrays.sort(py, (a, b) -> a.y - b.y);
        Arrays.sort(pz, (a, b) -> a.z - b.z);

        this.root = buildTree(px, py, pz, 0);
        
    }
    
    
    
    
    
    public Node buildTree(Point[] px, Point[] py, Point[] pz, int depth) {
        
        int n = px.length;
        int axis = depth % 3;
        
        if(n == 0) {
            return null;
        }
        if(n == 1) {
            if(axis == 0 ) {
                return new Node(new Point(px[0].x, px[0].y, px[0].z));
            }
            else if(axis == 1 ) {
                return new Node(new Point(py[0].x, py[0].y, py[0].z));
            }
            else {
                return new Node(new Point(pz[0].x, pz[0].y, pz[0].z));
            }
            
        }
        
        int mid = n/2;

        Point[] x_left = Arrays.copyOfRange(px, 0, mid);
        Point[] x_right = Arrays.copyOfRange(px, mid+1, n);
        
        Point[] y_left = Arrays.copyOfRange(py, 0, mid);
        Point[] y_right = Arrays.copyOfRange(py, mid+1, n);
        
        Point[] z_left = Arrays.copyOfRange(pz, 0, mid);
        Point[] z_right = Arrays.copyOfRange(pz, mid+1, n);

        //sort arrays
        mergeSort(px, n, 0);
        mergeSort(py, n, 1);
        mergeSort(pz, n, 2);
        
        if(axis == 0) {

            Node v = new Node(new Point(px[mid].x, px[mid].y, px[mid].z));
          
            int yleft_index = 0;
            int zleft_index = 0;
            int yright_index = 0;
            int zright_index = 0;
            
            for(int i = 0; i < px.length; i++) {
                
//                if(v.point.x > px[i].x) {
//                    x_left[xleft_index] = px[i];
//                    xleft_index++;
//                } else {
//                    x_right[xright_index] = px[i];
//                    xright_index++;
//                }
           
                if(v.point.x > py[i].x) {
                    y_left[yleft_index] = py[i];
                    yleft_index++;
                    
                } else if (v.point.x < py[i].x)  {
                    y_right[yright_index] = py[i];
                    yright_index++;
                }
                
                if(v.point.x > pz[i].x) {
                    z_left[zleft_index] = pz[i];
                    zleft_index++;
                } else if (v.point.x < pz[i].x){
                    z_right[zright_index] = pz[i];
                    zright_index++;
                }
                    
            }
            mergeSort(x_left, x_left.length, 0);
            mergeSort(x_right, x_right.length, 0);
            mergeSort(y_left, y_left.length, 1);
            mergeSort(y_right, y_right.length, 1);            
            mergeSort(z_left, z_left.length, 2);
            mergeSort(z_right, z_right.length, 2);
            
            v.left = buildTree(x_left, y_left, z_left, depth + 1);
            v.right = buildTree(x_right, y_right, z_right, depth + 1);
  
            
            return v;
        }
        else if(axis == 1) {

            Node v = new Node(new Point(py[mid].x, py[mid].y, py[mid].z));
            
            int xleft_index = 0; 
            int zleft_index = 0;
            int xright_index = 0;
            int zright_index = 0;
            
            for(int i = 0; i < py.length; i++) {

                if(v.point.y > px[i].y) {
                    x_left[xleft_index] = px[i];
                    xleft_index++;
                } else if(v.point.y < px[i].y) {
                    x_right[xright_index] = px[i];
                    xright_index++;
                }
               
                
//                if(v.point.y > py[i].y) {
//                    y_left[yleft_index] = py[i];
//                    yleft_index++;
//                } else {
//                    y_right[yright_index] = py[i];
//                    yright_index++;
//                }
                
                if(v.point.y > pz[i].y) {
                    z_left[zleft_index] = pz[i];
                    zleft_index++;
                } else if(v.point.y < pz[i].y){
                    z_right[zright_index] = pz[i];
                    zright_index++;
                }
            }
            mergeSort(x_left, x_left.length, 0);
            mergeSort(x_right, x_right.length, 0);
            mergeSort(y_left, y_left.length, 1);
            mergeSort(y_right, y_right.length, 1);            
            mergeSort(z_left, z_left.length, 2);
            mergeSort(z_right, z_right.length, 2);
            v.left = buildTree(x_left, y_left, z_left, depth + 1);
            v.right = buildTree(x_right, y_right, z_right, depth + 1);
            return v;
        }
        else {
            
            Node v = new Node(new Point(pz[mid].x, pz[mid].y, pz[mid].z));
            
            int xleft_index = 0; 
            int yleft_index = 0;
            int xright_index = 0;
            int yright_index = 0;
            
            for(int i = 0; i < pz.length; i++) {

                if(v.point.z > px[i].z) {
                    x_left[xleft_index] = px[i];
                    xleft_index++;
                } else if(v.point.z > px[i].z){
                    x_right[xright_index] = px[i];
                    xright_index++;
                }
                
                if(v.point.z > py[i].z) {
                    y_left[yleft_index] = py[i];
                    yleft_index++;
                } else if(v.point.z < py[i].z) {
                    y_right[yright_index] = py[i];
                    yright_index++;
                }
                
//                if(v.point.z > pz[i].z) {
//                    z_left[zleft_index] = pz[i];
//                    zleft_index++;
//                } else {
//                    z_right[zright_index] = pz[i];
//                    zright_index++;
//                }
            }
            mergeSort(x_left, x_left.length, 0);
            mergeSort(x_right, x_right.length, 0);
            mergeSort(y_left, y_left.length, 1);
            mergeSort(y_right, y_right.length, 1);            
            mergeSort(z_left, z_left.length, 2);
            mergeSort(z_right, z_right.length, 2);
            
            v.left = buildTree(x_left, y_left, z_left, depth + 1);
            v.right = buildTree(x_right, y_right, z_right, depth + 1);
            return v;
        }
        
        
        
    
    }
    
    
    
    public static void mergeSort(Point[] a, int n, int dimension) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        Point[] l = new Point[mid];
        Point[] r = new Point[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid, dimension);
        mergeSort(r, n - mid, dimension);

        merge(a, l, r, mid, n - mid, dimension);
    }
    
    /**
     * Merges the sub-arrays in a sorted manner
     * 
     * @return
     */
    public static void merge(
        Point[] a, Point[] l, Point[] r, int left, int right, int dimension) {
       
          int i = 0, j = 0, k = 0;
          while (i < left && j < right) {
              int l_val;
              int r_val;
              
              
              if(dimension % 3 == 0 ) {
                  l_val = l[i].getX();
                  r_val = r[j].getX();
              } else if(dimension % 3 == 1) {
                  l_val = l[i].getY();
                  r_val = r[j].getY();
              } else {
                  l_val = l[i].getZ();
                  r_val = r[j].getZ();
              }
              
              
              if (l_val <= r_val) {
                  a[k++] = l[i++];
              }
              else {
                  a[k++] = r[j++];
              }
          }
          while (i < left) {
              a[k++] = l[i++];
          }
          while (j < right) {
              a[k++] = r[j++];
          }
      }
    
    /**
     * Gets the median of the array
     * @param coords_arr
     * @return
     */
    private Point getMedian(Point[] coords_arr) {
        int n = coords_arr.length;
        return coords_arr[(n / 2) - 1]; 
    }
}
