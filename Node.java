package prj3;


public class Node {
    public Point point;
    public Point[] py;
    public Node left, right;
    public int depth;
    
    
    public Node(Point point) {
        this.point = point;
        left = right = null;
        this.py = null;
        this.depth = 0;
    }
    
    public Node(Point point, int depth) {
        this.point = point;
        left = right = null;
        this.py = null;
        this.depth = depth;
    }
    
    
    public boolean isLeaf() {
        if(left == null & right == null) {
            return true;
        }
        return false;
    }
    
    public void setPy(Point[] points) {
        this.py = points;
    }
    
}
