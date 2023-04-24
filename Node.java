package prj3;


public class Node {
    public Point point;
    public Point[] py;
    public Point[] pz;
    public BST tree1D;
    public RangeTree2D tree2D;
    public Node left, right;
    
    
    public Node(Point point) {
        this.point = point;
        left = right = null;
        this.py = null;
        this.pz = null;
        this.tree1D = null;
        this.tree2D = null;
    }
    
    
    public Node(Point point, BST tree1D) {
        this.point = point;
        left = right = null;
        this.py = null;
        this.pz = null;
        this.tree1D = tree1D;
        this.tree2D = null;
    }
    
    public Node(Point point, BST tree1D, RangeTree2D tree2D) {
        this.point = point;
        left = right = null;
        this.py = null;
        this.pz = null;
        this.tree1D = tree1D;
        this.tree2D = tree2D;
    }
    public Node(Point point , RangeTree2D tree2D) {
        this.point = point;
        left = right = null;
        this.py = null;
        this.pz = null;
        this.tree1D = null;
        this.tree2D = tree2D;
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
    
    public void setPz(Point[] pz) {
        this.pz = pz;
    }
}
