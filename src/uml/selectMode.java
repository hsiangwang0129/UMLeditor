package uml;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.List;

public class selectMode extends Mode{
	private BaseObject object1,object2 = null;
	private int portIndex1 = -1, portIndex2 = -1;
	Point endPos = null;
	Point startPos = null;
	String LineInsideFlag = null;
	Point mousePos = null;
	List<BaseObject> objlist ;
	List<Line> linelist;
	List<GroupObj> GroupObjS ;
	GroupObj selectedGroup;
	List<BaseObject> objingroups ;
	public void mousePressed(MouseEvent e) {
		panel.selectedArea = null;
		selectedGroup = null;
		panel.selectObj = null;
		panel.selectLine = null;
		mousePos = e.getPoint();
		objlist = panel.getObjects();
		linelist = panel.getLines();
		GroupObjS = panel.getGroupobj();
		panel.GroupArea = null;
		panel.clear();
		
		
		
		
		for(int i = 0;i<GroupObjS.size();i++) {
			System.out.println("check select group "+ i);
			GroupObj groupobj = GroupObjS.get(i);
			if (groupobj.isInside(mousePos)){
				selectedGroup = groupobj;
				panel.GroupArea = groupobj.show();
			}
			
		}
		for(int i = 0;i<objlist.size();i++) {
			BaseObject selectobj = objlist.get(i);
			if(selectobj.isInside(e.getPoint())) {
				panel.selectObj=selectobj;
				break;
			}
			
		}
		for(int i = 0;i<linelist.size();i++) {
			System.out.println("i = "+i);
			System.out.println("detect select line");
			Line selectLine = linelist.get(i);
			LineInsideFlag = selectLine.isInside(e.getPoint());
			if(LineInsideFlag != null) {
				panel.selectLine=selectLine;
				break;
			}
			
		}
		panel.repaint();
		
	}
	public void mouseReleased(MouseEvent e) {
		if(panel.selectLine!=null) {
			System.out.println("Start release");
			endPos = panel.selectLine.getEnd();
			startPos = panel.selectLine.getStart();
			if(panel.selectLine.selectPos.equals("start")) {
				
				startPos = findConnectObj(e.getPoint(),panel.selectLine.selectPos);
				if(startPos!=null) {
					PortClass[] ports = panel.selectLine.getPorts();
					PortClass port1 = ports[0];
					PortClass port2 = ports[1];
				
					
					List<Line> lines = port1.getlines();
					lines.remove(panel.selectLine);
					panel.selectLine.setPort1(object1.getPort(portIndex1));
					
					object1.getPort(portIndex1).addLine(panel.selectLine);
					panel.selectLine.resetLine(panel.selectLine.getPorts()[0].getcenter());
					panel.repaint();
				}
				else {
					panel.selectLine.resetLine(panel.selectLine.getPorts()[0].getcenter());
					panel.repaint();
				}
				
					
					
			}
			else if(panel.selectLine.selectPos.equals("end")) {
				
				endPos = findConnectObj(e.getPoint(),panel.selectLine.selectPos);
				if(endPos!=null) {
					PortClass[] ports = panel.selectLine.getPorts();
					PortClass port1 = ports[0];
					PortClass port2 = ports[1];
				
					
					List<Line> lines = port2.getlines();
					lines.remove(panel.selectLine);
					panel.selectLine.setPort2(object2.getPort(portIndex2));
					
					object2.getPort(portIndex2).addLine(panel.selectLine);
					panel.selectLine.resetLine(panel.selectLine.getPorts()[1].getcenter());
					panel.repaint();
				}
				else {
					panel.selectLine.resetLine(panel.selectLine.getPorts()[1].getcenter());
					panel.repaint();
				}
				
					
					
			}
		}

		startPos = null;
		panel.tempLine = null;
		panel.repaint();
	}
	
	public void mouseDragged(MouseEvent e) {
		int deltaX = e.getX()-mousePos.x;
		int deltaY = e.getY()-mousePos.y;
		if(selectedGroup != null) {
			System.out.println("group drag");
			selectedGroup.resetAllPos(deltaX,deltaY);
			panel.GroupArea = selectedGroup.show();
			mousePos.x = e.getX();
			mousePos.y = e.getY();
			panel.repaint();
			
		}
		else if(panel.selectObj != null) {
			System.out.println("obj drag");
			panel.selectObj.updatePos(e.getPoint());
			panel.selectObj.updatePorts();
			panel.repaint();
		}
		else if(panel.selectLine != null) {
			panel.selectLine.resetLine(e.getPoint());
			
			
			
			
			
			panel.repaint();
		}
		else {
			if(e.getX()>mousePos.getX() && e.getY()>mousePos.getY()) {
				panel.selectedArea = new Rectangle((int)mousePos.getX(),(int)mousePos.getY(),(int)Math.abs(e.getX()-mousePos.getX()),(int)Math.abs(e.getY()-mousePos.getY()));
			}
			else if(e.getX()<mousePos.getX() && e.getY()<mousePos.getY()) {
				panel.selectedArea = new Rectangle((int)e.getX(),(int)e.getY(),(int)Math.abs(e.getX()-mousePos.getX()),(int)Math.abs(e.getY()-mousePos.getY()));
			}
			else if(e.getX()>mousePos.getX() && e.getY()<mousePos.getY()) {
				panel.selectedArea = new Rectangle((int)mousePos.getX(),(int)e.getY(),(int)Math.abs(e.getX()-mousePos.getX()),(int)Math.abs(e.getY()-mousePos.getY()));
			}
			else if(e.getX()<mousePos.getX() && e.getY()>mousePos.getY()) {
				panel.selectedArea = new Rectangle((int)e.getX(),(int)mousePos.getY(),(int)Math.abs(e.getX()-mousePos.getX()),(int)Math.abs(e.getY()-mousePos.getY()));
			}
			panel.repaint();
		}
		
	}
	
public Point findConnectObj(Point p,String target) {
		
		for(int i = 0;i<objlist.size();i++) {
			int portIndex=-1;
			BaseObject baseobject = objlist.get(i);
			
			if(baseobject.isInPort(p)!=-1) {
				portIndex = baseobject.isInPort(p);
				System.out.println("inport release!!!!");
		
				switch (target) {
				case "start":
					object1 = baseobject;
					portIndex1 = portIndex;
					break;
				case "end":
					object2 = baseobject;
					portIndex2 = portIndex;
					break;
				}
				Point portPos = baseobject.getPort(portIndex).getcenter();
				return portPos;
			}
		
		}
		return null;
	}	
	
}
