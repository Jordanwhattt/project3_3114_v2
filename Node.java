package prj3;


public class Node {
    public Point point;
    public int depth;
    public Prism range;
    public Point[] p;
    public Node left, right;
    
    
    public Node(Point point) {
        this.point = point;
        left = right = null;
        this.p = null;
        depth = 0;
    }
    public Node(Point point, int depth) {
        this.point = point;
        left = right = null;
        this.p = null;
        this.depth = depth;
    }
    
    
    public boolean isLeaf() {
        if(left == null & right == null) {
            return true;
        }
        return false;
    }
    
    public void setP(Point[] points) {
        this.p = points;
    }
    
    

}
