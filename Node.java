package prj3;


public class Node {
    public Point point;
    public Prism range;
    public Point[] px;
    public Point[] py;
    public Point[] pz;
    public Node left, right;
    
    
    public Node(Point point) {
        this.point = point;
        left = right = null;
        this.px = this.py = null;
    }
    
    
    public boolean isLeaf() {
        if(left == null & right == null) {
            return true;
        }
        return false;
    }
    
    public void setPx(Point[] points) {
        this.px = points;
    }
    
    
    public void setPy(Point[] points) {
        this.py = points;
    }
    
    
    public void setPz(Point[] points) {
        this.pz = points;
    }
    

}
