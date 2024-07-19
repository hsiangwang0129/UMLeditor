package uml;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class GroupObj extends BaseObject{
	private List<BaseObject> objlist = new ArrayList<BaseObject>();
	
	
	public void addmember(BaseObject obj) {
		objlist.add(obj);
	}
	
	public List<BaseObject> getmember() {
		return objlist;
	}
	
	public void resetAllPos(int deltaX, int deltaY) {
		for(int i = 0;i<objlist.size();i++) {
			BaseObject obj = objlist.get(i);
			obj.moveLocation(deltaX,deltaY);
		}
	}
	
	@Override
	public boolean isInside(Point P) {
		// TODO Auto-generated method stub
		for(int i = 0;i<objlist.size();i++) {
			BaseObject selectobj = objlist.get(i);
			if(selectobj.isInside(P)) {
				return true;
			}
			
		}
		return false;
	}
	
	public boolean contains(BaseObject selectObj) {
		return objlist.contains(selectObj);
	}
	
	public Rectangle show() {
		int maxX=0;
		int maxY=0;
		int minX=100000;
		int minY=100000;
		
		for(int i = 0;i<objlist.size();i++) {
			System.out.println("in show");
			BaseObject selectobj = objlist.get(i);
			System.out.println(selectobj.x2);
			System.out.println(selectobj.y2);
			System.out.println(selectobj.x1);
			System.out.println(selectobj.y1);
			
			maxX = Math.max(maxX,selectobj.x2);
			maxY = Math.max(maxY,selectobj.y2);
			minX = Math.min(minX,selectobj.x1);
			minY = Math.min(minY,selectobj.y1);
			System.out.println("++++++++++++++++++++++");
			System.out.println(maxX);
			System.out.println(maxY);
			System.out.println(minX);
			System.out.println(minY);
		}
		System.out.println("++++++++++++++++++++++");
		System.out.println(maxX);
		System.out.println(maxY);
		System.out.println(minX);
		System.out.println(minY);
		return new Rectangle(minX,minY,maxX-minX,maxY-minY);
	}
	
	@Override
	public int isInPort(Point p) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PortClass getPort(int portIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void moveLocation(int moveX, int moveY) {
		// TODO Auto-generated method stub
		
	}
	
	

}
