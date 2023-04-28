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
    
        if(this.plow.x <= qx && this.plow.y <= qy && this.plow.z <= qz &&
            this.phigh.x >= qx && this.phigh.y >= qy && this.phigh.z >= qz) {
            return true;
        }
        
//        if(cd % 3 == 0) {
//            if(this.plow.x <= qx && this.plow.y <= qy &&
//                this.phigh.x >= qx & this.phigh.y >= qy) {
//                return true;
//            } 
//        }
//        else if(cd % 3 == 1) {
//            if(this.plow.y <= qy && this.plow.z <= qz &&
//                this.phigh.y >= qy && this.phigh.z >= qz) {
//                return true;
//            } 
//        }
//        else if(cd % 3 == 2) {
//            if(this.plow.x <= qx && this.plow.z <= qz &&
//                this.phigh.z >= qx && this.phigh.z >= qz) {
//                return true;
//            } 
//        }
        
        return false;
    }
    
    
    /**
     * This checks if a rectangle C is inside the entirety of this Rectangle.
     * 
     * @param c
     * @return
     */
    
    //c.low[i], c.high[i] ⊆ low[i], high[i], for all 0 ≤ i ≤ d − 1.

    public boolean contains(Prism c) {
        
        if(this.contains(c.plow) && this.contains(c.phigh)) {
            return true;
        }
        
        return false;
    }
    
    
    // r.high[i] < c.low[i] or r.low[i] > c.high[i], for any 0 ≤ i ≤ d − 1.
    public boolean isDisjoint(Prism c ,int cd ) { 
        if(this.contains(c) ) {
            return false;
        }
        
        if( ((this.phigh.x < c.plow.x) || (this.plow.x > c.phigh.x)) ||
            ((this.phigh.y < c.plow.y) || (this.plow.y > c.phigh.y)) || 
            ((this.phigh.z < c.plow.z) || (this.plow.z > c.phigh.z)) ) {
            return true;
        }
        
      
//        if(cd % 3 == 0) {
//            if( ((this.phigh.x < c.plow.x) || (this.plow.x > c.phigh.x)) ||
//                ((this.phigh.y < c.plow.y) || (this.plow.y > c.phigh.y)) ) {
//                return true;
//            }
//        }
//        else if(cd % 3 == 1) {
//            if( ((this.phigh.y < c.plow.y) || (this.plow.y > c.phigh.y)) ||
//                ((this.phigh.z < c.plow.z) || (this.plow.z > c.phigh.z)) ) {
//                return true;
//            }
//        } else {
//            if(cd % 3 == 2) {
//                if( ((this.phigh.x < c.plow.x) || (this.plow.x > c.phigh.x)) ||
//                    ((this.phigh.z < c.plow.z) || (this.plow.z > c.phigh.z)) ) {
//                    return true;
//                }
//            }
//        }
        
        
        return false;
    }
    
    
    public Prism leftPart(int cd, Point s) {
        Point new_phigh;
        if(cd % 3 == 0) {
           new_phigh = new Point(s.x, this.phigh.y, this.phigh.z);
           return new Prism(this.plow, new_phigh);
        } else if( cd % 3 == 1) {
            new_phigh = new Point(this.phigh.x, s.y, this.phigh.z);
            return new Prism(this.plow, new_phigh);
        } else {
            new_phigh = new Point(this.phigh.x, this.phigh.y, s.z);
            return new Prism(this.plow, new_phigh);
        }
    }
    
    
    public Prism rightPart(int cd, Point s) {
        Point new_plow;
        if(cd % 3 == 0) {
            new_plow = new Point(s.x, this.phigh.y, this.phigh.z);
            return new Prism(new_plow, this.phigh);
        } else if( cd % 3 == 1) {
            new_plow = new Point(this.phigh.x, s.y, this.phigh.y);
            return new Prism(new_plow, this.phigh);
        } else {
            new_plow = new Point(this.phigh.x, this.phigh.y, s.z);
            return new Prism(new_plow, this.phigh);
        }
    }

    public boolean intersects(Point p ) {
        if((this.plow.x <= p.x && p.x <= this.phigh.x )||
            (this.plow.y <= p.y && p.y <= this.phigh.y ) ||
            (this.plow.z <= p.z && p.z <= this.phigh.z )) {
            return true;
        }
        return false;
        
    }
    
    
    
}
