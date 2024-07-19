package uml;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.Port;

public abstract class BaseObject {
		public boolean IsSelect;
		public int x1,x2,y1,y2,height,width;
		protected String objectName = "Object Name";
		protected Font font = new Font(Font.DIALOG, Font.BOLD, 14);
		public int portwidth = 10;
		
		
		public void draw(Graphics g) {
			
		};
		
		public int getX1(){
			return x1;
		}
		public int getY1(){
			return y1;
		}
		public int getX2(){
			return x2;
		}
		public int getY2(){
			return y2;
		}
		public void changeName(String name){
			this.objectName = name;
		}
		public abstract boolean isInside(Point P);
		
		public abstract int isInPort(Point p);
		
		public abstract PortClass getPort(int portIndex);
		
		public void updatePos(Point P) {
			
		};
		
		public void updatePorts() {
			
		};
		
		public void drawPorts(Graphics g) {
			
		}
		
		protected void createPorts() {
			
		}
		
		public abstract void moveLocation(int moveX, int moveY);
		
}
