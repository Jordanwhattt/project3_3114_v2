package prj3;

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
        
        //1. if P contains only one point
        if(n == 1) {
            Node v = new Node(points[0]); //Createa leaf node storing this point and make it 
            return v;
        }
        
        Point[] p_left;
        Point[] p_right;
        Point mid;
        
        if(n % 2 == 1) {
            
            //Find median
            int median_index = n / 2;
            mid = points[median_index];

            //Split P into P_left and P_right. 
            p_left = new Point[median_index + 1];
            p_right = new Point[median_index];
            //Then subsets <= and > the median x-coordinate x_mid
            for(int i = 0; i < p_left.length; i++) {
               p_left[i] = points[i];
            }
            for(int i = 0; i < p_right.length; i++) {
                p_right[i] = points[i + p_left.length];
            }
            
        } else {
            
            int median_index = n/2 - 1;
            mid = points[median_index];
            
            p_left = new Point[median_index + 1];
            p_right = new Point[median_index + 1];
            //Then subsets <= and > the median x-coordinate x_mid
            for(int i = 0; i < p_left.length; i++) {
               p_left[i] = points[i];
            }
            for(int i = 0; i < p_right.length; i++) {
                p_right[i] = points[i + p_left.length];
            }
            
        }
        
       
        
        //5. build v_left
        Node v_left = this.buildTree(p_left);
        //6. build v_right
        Node v_right = this.buildTree(p_right);
        
        //7. Create node v storing x_mid
        Node v = new Node(mid);
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
}
