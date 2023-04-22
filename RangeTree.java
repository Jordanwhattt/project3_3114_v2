package prj3;

import java.util.Arrays;

public class RangeTree {
    
    public Node root;
    public String type;
    
    //1D Range Tree
    public RangeTree() {
        root = null;
        this.type = "y";
    }
    
    
    public RangeTree(String type) {
        root = null;
        this.type = type;
    }
    
    
    public Node buildTree(Point[] points_x, Point[] points_y) {
        int n = points_x.length;
        
        //1. Build a binary search tree for based on P_y
        
        BST y_tree = new BST("y");
        y_tree.root = y_tree.buildTree(points_y);
        
        //2. if P contains only one point
        if(n == 1) {
            Node v = new Node(points_x[0], y_tree); //Creates leaf node storing this point and make it 
            Point[] pyArray = new Point[1];
            pyArray[0] = points_x[0];
            v.setPy(pyArray);
            return v;
        }
        
        Point[] px_left;
        Point[] px_right;
        Point[] py_left;
        Point[] py_right;
        
    /*
     * Point[] pz_left;
     * Point[] pz_right;
     */
        
    
        // Find median
        int median_index = (n/2);


        // Split P into P_left and P_right.
        
        if(n % 2 == 0) { // This is 
            px_left = Arrays.copyOfRange(points_x, 0, median_index);
            px_right = Arrays.copyOfRange(points_x, median_index, n);
        }
        else {
            px_left = Arrays.copyOfRange(points_x, 0, median_index);
            px_right = Arrays.copyOfRange(points_x, median_index, n);
        }
        

        py_left = new Point[px_left.length];
        py_right = new Point[px_right.length];
        //pz_left = new Point[px_left.length];
        //pz_right = new Point[px_right.length];
            

        int left_index = 0, right_index = 0;
        // Then subsets <= and > the median x-coordinate x_mid
        for (Point p : points_y) {
            if (p.getX() <= points_x[median_index-1].getX()) {
                py_left[left_index] = p;
                left_index++;
            }
            else {
                py_right[right_index] = p;
                right_index++;
            }
        }
        /*
         * for (Point p : points_z) {
         * if (p.getX() <= points_x[median_index-1].getX()) {
         * py_left[iLeft++] = p;
         * }
         * else {
         * py_right[iRight++] = p;
         * }
         * }
         */

        
        Node v = new Node(points_x[median_index-1], y_tree);
        v.setPy(points_y);

        //5. build v_left
        Node v_left = this.buildTree(px_left, py_left);
        //6. build v_right
        Node v_right = this.buildTree(px_right, py_right);
        
        //Make v_left the left child of v
        v.left = v_left;
        //Make v_right the right child of v
        v.right = v_right;    
        return v;
        
    }
    
    
    /**
     * This binary Search Tree inserts a Node containing an array of points
     * @param point
     */
    public void insert(Node point) {
        root = insertRec(root, point);
    }
    
    
    /**
     * This insert function is recursively called
     * @param root
     * @param point
     * @return
     */
    private Node insertRec(Node root, Node point) {
        
        if(root == null) { //If the root node is empty, set the root node equal to a new node with Point as the value associated with it.
            return new Node(point.point);
        }
        //Insert Points based on the x coordinate
        if(this.type.equals("x")) {
            if(point.point.getX() <= root.point.getX()) { //If X value is <=, insert to the left
                root.left = insertRec(root.left, point);
            }
            else if(point.point.getX() > root.point.getX()) { //If X value is >, insert to the right
                root.right = insertRec(root.right, point);
            }
        } 
        else if(this.type.equals("y")) {
            if(point.point.getY() <= root.point.getY()) { //If Y value is <=, insert to the left
                root.left = insertRec(root.left, point);
            }
            else if(point.point.getY() > root.point.getY()) { //If Y value is >, insert to the right
                root.right = insertRec(root.right, point);
            }
        }        
        else if(this.type.equals("z")) {
            if(point.point.getZ() <= root.point.getZ()) { //If Z value is <=, insert to the left
                root.left = insertRec(root.left, point);
            }
            else if(point.point.getZ() > root.point.getZ()) { //If Z value is >, insert to the right
                root.right = insertRec(root.right, point);
            }
        }

        return root;
    }
    
    
    public int RangeQuery1D(RangeTree tree, int min, int max) {
        Node v_split = findSplitNode(tree, min, max);
        int x = v_split.point.getX();
        int count = 0;
        
        
        if(v_split.isLeaf()) {
            if(x >= min & x <= max) {
                count++; 
            } 
        } 
        else {
            Node v = v_split.left;
            while(!v.isLeaf()) {
                if(min <= v.point.getX()) {
                    //TODO reportSubtree(v.right);
                    v = v.left;
                }
                else {
                    v = v.right;
                }
             }
        }
        return count;
    }
    
    
    public Node findSplitNode(RangeTree tree, int min, int max) {
        Node v = tree.root;
        
        while(!v.isLeaf() & (max <= v.point.getX() | min > v.point.getX())) {
            if(max <= v.point.getX()) {
                v = v.left;
            } else {
                v = v.right;
            }
        }
        return v;
    }
    
}
