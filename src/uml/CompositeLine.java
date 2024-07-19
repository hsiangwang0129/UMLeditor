
package uml;

import java.awt.Graphics;
import java.awt.Point;

public class CompositeLine extends Line{
	
	private PortClass[] ports = new PortClass[2];
	
	public CompositeLine(int x1,int x2,int y1, int y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
	
	public void setPorts(PortClass port_1,PortClass port_2) {
		this.ports[0] = port_1;
		this.ports[1] = port_2;
	}
	
	public PortClass[] getPorts() {
		return ports;
	}
	
	public void setPort1(PortClass port_1) {
		this.ports[0] = port_1;
		
	}
	
	public void setPort2(PortClass port_2) {
		
		this.ports[1] = port_2;
	}
	public String isInside(Point p) {
		//System.out.println("line--isInside");
		int maxdistance = 5;
		//System.out.println(distance(p));
        if(distance(p) < maxdistance) {
        	Point StartPos = new Point(x1,y1);
        	Point EndPos = new Point(x2,y2);
        	double distanceToStart = p.distance(StartPos);
        	double distanceToEnd = p.distance(EndPos);
        	if(distanceToStart < distanceToEnd) {
        		selectPos = "start";
			}
			else{
				selectPos = "end";
			}
        	//System.out.println("(t)(t)(t)Line--isInside");
        	return "true";
        }
        return null;
    }
	
	public void draw(Graphics g) {
		g.drawLine(x1, y1, x2, y2);
		// Calculate arrowhead direction (end of the line)
	    double angle = Math.atan2(y2 - y1, x2 - x1);
	    
	    // Define arrowhead length
	    int arrowLength = 10;
	    
	    // Calculate coordinates for the arrowhead points
	    int x3 = x2 - (int) (arrowLength * Math.cos(angle - Math.PI / 4));
	    int y3 = y2 - (int) (arrowLength * Math.sin(angle - Math.PI / 4));
	    int x4 = x2 - (int) (arrowLength * Math.cos(angle + Math.PI / 4));
	    int y4 = y2 - (int) (arrowLength * Math.sin(angle + Math.PI / 4));
		
	    int x5 = x3 - (int) (arrowLength * Math.cos(angle + Math.PI / 4));
	    int y5 = y3 - (int) (arrowLength * Math.sin(angle + Math.PI / 4));
	    int[] xpoints = {x2, x3,x5, x4};
        int[] ypoints = {y2, y3,y5 ,y4};
		
        
        // 繪製菱形
        g.fillPolygon(xpoints, ypoints, 4);
	}
	
	public double distance(Point p) {
		  //System.out.println("distance Function");
		  //System.out.println("x1"+x1+"x2"+x2+"y1"+y1+"y2"+y2);
	      double A = p.x - x1; // position of point rel one end of line
	      double B = p.y - y1;
	      double C = x2 - x1; // vector along line
	      double D = y2 - y1;
	      double E = -D; // orthogonal vector
	      double F = C;
	      //System.out.println("A = "+A);
	      //System.out.println("D = "+D);
	      //System.out.println("y2 = "+y2);
	      double dot = A * E + B * F;
	      double len_sq = E * E + F * F;
	      //System.out.println("dot = "+dot);
	      //System.out.println("len_sq = "+len_sq);
	      double distance = (double) Math.abs(dot) / Math.sqrt(len_sq);
	      //System.out.println("distance = "+distance);
	      return distance;
	    }
	
	public void resetPos() {
		this.x1 = (int) ports[0].getcenter().getX();
		
		this.y1 = (int) ports[0].getcenter().getY();
		
		this.x2 = (int) ports[1].getcenter().getX();
		
		this.y2 = (int) ports[1].getcenter().getY();
	}
	
	
	
	
	public void resetLine(Point p) {
		if(selectPos!=null) {
			if(selectPos.equals("start")) {
				this.x1 = p.x;
				this.y1 = p.y;
			}
			else {
				this.x2 = p.x;
				this.y2 = p.y;
			}
		}
	}
}

