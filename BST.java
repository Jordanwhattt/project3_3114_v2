package prj3;

import java.util.Arrays;

public class BST {
    
    public Node root;
    public String type;
    
    public BST() {
        root = null;
        this.type = "y";
    }
    
    
    public BST(String type) {
        root = null;
        this.type = type;
    }
    
    
    public Node buildTree(Point[] points) {
        int n = points.length;
        
        //If P contains one Point
        if(n == 1) {
            Node v = new Node(points[0]); //Createa leaf node storing this point and make it 
            return v;
        }
        
        Point[] p_left;
        Point[] p_right;
        Point mid;
        
        int median_index = n / 2;
        
        
        if(n % 2 == 0) { // This is 
            p_left = Arrays.copyOfRange(points, 0, median_index);
            p_right = Arrays.copyOfRange(points, median_index, n);
        }
        else {
            p_left = Arrays.copyOfRange(points, 0, median_index);
            p_right = Arrays.copyOfRange(points, median_index, n);
        }

        Node v = new Node(points[median_index-1]);
        v.setPy(points);

        //5. build v_left
        Node v_left = this.buildTree(p_left);
        //6. build v_right
        Node v_right = this.buildTree(p_right);
        
        //Make v_left the left child of v
        v.left = v_left;
        //Make v_right the right child of v
        v.right = v_right;    
        return v;
        
    }
    
}