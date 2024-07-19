package uml;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class classObj extends BaseObject{
	private int midX,midY;
	private List<PortClass> portlist = new ArrayList<PortClass>();
	public classObj(int x1, int y1) {
		this.width = 120;
		this.height = 90;
		this.x1 = x1-width/2;
		this.y1 = y1-height/2;
		this.x2 = this.x1 + width;
		this.y2 = this.y1 + height;
		createPorts();
	}
	public void draw(Graphics g) {
		g.drawRect(x1, y1, width, height); //create the object in the middle of the mouse
		
		int portion = height / 3;
		g.drawLine(x1, y1 + portion, x2, y1 + portion);
		g.drawLine(x1, y1 + portion * 2, x2, y1 + portion * 2);
		g.setFont(font);	
		g.drawString(objectName, x1+15, y1 + 15);
	}
	public boolean isInside(Point p) {
	    return p.x >= this.x1 && p.x <= this.x2 && p.y >= this.y1 && p.y <= this.y2;
	}
	
	public int isInPort(Point p) {
		
		if(isInside(p)) {
			int midX = (x1+x2)/2;
			int midY = (y1+y2)/2;
			if(p.x <= (x1+midX)/2) {
				System.out.println("3");
				return 3;
			}
			else if(p.x>(midX+x2)/2) {
				System.out.println("1");
				return 1;
			}
			else {
				if(p.y<=midY) {
					System.out.println("0");
					return 0;
				}
				else
					System.out.println("2");
					return 2;
			}
			
			
			
		}
		
		
		
	    return -1;
	}
	
	
	public void moveLocation(int moveX, int moveY) {
		int x1 = this.x1 + moveX;
		int y1 = this.y1 + moveY;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x1 + width;
		this.y2 = y1 + height;
		int midX = (this.x1 + this.x2)/2;
		int midY = (this.y1 + this.y2)/2;
		
		int[] xs = {midX-5,this.x2-5,midX-5,this.x1-5}; //-5矯正視覺偏差
		int[] ys = {this.y1-5,midY-5,this.y2-5,midY-5};
		for(int i = 0;i<portlist.size();i++) {
			portlist.get(i).resetPos(xs[i],ys[i]);
			portlist.get(i).resetLines();
			
		}
	}
	
	public void updatePos(Point P) {
		this.x1 = P.x-width/2;
		this.y1 = P.y-height/2;
		this.x2 = this.x1 + width;
		this.y2 = this.y1 + height;
	};
	
	public void updatePorts() {
		
		int midX = (this.x1 + this.x2)/2;
		int midY = (this.y1 + this.y2)/2;
		
		int[] xs = {midX-5,this.x2-5,midX-5,this.x1-5}; //-5矯正視覺偏差
		int[] ys = {this.y1-5,midY-5,this.y2-5,midY-5};
		for(int i = 0;i<portlist.size();i++) {
			portlist.get(i).resetPos(xs[i],ys[i]);
			portlist.get(i).resetLines();
			
		}
	}
	
	public void drawPorts(Graphics g) {
		//System.out.println("drawporst");
		for(int i = 0;i< portlist.size();i++) {
			//System.out.println(portlist.get(i).x);
			//System.out.println(portlist.get(i).y);
			//System.out.println(this.x1);
			//System.out.println(this.y1);
			g.fillRect(portlist.get(i).x, portlist.get(i).y,portwidth,portwidth);
			
		}
	}
	
	protected void createPorts() {
		//System.out.println("createPPPP");
		int midX = (this.x1 + this.x2)/2;
		int midY = (this.y1 + this.y2)/2;
		/*
		int[] xs = {this.x1-5,this.x2-5,midX-5,midX-5}; //-5矯正視覺偏差
		int[] ys = {midY,midY-5,this.y1-5,this.y2-5};
		*/
		int[] xs = {midX-5,this.x2-5,midX-5,this.x1-5}; //-5矯正視覺偏差
		int[] ys = {this.y1-5,midY-5,this.y2-5,midY-5};
		
		for(int i = 0;i<4;i++) {
			PortClass newport = new PortClass(xs[i],ys[i],portwidth);
			portlist.add(newport);
		}
	}
	
	public PortClass getPort(int portIndex) {
		return portlist.get(portIndex);
	}
}
