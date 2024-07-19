package uml;

import java.awt.Point;
import java.awt.event.MouseEvent;

public class createObjMode extends Mode{
	private String objType = null;
	
	public createObjMode(String Type) {
		this.objType = Type;
	}
	
	public void mousePressed(MouseEvent e) {
		if (objType.equals("usecase")){
			BaseObject newObj = new usecaseObj(e.getX(),e.getY());
			panel.add(newObj);
			panel.repaint();
		}
		else if (objType.equals("classObj")){
			BaseObject newObj = new classObj(e.getX(),e.getY());
			panel.add(newObj);
			panel.repaint();
		}
	}
	
	
	
}
