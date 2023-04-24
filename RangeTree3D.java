package prj3;

import java.util.Arrays;

public class RangeTree3D {
    
    
    public Node root;
    public int type;
    
    public RangeTree3D() {
        root = null;
        this.type = 0;
    }
    
    
    public RangeTree3D(int type) {
        root = null;
        this.type = type;
    }
    
    
    public Node buildTree(Point[] points_x, Point[] points_y, Point[] points_z) {
        int n = points_x.length;
        
        
        

        // build a 2d Range Tree for y and z
        
        RangeTree2D tree_2d = new RangeTree2D(1);
        tree_2d.root = tree_2d.buildTree(points_y, points_z);
        
        //2. if P contains only one point
        if(n == 1) {
            Node v = new Node(points_x[0], tree_2d); //Creates leaf node storing this point and make it 
            Point[] pyArray = new Point[1];
            Point[] pzArray = new Point[1];
            pyArray[0] = points_x[0];
            pzArray[0] = points_x[0];
            v.setPy(pyArray);
            v.setPz(pzArray);
            return v;
        }
        
        Point[] px_left;
        Point[] px_right;
        Point[] py_left;
        Point[] py_right;
        Point[] pz_left;
        Point[] pz_right;
         
        
    
        // Find median
        int median_index = (n/2);


        // Split P into P_left and P_right.
        
       
        px_left = Arrays.copyOfRange(points_x, 0, median_index);
        px_right = Arrays.copyOfRange(points_x, median_index, n);
        
        

        py_left = new Point[px_left.length];
        py_right = new Point[px_right.length];
        pz_left = new Point[px_left.length];
        pz_right = new Point[px_right.length];
            

        int left_index1 = 0, right_index1 = 0, right_index2 = 0, left_index2 = 0;
        // Then subsets <= and > the median x-coordinate x_mid
        for (int i = 0; i < n; i++) {
            int py_value;
            int pz_value;
            int median_val;
            
            Point py = points_y[i];
            Point pz = points_z[i];
            
            if(this.type == 0) {
                py_value = py.getX();
                pz_value = pz.getX();
                median_val = points_x[median_index-1].getX();
            } else if(this.type == 1) {
                py_value = py.getY();
                pz_value = pz.getY();
                median_val = points_x[median_index-1].getY();
            } else if(this.type == 2){
                py_value = py.getZ();
                pz_value = pz.getZ();
                median_val = points_x[median_index-1].getZ();
            } else {
                return null; //type must be 0, 1, or 2.
            }
            
            
            //Split
            if (py_value <= median_val) {
                py_left[left_index1] = py;
                left_index1++;
            }
            else {
                py_right[right_index1] = py;
                right_index1++;
            }
            
            if (pz_value <= median_val) {
                pz_left[left_index2] = pz;
                left_index2++;
            }
            else {
                pz_right[right_index2] = pz;
                right_index2++;
            }
        }
        


        
        Node v = new Node(points_x[median_index-1], tree_2d);
        v.setPy(points_y);
        v.setPy(points_z);

        //5. build v_left
        Node v_left = this.buildTree(px_left, py_left, pz_left);
        //6. build v_right
        Node v_right = this.buildTree(px_right, py_right, pz_right);
        
        //Make v_left the left child of v
        v.left = v_left;
        //Make v_right the right child of v
        v.right = v_right;    
        return v;
        
    }

}
