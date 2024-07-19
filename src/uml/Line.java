package uml;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Line2D;

public abstract class Line {
	int x1,x2,y1,y2;
	public String selectPos = null;
	public abstract String isInside(Point p) ;
	
	
	
	public void draw(Graphics g) {};
	public abstract void setPorts(PortClass port_1,PortClass port_2);
	public abstract double distance(Point p);
	
	/*
	private double distance(Point p) {
		System.out.println("x1"+x1+"x2"+x2+"y1"+y1+"y2"+y2);
		Line2D line = new Line2D.Double(x1, y1, x2, y2);
		return line.ptLineDist(p.getX(), p.getY());
	}
	*/
	public Point getStart() {
		return new Point(x1,y1);
	}
	public abstract void setPort1(PortClass port_1);
	public abstract void setPort2(PortClass port_2);
	public Point getEnd() {
		return new Point(x2,y2);
	}
	public abstract PortClass[] getPorts() ;
	public abstract void resetLine(Point p);
	public abstract void resetPos();
}
