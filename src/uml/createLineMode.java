package uml;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.List;



public class createLineMode extends Mode{
	
	private String lineType = null;
	private Point startPos  = null;
	private int portIndex1 = -1, portIndex2 = -1;
	private List<BaseObject> objlist;
	private BaseObject object1,object2 = null;
	public createLineMode(String lineType) {
		this.lineType = lineType;
	}
	
	public void mousePressed(MouseEvent e) {
		objlist = panel.getObjects();
		
		startPos = findConnectObj(e.getPoint(),"start");
	}
	//associationLine Line
	public void mouseDragged(MouseEvent e) {
		/* show dragged line */
		if (startPos != null) {
			if(lineType.equals("generalization Line")) {
				Line newline = new generalizationLine(startPos.x,e.getX(),startPos.y,e.getY());
				panel.tempLine = newline;
				panel.repaint();
			}
			else if(lineType.equals("associationLine")) {
				Line newline = new AssociationLine(startPos.x,e.getX(),startPos.y,e.getY());
				panel.tempLine = newline;
				panel.repaint();
			}
			else if(lineType.equals("composite Line")) {
				Line newline = new CompositeLine(startPos.x,e.getX(),startPos.y,e.getY());
				panel.tempLine = newline;
				panel.repaint();
			}
			
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		Point endPos = null;
		if(startPos != null) {
			endPos = findConnectObj(e.getPoint(),"end");
			
			if(endPos!=null && object1 != object2) {
				if(lineType.equals("generalization Line")) {
					panel.tempLine = null;
					Line newline = new generalizationLine(startPos.x,endPos.x,startPos.y,endPos.y);
					panel.getLines().add(newline);
					
					newline.setPorts(object1.getPort(portIndex1), object2.getPort(portIndex2));
					
					object1.getPort(portIndex1).addLine(newline);
					object2.getPort(portIndex2).addLine(newline);
					
				}
				else if(lineType.equals("associationLine")) {
					Line newline = new AssociationLine(startPos.x,endPos.x,startPos.y,endPos.y);
					panel.getLines().add(newline);
					
					newline.setPorts(object1.getPort(portIndex1), object2.getPort(portIndex2));
					
					object1.getPort(portIndex1).addLine(newline);
					object2.getPort(portIndex2).addLine(newline);
				}
				else if(lineType.equals("composite Line")) {
					Line newline = new CompositeLine(startPos.x,endPos.x,startPos.y,endPos.y);
					panel.getLines().add(newline);
					
					newline.setPorts(object1.getPort(portIndex1), object2.getPort(portIndex2));
					
					object1.getPort(portIndex1).addLine(newline);
					object2.getPort(portIndex2).addLine(newline);
				}
				
				
			}
			
		}
		startPos = null;
		panel.tempLine = null;
		panel.repaint();
		
	}
	
	
	public Point findConnectObj(Point p,String target) {
		
		for(int i = 0;i<objlist.size();i++) {
			int portIndex=-1;
			BaseObject baseobject = objlist.get(i);
			
			if(baseobject.isInPort(p)!=-1) {
				portIndex = baseobject.isInPort(p);
			
		
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
