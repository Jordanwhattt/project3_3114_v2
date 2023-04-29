package prj3;

import java.util.Arrays;

public class BST {
    
    public Node root;
    public int count;
    
    //1D Range Tree
    public BST() {
        root = null;
        this.count = 0;
    }
    

    /**
     * 
     * @param points
     * @param type: x = 0, y = 1, z = 2
     * @return
     */
    public Node buildTree(Point[] points) {
        int n = points.length;
        
        //If P contains one Point
        if(n == 1) {
            Node v = new Node(points[0]); //Createa leaf node storing this point and make it 
            return v;
        }

        int l_length = 0;
        int mid_i =0;
        if(n % 2 == 0) {
            l_length = n / 2;
            mid_i = l_length - 1;
        }
        else {
            mid_i = n/2;
            l_length = mid_i + 1;
        }

        
        Point[] z_left = new Point[l_length];
        Point[] z_right = new Point[n - l_length];

        for(int i = 0; i < l_length; i++) {
            z_left[i] = points[i];
        }
        for(int i =0; i < n-l_length;i++) {
            z_right[i] = points[i + l_length];
        }

        Node v = new Node(points[mid_i]);
        //v.setPy(points);

        //5. build v_left
        Node v_left = this.buildTree(z_left);
        //6. build v_right
        Node v_right = this.buildTree(z_right);
        
        //Make v_left the left child of v
        v.left = v_left;
        //Make v_right the right child of v
        v.right = v_right;    
        return v;
        
    }
    
    
    /**
     * query does not work yet. This should also be in 
     * @param min
     * @param max
     * @return
     */
    public int RangeQuery1D(int min, int max) {
        
        Node v_split = findSplitNode(this, min, max);
        int split_value;
        int v_value;
        if(v_split == null) {
            return 0;
        }
        else if(v_split.isLeaf()) { //Base Case 1
            
            split_value = returnSplitValue(v_split);
            
            if(split_value >= min && split_value <= max) {
                count++; //Report point
            }
            
        } else { //Case 2: Not a leaf
            Node v = v_split.left;
            v_value = returnSplitValue(v);
            
            while(!v.isLeaf()) {
                v_value = returnSplitValue(v);
                if(min <= v_value) {
                    reportSubtree(v.right, min, max);
                    v = v.left;
                }
                else {
                    v = v.right;
                }
            }
            
            if(v_value >= min & v_value <= max) {
                count++; //Report point
            }
            
            
            v = v_split.right;
            while(!v.isLeaf()) {
                split_value = returnSplitValue(v);
                
                if(max >= split_value) {
                    reportSubtree(v.left, min, max);
                    v = v.right;
                } 
                else {
                    v = v.left;
                }
            }
            if(v_value >= min & v_value <= max) {
                count++; //Report point
            }
        }
        
        return count;
    
    }
    
    
    private int returnSplitValue(Node v_split) {
       
        return  v_split.point.z;
        
    }


    public Node findSplitNode(BST tree, int min, int max) {
        Node v = this.root;
        while(v != null && !v.isLeaf() && (max <= v.point.z | min >= v.point.z)) {
            if(max <= v.point.getZ()) {
                v = v.left;
            } else {
                v = v.right;
            }
        }
        return v;
    }
    

    
    
    public void reportSubtree(Node v, int min, int max) {
        if(v.isLeaf()) {
            if(v.point.getZ() >= min && v.point.getZ() <= max) {
                count++; //Report point
            }
        } else {
            reportSubtree(v.left, min, max);
            reportSubtree(v.right, min, max);
        }
    }
}