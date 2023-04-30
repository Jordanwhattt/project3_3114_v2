package prj3;

public class Prism {
    public Point plow; //Lower Left Corner
    public Point phigh;
    
    
    public Prism(Point plow, Point phigh) {
        this.plow = plow;
        this.phigh = phigh;
    }
    
    public Prism(int xmin, int xmax, int ymin, int ymax, int zmin, int zmax) {
        this(new Point(xmin, ymin, zmin), new Point(xmax, ymax, zmax));
    }
    
    /**
     * Does the rectangle contain this point
     * 
     * @param q
     * @return
     */
    // if low[i] ≤ q[i] ≤ high[i], for 0 ≤ i ≤ d − 1 
    public boolean contains(Point q ) {
        int qx = q.x;
        int qy = q.y;
        int qz = q.z;
        //If the lowest x value in this rectangle is greater than the x value of point q, then its definitely not inside the triangle.
        //This works for each dimension and the max dimension of this rectangle  
    
        if(this.plow.x <= qx && this.plow.y <= qy &&  this.plow.z <= qz &&
            this.phigh.x >= qx && this.phigh.y >= qy && this.phigh.z >= qz) {
            return true;
        }
        

        
        return false;
    }

    
    /**
     * This checks if a rectangle C is inside the entirety of this Rectangle.
     * 
     * @param c
     * @return
     */
    
    //c.low[i], c.high[i] ⊆ low[i], high[i], for all 0 ≤ i ≤ d − 1.

    public boolean contains2d(Prism c) {
        
        if(this.contains(c.plow) && this.contains(c.phigh)) {
            return true;
        }
//        if(this.plow.x <= c.plow.x && this.plow.y <= c.plow.y &&
//            this.phigh.x >= c.phigh.x && this.phigh.y >= c.phigh.y) {
//            return true;
//        }
        
        return false;
    }
    
    



    public boolean intersects(Point point) {
//        if((this.plow.x <= point.x && point.x <= this.phigh.x )||
//            (this.plow.y <= point.y && point.y <= this.phigh.y ) ||
//            (this.plow.z <= point.z && point.z <= this.phigh.z )) {
//            return true;
//        }
      if(this.plow.x <= plow.x && this.plow.y <= plow.y &&
          this.phigh.x >= phigh.x && this.phigh.y >= phigh.y) {
          return true;
      }
        
        return false;
        
    }
    
    public boolean intersects2(Prism c) {
        
        if(this.intersects(c.plow) || this.intersects(c.phigh)) {
            return true;
        }
        
//        if(this.plow.x <= c.plow.x && this.plow.y <= c.plow.y &&
//            this.phigh.x >= c.phigh.x && this.phigh.y >= c.phigh.y) {
//            return true;
//        }
        
        return false;
    }
    
}
