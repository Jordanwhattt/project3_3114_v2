package prj3;

import java.util.Arrays;

public class BST {
    
    public Node root;
    
    //1D Range Tree
    public BST() {
        root = null;
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
        int count = 0;
        Node v_split = this.root;
        v_split = findSplitNode(v_split, min, max);
        int split_value;
        int v_value;
        
        if(v_split == null) {
            return 0;
        }
        else if(v_split.isLeaf()) { //Base Case 1
            
            split_value = returnSplitValue(v_split);
            
            if(v_split.point.z >= min && v_split.point.z <= max) {
                count++; //Report point
            }
            
        } else { //Case 2: Not a leaf
            Node v = v_split.left;
            v_value = returnSplitValue(v);
            
            while(!v.isLeaf()) {
                v_value = returnSplitValue(v);
                if(min <= v_value) {
                    count += reportSubtree(v.right, min, max);
                    v = v.left;
                }
                else {
                    v = v.right;
                }
            }
            
            if(v_value >= min && v_value <= max) {
                count++; //Report point
            }

            v = v_split.right;
            while(!v.isLeaf()) {
                split_value = returnSplitValue(v);
                
                if(max >= split_value) {
                    count += reportSubtree(v.left, min, max);
                    v = v.right;
                } 
                else {
                    v = v.left;
                }
            }
            if(v_value >= min && v_value <= max) {
                count++; //Report point
            }
        }
        
        return count;
    
    }
    
    
    private int returnSplitValue(Node v_split) {
       
        return  v_split.point.z;
        
    }


    public Node findSplitNode(Node v, int min, int max) {
        while(!v.isLeaf() && (max <= v.point.z || min >= v.point.z)) {
            if(max <= v.point.z) {
                v = v.left;
            } else {
                v = v.right;
            }
        }
        return v;
    }
    

    
    
    public int reportSubtree(Node v, int min, int max) {
        int count1 = 0;
        if(v.isLeaf()) {
            if(v.point.z >= min && v.point.z <= max) {
                count1++; //Report point
            }
        } else {
            count1 += reportSubtree(v.left, min, max);
            count1 += reportSubtree(v.right, min, max);
        }
        return count1;
    }
}