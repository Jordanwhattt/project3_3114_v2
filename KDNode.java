package prj3;

public class KDNode {
    public Point point;
    public int depth;
    public boolean visited;
    public Point[] px;
    public Point[] py;
    public Point[] pz;
    public KDNode left, right;
    public BST z_tree;
    
    public KDNode(Point point) {
        this.point = point;
        this.left = this.right = null;
        this.px = this.py = null;
        this.visited = false;
        this.depth = 0;
    }
    public KDNode(Point point, int depth, BST ztree) {
        this.point = point;
        this.left = this.right = null;
        this.px = this.py = null;
        this.visited = false;
        this.depth = depth;
        this.z_tree = ztree;
    }
    
    
    public boolean isLeaf() {
        if(this.left == null & this.right == null) {
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