package uml;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import javax.swing.JPanel;



public class Panel extends JPanel{
	private static Panel instance = null;
	protected String Mode = null;
	public Line tempLine = null;
	public BaseObject selectObj = null;
	public Line selectLine = null;
	int x1,y1;
	private EventListener listener = null;
	protected Mode currentMode = null;
	private List<BaseObject> objlist = new ArrayList<BaseObject>();
	private List<Line> linelist = new ArrayList<Line>();
	private List<BaseObject> Groupobjlist = new ArrayList<BaseObject>();
	private List<Line> Grouplinelist = new ArrayList<Line>();
	private List<GroupObj> GroupObjS = new ArrayList<GroupObj>();	
	public Rectangle selectedArea = null;
	public Rectangle GroupArea = null;
	private Panel() {
		
	}
	
	

	
	public static Panel getInstance() {
		if(instance == null) {
			instance = new Panel();
		}
		return instance;
	}
	
	public void updateMode() {
		removeMouseListener((MouseListener) listener);
		removeMouseMotionListener((MouseMotionListener) listener);
		listener = currentMode;
		addMouseListener((MouseListener) listener);
		addMouseMotionListener((MouseMotionListener) listener);
		
	}
	
	public void changeObjName(String name) {
		if(selectObj != null){
			selectObj.changeName(name);
			repaint();
		}
	}
	
	public void add(BaseObject obj) {
		objlist.add(obj);
	}
	
	public List<BaseObject> getObjects() {
		return objlist;
	}
	
	public List<GroupObj> getGroupobj() {
		return GroupObjS;
	}
	
	public List<Line> getLines() {
		return linelist;
	}
	public void clear() {
		if(selectObj != null) {
			selectObj = null;
			repaint();
		}
		if(selectLine != null) {
			selectLine = null;
			repaint();
		}
		
	}
	
	public void groupfunction() {
		System.out.println("groupfunction");
		GroupObj newgroup_obj = new GroupObj();
		for(int i = 0;i<Groupobjlist.size();i++) {
			System.out.println(i);
			BaseObject obj = Groupobjlist.get(i);
			
			//objlist.remove(obj);
			
			
			newgroup_obj.addmember(obj);
			
			
			
			
			
		}
		
		GroupObjS.add(newgroup_obj);
		
		/*
		for(int i = 0;i<Grouplinelist.size();i++) {
			Line line = Grouplinelist.get(i);
			linelist.remove(line);
		}
		*/
		Groupobjlist.clear();
		
	}
	
	public void UnGroup() {
		System.out.println("@remove@ group function");
		for(int i = GroupObjS.size()-1;i>=0;i--) {
			System.out.println(i);
			GroupObj groupobj = GroupObjS.get(i);
			if(groupobj.contains(selectObj)) {
				System.out.println("$$$$$$$$$$");
				
				GroupObjS.remove(groupobj);
				GroupArea = null;
				selectObj = null;
				repaint();
				break;
			}
			
			
		}
	}
	
	public void isInArea() {
		Grouplinelist.clear();
		Groupobjlist.clear();
		int x1 = selectedArea.x;
		int y1 = selectedArea.y;
		int x2 = x1 + selectedArea.width;
		int y2 = y1 + selectedArea.height;
		for(int i = 0;i<objlist.size();i++) {
			BaseObject obj = objlist.get(i);
			//&& x2>obj.getX2() && obj.getY1()>y1 && y2>obj.getY2()
			System.out.println("x1 = "+x1);
			System.out.println("obj.getX1() = "+obj.getX1());
			if(obj.getX1()>x1 && x2>obj.getX2() && obj.getY1()>y1 && y2>obj.getY2()) {
				
				Groupobjlist.add(obj);
			}
		}
		for(int i = 0;i<linelist.size();i++) {
			Line line = linelist.get(i);
			//&& x2>obj.getX2() && obj.getY1()>y1 && y2>obj.getY2()
			if(line.getStart().x>x1 && x2>line.getEnd().x && line.getStart().x>y1 && y2 >line.getEnd().y) {
				Grouplinelist.add(line);
				
			}
		}
	}
	
	public void paint(Graphics g) {
		
		g.setColor(new Color(30, 30, 30));
		g.fillRect(0, 0, getWidth(), getHeight());
		/* set painting color */
		g.setColor(Color.white);
		for(int i = 0;i<objlist.size();i++) {
			BaseObject drawobject = objlist.get(i);
			drawobject.draw(g);
		}
		for(int i = 0;i<linelist.size();i++) {
			Line lineobject = linelist.get(i);
			lineobject.draw(g);
		}
		g.setColor(Color.cyan);
		if(selectObj!=null) {
			selectObj.drawPorts(g);
			selectObj.draw(g);
		}
		else if(selectLine!=null) {
			selectLine.draw(g);
		}
		if(tempLine!=null) {
			tempLine.draw(g);
		}
		
		if(selectedArea!=null) {
			//System.out.println("SelectedAreaaaaaa");
			int x1 = selectedArea.x;
			int y1 = selectedArea.y;
			int x2 = x1 + selectedArea.width;
			int y2 = y1 + selectedArea.height;
			for(int i = 0;i<objlist.size();i++) {
				BaseObject obj = objlist.get(i);
				//&& x2>obj.getX2() && obj.getY1()>y1 && y2>obj.getY2()
				//System.out.println("x1 = "+x1);
				//System.out.println("obj.getX1() = "+obj.getX1());
				if(obj.getX1()>x1 && x2>obj.getX2() && obj.getY1()>y1 && y2>obj.getY2()) {
					if(!Groupobjlist.contains(obj)) {
						System.out.println("add");
						Groupobjlist.add(obj);
					}
					
					
					obj.draw(g);
					obj.drawPorts(g);
				}
			}
			for(int i = 0;i<linelist.size();i++) {
				Line line = linelist.get(i);
				//&& x2>obj.getX2() && obj.getY1()>y1 && y2>obj.getY2()
				int maxX = Math.max(line.getStart().x,line.getEnd().x);
				int maxY = Math.max(line.getStart().y,line.getEnd().y);
				int minX = Math.min(line.getStart().x,line.getEnd().x);
				int minY = Math.min(line.getStart().y,line.getEnd().y);
				if(minX>x1 && x2>maxX && minY>y1 && y2 >maxY) {
					line.draw(g);
					
				}
			}
		}
		
		if(selectedArea!=null) {
			g.setColor(new Color(40,150,200,80));
			g.fillRect(selectedArea.x,selectedArea.y,selectedArea.width,selectedArea.height);
			g.setColor(new Color(40,150,200));
			g.drawRect(selectedArea.x,selectedArea.y,selectedArea.width,selectedArea.height);
		}
		if(GroupArea!=null) {
			System.out.println("yoyo");
			g.setColor(new Color(100,25,25,80));
			g.fillRect(GroupArea.x,GroupArea.y,GroupArea.width,GroupArea.height);
			g.setColor(new Color(100,25,25));
			g.drawRect(GroupArea.x,GroupArea.y,GroupArea.width,GroupArea.height);
		}
		
		
	}
	
	
	
	
	

	
}
