package uml;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class PortClass{
	public int x,y,width;
	
	
	private List<Line> lines = new ArrayList<Line>();
	public PortClass(int x, int y, int width){
		this.x = x;
		this.y = y;
		this.width = width;
		
	}
	public List<Line> getlines() {
		return lines;
	}
	
	public void resetPos(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	
	
	public Point getcenter() {
		return new Point(x+width/2,y+width/2);
	}
	
	public void addLine(Line line) {
		lines.add(line);
	}
	
	public void resetLines() {
		for(int i = 0;i<lines.size();i++) {
			Line line = lines.get(i);
			line.resetPos();
		}
	}
}