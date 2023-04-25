package prj3;


public class Node {
    public Point point;
    public Point[] py;
    public Point[] pz;
    public Node left, right;
    public int depth;
    
    
    public Node(Point point) {
        this.point = point;
        left = right = null;
        this.py = null;
        this.pz = null;
        this.depth = 0;
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
