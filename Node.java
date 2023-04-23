package prj3;


public class Node {
    public Point point;
    public Point[] py;
    public BST first_tree;
    public RangeTree2D second_tree;
    public Node left, right;
    
    
    public Node(Point point) {
        this.point = point;
        left = right = null;
        this.py = null;
        this.first_tree = null;
    }
    
    
    public Node(Point point, BST first_tree) {
        this.point = point;
        left = right = null;
        this.py = null;
        this.first_tree = first_tree;
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
