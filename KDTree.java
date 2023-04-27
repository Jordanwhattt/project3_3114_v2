package prj3;

import java.util.Arrays;
import java.util.Comparator;

public class KDTree {

    
    Node root;
    int count;
    
    public KDTree() {
        root = null;
        count = 0;
    }
    

    public Node buildTree(Point[] point, int depth) {
        // Empty Tree, return the new node
        // The node we are currently looking at does not have any children.
        int l = point.length;
        if (l == 1) { 
            Node v = new Node(point[0], depth + 1); //Createa leaf node storing this point and make it 
            v.setPy(point);
            return v;
        }
        // Initializing variables
        int axis = depth%3; // determines which axis we sort points by

        Point median;
        
        Point[] x_left;
        Point[] x_right;
        Point[] y_left;
        Point[] y_right;
        Point[] z_left;
        Point[] z_right;
        Point[] p_x = new Point[l];
        Point[] p_y = new Point[l];
        Point[] p_z = new Point[l];
        Node v = null;
        int median_index = l / 2;

        
        mergeSort(point, l, axis);
        
        x_left = Arrays.copyOfRange(point, 0, median_index);
        x_right = Arrays.copyOfRange(point, median_index, l);
        

        y_left = Arrays.copyOfRange(point, 0, median_index);
        y_right = Arrays.copyOfRange(point, median_index, l);
        
        
        z_left = Arrays.copyOfRange(point, 0, median_index);
        z_right = Arrays.copyOfRange(point, median_index, l);
            

        
        // Sorting by x-axis
        if(axis % 3 == 0) {

            for(int i = 0; i < l;i++) {
                p_x[i] = point[i];
                p_y[i] = point[i];
                p_z[i] = point[i];
            }
            
            mergeSort(p_x, l, axis); //Sort Point Array p_x based on x axis
            mergeSort(p_y, l, axis + 1); //Sort Point Array p_y by y axis
            mergeSort(p_z, l, axis + 2); //Sort Point Array p_y by y axis
            
            median = getMedian(p_x); // Gets median of x-coords
            
            int left_ind = 0;
            int right_ind = 0;
            
            for(int i = 0; i < l; i++) {
                // If x-coord of point is less than or equal to median,
                // add it to the left array.
                if(p_x[i].getX() <= median.getX()) {
                    x_left[left_ind] = p_x[i];
                    y_left[left_ind] = p_y[i];
                    z_left[left_ind] = p_z[i];
                    left_ind++;
                }
                // If x-coord of point is greater than median,
                // add it to the right array.
                else {
                    x_right[right_ind] = p_x[i];
                    y_right[right_ind] = p_y[i];
                    z_right[right_ind] = p_z[i];
                    right_ind++;
                }
            }
            v = new Node(p_x[median_index - 1], depth);
            v.setPx(p_x);
            v.setPy(p_y);
            v.setPy(p_z);
        }
        
        // Sorting by y-axis
        else if(axis % 3 == 1) {
            p_y = new Point[l];
            p_z = new Point[l];
            for(int i = 0; i <l ;i++) {
                p_y[i] = point[i];
                p_z[i] = point[i];
            }
            
            mergeSort(p_y, l, axis); // Sorts array of points using the merge sort
            mergeSort(p_z, l, axis + 1);
            median = getMedian(p_y); // Gets median of y-coords
            
            int left_ind = 0;
            int right_ind = 0;
            
            for(int i = 0; i < l; i++) {
                // If y-coord of point is less than or equal to median,
                // add it to the left array.
                if(p_y[i].getY() <= median.getY()) {
                    x_left[left_ind] = p_y[i];
                    y_left[left_ind] = p_z[i];
                    left_ind++;
                }
                // If y-coord of point is greater than median,
                // add it to the right array.
                else {
                    x_right[right_ind] = p_y[i];
                    y_right[right_ind] = p_z[i];
                    right_ind++;
                }
            }
            v = new Node(p_y[median_index - 1], depth);
            v.setPx(p_y);
            v.setPy(p_z);
        }
        
        // Sorting by z-axis
        else if(axis % 3 == 2) {
            p_z = new Point[l];
            p_x = new Point[l];
            for(int i = 0; i < l;i++) {
                p_z[i] = point[i];
                p_x[i] = point[i];
            }
            
            
            mergeSort(p_z, l, axis);
            mergeSort(p_x, l, axis + 1); //Sorts array of points using the merge sort
            median = getMedian(p_z); //Gets median of z-coords
            
            int left_ind = 0;
            int right_ind = 0;
            
            for(int i = 0; i < l; i++) {
                // If z-coord of point is less than or equal to median,
                // add it to the left array.
                if(p_z[i].getZ() <= median.getZ()) {
                    x_left[left_ind] = p_z[i];
                    y_left[left_ind] = p_x[i];
                    left_ind++;
                }
                // If z-coord of point is greater than median,
                // add it to the right array.
                else {
                    x_left[right_ind] = p_z[i];
                    y_left[right_ind] = p_x[i];
                    right_ind++;
                }
            }
            v = new Node(point[median_index - 1], depth);
            v.setPx(p_z);
            v.setPy(p_x);
        }
        else {
            return null;
        }
        
        
        
        v.depth++; //add depth to the node
        v.left = buildTree(x_left, depth + 1); // left child of root
        v.right = buildTree(x_right, depth + 1);  // right child of root
        
        return v;
    }
    
    /**
     * Function that finds the median value in an array
     * 
     * @return median
     */
    private Point getMedian(Point[] coords_arr) {
        int n = coords_arr.length;
        return coords_arr[(n / 2) - 1]; 
    }
    
    /**
     * Recursive function that splits array into sub-arrays to sort array
     * 
     * @return
     */
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
    
    
    
    public int rangeCount(Prism R, Node v, Prism cell) {
        if(v == null) {
            return 0;
        }
        else if(R.isDisjoint(cell) {
            return 0;
        }
        else if(R.contains(cell) {
            return v.py.length;
        }
        else {
            int count = 0;
            if (R.contains(v.point) && v.isLeaf()) // consider this point
                count += 1;
            count += rangeCount(R, v.left, cell.leftPart(v.depth, v.point));
            count += rangeCount(R, v.right, cell.rightPart(v.depth, v.point));
            return count;
        }
        
        
    }

    
    public void reportSubtree(Node v, Prism range) {
        if(v.isLeaf()) {
            
        } else {
            reportSubtree(v.left, range);
            reportSubtree(v.right, range);
        }
    }

    
}