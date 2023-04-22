package prj3;


public class Node {
    public Point point;
    
    public Node left, right;
    
    public Node(Point point) {
        this.point = point;
        left = right = null;
    }
    
    public boolean isLeaf() {
        if(left == null & right == null) {
            return true;
        }
        return false;
    }
}
