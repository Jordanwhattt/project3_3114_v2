package prj3;


public class Node {
    public Point point;
    public int depth;
  
    public Node left, right;
    
    
    public Node(Point point) {
        this.point = point;
        this.left = this.right = null;
        depth = 0;
    }
    public Node(Point point, int depth) {
        this.point = point;
        this.left = this.right = null;
        this.depth = depth;
    }
    
    
    public boolean isLeaf() {
        if(this.left == null & this.right == null) {
            return true;
        }
        return false;
    }

    

}