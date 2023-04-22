package prj3;


public class Node {
    public Point point;
    public Point[] py;
    public BST y_tree;
    public Node left, right;
    
    
    public Node(Point point) {
        this.point = point;
        left = right = null;
        this.py = null;
        this.y_tree = null;
    }
    
    
    public Node(Point point, BST y_tree) {
        this.point = point;
        left = right = null;
        this.py = null;
        this.y_tree = y_tree;
    }
    
    public boolean isLeaf() {
        if(left == null & right == null) {
            return true;
        }
        return false;
    }
    
    public void setPy(Point[] py) {
        this.py = py;
    }
}
