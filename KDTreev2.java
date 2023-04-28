package prj3;

import java.util.Arrays;
import java.util.Comparator;


public class KDTreev2 {
    
    Node root;
    int query_count;

    public KDTreev2(Point[] points) {
        
        int l = points.length;
        Point[] px = new Point[l];
        Point[] py = new Point[l];
        Point[] pz = new Point[l];
        
        for(int i = 0; i < l; i++) {
            px[i] = points[i];
            py[i] = points[i];
            pz[i] = points[i];
        }
        
        Arrays.sort(px, (a, b) -> a.x - b.x);
        Arrays.sort(py, (a, b) -> a.y - b.y);
        Arrays.sort(pz, (a, b) -> a.z - b.z);

        this.root = buildTree(px, py, pz, 0);
        
    }
    
    
    
    
    
    public Node buildTree(Point[] px, Point[] py, Point[] pz, int depth) {
        
        int n = px.length;
        int axis = depth % 3;
        
        if(n == 0) {
            return null;
        }
        if(n == 1) {
            Node v = new Node(new Point(px[0].x, px[0].y, px[0].z), depth); 
            v.setPx(px);
            v.setPy(py);
            v.setPz(pz);
            return v;
           
            
        }

        

        int l_length = 0;
        int mid_i =0;
        if(n % 2 == 0) {
            l_length = n / 2;
            mid_i = l_length - 1;
        }
        else {
            mid_i = n/2;
            l_length = mid_i + 1;
        }
        
        Point[] x_left = new Point[l_length];
        Point[] x_right = new Point[n - l_length];
        
        Point[] y_left = new Point[l_length];
        Point[] y_right = new Point[n - l_length];
        
        Point[] z_left = new Point[l_length];
        Point[] z_right = new Point[n - l_length];
        
        if(axis == 0) {

            Node v = new Node(new Point(px[mid_i].x, px[mid_i].y, px[mid_i].z), depth);
            v.setPx(px);
            v.setPy(py);
            v.setPz(pz);
            int xleft_index = 0;
            int xright_index = 0;
            int yleft_index = 0;
            int zleft_index = 0;
            int yright_index = 0;
            int zright_index = 0;
            
            for(int i = 0; i < px.length; i++) {
                
                if(v.point.x >= px[i].x) {
                    x_left[xleft_index] = px[i];
                    xleft_index++;
                } else {
                    x_right[xright_index] = px[i];
                    xright_index++;
                }
           
                if(v.point.x >= py[i].x) {
                    y_left[yleft_index] = py[i];
                    yleft_index++;
                    
                } else  {
                    y_right[yright_index] = py[i];
                    yright_index++;
                }
                
                if(v.point.x >= pz[i].x) {
                    z_left[zleft_index] = pz[i];
                    zleft_index++;
                } else {
                    z_right[zright_index] = pz[i];
                    zright_index++;
                }
                    
            }

            
            v.left = buildTree(x_left, y_left, z_left, depth + 1);
            v.right = buildTree(x_right, y_right, z_right, depth + 1);
  
            
            return v;
        }
        else if(axis == 1) {

            Node v = new Node(new Point(py[mid_i].x, py[mid_i].y, py[mid_i].z), depth);
            v.setPx(px);
            v.setPy(py);
            v.setPz(pz);
            
            int xleft_index = 0; 
            int zleft_index = 0;
            int yleft_index = 0;
            int yright_index = 0;
            int xright_index = 0;
            int zright_index = 0;
            
            for(int i = 0; i < py.length; i++) {
                
                if(v.point.y >= px[i].y) {
                    x_left[xleft_index] = px[i];
                    xleft_index++;
                } else {
                    x_right[xright_index] = px[i];
                    xright_index++;
                }
               
                
                if(v.point.y >= py[i].y) {
                    y_left[yleft_index] = py[i];
                    yleft_index++;
                } else {
                    y_right[yright_index] = py[i];
                    yright_index++;
                }
                
                if(v.point.y >= pz[i].y) {
                    z_left[zleft_index] = pz[i];
                    zleft_index++;
                } else {
                    z_right[zright_index] = pz[i];
                    zright_index++;
                }
            }

            v.left = buildTree(x_left, y_left, z_left, depth + 1);
            v.right = buildTree(x_right, y_right, z_right, depth + 1);
            return v;
        }
        else {
            
            Node v = new Node(new Point(pz[mid_i].x, pz[mid_i].y, pz[mid_i].z), depth);
            v.setPx(px);
            v.setPy(py);
            v.setPz(pz);
            int xleft_index = 0; 
            int yleft_index = 0;
            int xright_index = 0;
            int yright_index = 0;
            int zright_index = 0;
            int zleft_index = 0;
            
            for(int i = 0; i < pz.length; i++) {

                if(v.point.z >= px[i].z) {
                    x_left[xleft_index] = px[i];
                    xleft_index++;
                } else {
                    x_right[xright_index] = px[i];
                    xright_index++;
                }
                
                if(v.point.z >= py[i].z) {
                    y_left[yleft_index] = py[i];
                    yleft_index++;
                } else  {
                    y_right[yright_index] = py[i];
                    yright_index++;
                }
                
                if(v.point.z >= pz[i].z) {
                    z_left[zleft_index] = pz[i];
                    zleft_index++;
                } else {
                    z_right[zright_index] = pz[i];
                    zright_index++;
                }
            }

            v.left = buildTree(x_left, y_left, z_left, depth + 1);
            v.right = buildTree(x_right, y_right, z_right, depth + 1);
            return v;
        }
        
        
        
    
    }
    
    
    
   
    
    /**
     * Gets the median of the array
     * @param coords_arr
     * @return
     */
    private Point getMedian(Point[] coords_arr) {
        int n = coords_arr.length;
        return coords_arr[(n / 2) - 1]; 
    }
    
    
//    public int rangeCount(Prism R, Node v, Prism cell) {
//        if(v == null) {
//            return 0;
//        }
//        else if(R.isDisjoint(cell, v.depth)) {
//            return 0;
//        }
//        else if(R.contains(cell, v.depth)) {
//            return v.px.length;
//        }
//        else {
//            int count = 0;
//            if (R.contains(v.point, count) && v.isLeaf()) // consider this point
//                count += 1;
//            count += rangeCount(R, v.left, cell.leftPart(v.depth + 1, v.point));
//            count += rangeCount(R, v.right, cell.rightPart(v.depth + 1, v.point));
//            return count;
//        }
//        
//        
//    }
    
    
    
    public int searchKdTree(Node v, Prism range) {
        int qc=0;
        //If range is not in the region of this node.
        //
        if(!range.intersects(v.point)) {
            return 0;
        }
        else if(range.contains(region(v), v.depth)){
            return v.px.length;
        }
        
        if(range.contains(v.point, v.depth)) {
            qc++;
        }
        
        if(v.depth % 3 == 0 && !v.isLeaf()) {
            
            if(range.plow != null && range.plow.x < v.point.x) {
                qc += searchKdTree(v.left, range);
            }
            if(range.phigh != null && range.phigh.x > v.point.x) {
                qc += searchKdTree(v.right, range);
            }
        }

        if(v.depth % 3 == 1 && !v.isLeaf()) {
            if(range.plow != null && range.plow.y < v.point.y) {
                qc += searchKdTree(v.left, range);
            }
            if(range.phigh != null && range.phigh.y > v.point.y) {
                qc += searchKdTree(v.right, range);
            }
        }
        if(v.depth % 3 == 2 && !v.isLeaf()) {
            if(range.plow != null && range.plow.z < v.point.z) {
                qc += searchKdTree(v.left, range);
            }
            if(range.phigh != null && range.phigh.z > v.point.z) {
                qc += searchKdTree(v.right, range);
            }
        }
        
        

        return qc;
        
//        if (v.isLeaf()) {
//            if (range.contains(v.point, v.depth) && !v.visited) {
//                v.visited = true;
//                return query_count++;
//            }
//            return 0;
//        }
//        else {
//            v.visited = true;
//            if (range.contains(region(v.left), v.depth++)) {
//                return query_count += v.left.px.length;//report subtreee. 
//                //The length of the points array tells us how many leaves are in that subtree
//            }
//            // todo
//            else if (range.intersects(v.left.point)) {
//                query_count += searchKdTree(v.left, range);
//            }
//            // todo
//            if (range.contains(region(v.right), v.depth++)) {
//                return query_count += v.right.px.length; //report subtreee. 
//                //The length of the points array tells us how many leaves are in that subtree
//            }
//            // todo
//            else if (range.intersects(v.right.point)) {
//                query_count += searchKdTree(v.right, range);
//            }

//        }
    }
    
    
    
    public Prism region(Node p) {
        int n = p.px.length;
        Point min_point = new Point(p.px[0].x, p.py[0].y, p.pz[0].z);
        Point max_point = new Point(p.px[n - 1].x, p.py[n - 1].y, p.pz[n- 1].z);
        return new Prism(min_point, max_point);
    }

    
}
