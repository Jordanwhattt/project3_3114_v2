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
            Node v = new Node(point[0]); //Createa leaf node storing this point and make it 
            v.setPy(point);
            return v;
        }
        // Initializing variables
        int axis = depth%3; // determines which axis we sort points by

        Point median;
        
        Point[] left;
        Point[] right;
        Point[] x_coord = null;
        Point[] y_coord = null;
        Point[] z_coord = null;
        Node v = null;
        int median_index = l / 2;
        //If this tree is not correct, this is what is wrong with it. Choosing the median correctly.
        
        left = Arrays.copyOfRange(point, 0, median_index);
        right = Arrays.copyOfRange(point, median_index, l);
        
            

        
        // Sorting by x-axis
        if(axis == 0) {
            x_coord = new Point[l];
            for(int i = 0; i < x_coord.length;i++) {
                x_coord[i] = point[i];
            }
            
           
            //mergeSort(x_coord, l); // Sorts array of points using the merge sort
            mergeSort(x_coord, l, axis);
            median = getMedian(x_coord); // Gets median of x-coords
            
            int left_ind = 0;
            int right_ind = 0;
            
            for(int i = 0; i < l; i++) {
                // If x-coord of point is less than or equal to median,
                // add it to the left array.
                if(point[i].getX() <= median.getX()) {
                    left[left_ind] = point[i];
                    left_ind++;
                }
                // If x-coord of point is greater than median,
                // add it to the right array.
                else {
                    right[right_ind] = point[i];
                    right_ind++;
                }
            }
            v = new Node(point[median_index], depth);
            v.setPy(x_coord);
        }
        
        // Sorting by y-axis
        else if(axis == 1) {
            y_coord = new Point[l];
            for(int i = 0; i < y_coord.length;i++) {
                y_coord[i] = point[i];
            }
            
            mergeSort(y_coord, l, axis); // Sorts array of points using the merge sort
            median = getMedian(y_coord); // Gets median of y-coords
            
            int left_ind = 0;
            int right_ind = 0;
            
            for(int i = 0; i < l; i++) {
                // If y-coord of point is less than or equal to median,
                // add it to the left array.
                if(point[i].getY() <= median.getY()) {
                    left[left_ind] = point[i];
                    left_ind++;
                }
                // If y-coord of point is greater than median,
                // add it to the right array.
                else {
                    right[right_ind] = point[i];
                    right_ind++;
                }
            }
            v = new Node(point[median_index], depth);
            v.setPy(y_coord);
        }
        
        // Sorting by z-axis
        else if(axis == 2) {
            z_coord = new Point[l];
            for(int i = 0; i < z_coord.length;i++) {
                z_coord[i] = point[i];
            }
            
            
            
            mergeSort(z_coord, l, axis); //Sorts array of points using the merge sort
            median = getMedian(z_coord); //Gets median of z-coords
            
            int left_ind = 0;
            int right_ind = 0;
            
            for(int i = 0; i < l; i++) {
                // If z-coord of point is less than or equal to median,
                // add it to the left array.
                if(point[i].getZ() <= median.getZ()) {
                    left[left_ind] = point[i];
                    left_ind++;
                }
                // If z-coord of point is greater than median,
                // add it to the right array.
                else {
                    right[right_ind] = point[i];
                    right_ind++;
                }
            }
            v = new Node(point[median_index], depth);
            v.setPy(z_coord);
        }
        
        
        
        v.depth++; //add depth to the node
        v.left = buildTree(left, depth + 1); // left child of root
        v.right = buildTree(right, depth + 1);  // right child of root
        
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
        else if(R.isDisjoint(cell, v.depth)) {
            return 0;
        }
        else if(R.contains(cell, v.depth)) {
            return v.py.length;
        }
        else {
            int count = 0;
            if (R.contains(v.point, v.depth) & v.isLeaf()) // consider this point
                count += 1;
            count += rangeCount(R, v.left, cell.leftPart(v.depth - 1, v.point));
            count += rangeCount(R, v.right, cell.rightPart(v.depth - 1, v.point));
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