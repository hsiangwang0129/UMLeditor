package uml;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar{
	private Panel panel;
	private JButton holdBtn = null;
	public ToolBar() {
		panel = Panel.getInstance();
		setLayout(new GridLayout(6,1,3,3));
		
		this.setBackground(new Color(0,0,0));
		
		JButton selectBtn = new Btn("select","selected.png", new selectMode());
		add(selectBtn);
		
		JButton associationLine = new Btn("associationLine","assoline.png", new createLineMode("associationLine"));
		add(associationLine);
		
		JButton generalizationLineBtn = new Btn("generalization Line","generline.png", new createLineMode("generalization Line"));
		add(generalizationLineBtn);
		
		JButton compositeBtn = new Btn("composite Line","composite.png", new createLineMode("composite Line"));
		add(compositeBtn);
		
		JButton classObjBtn = new Btn("class","classObj.png", new createObjMode("classObj"));
		add(classObjBtn);
		
		JButton usecaseBtn = new Btn("usecase","usecase1.png", new createObjMode("usecase"));
		add(usecaseBtn);
		
	}
	private class Btn extends JButton{
		Mode ToolMode;
		String Mode;
		public Btn(String ToolName,String filename, Mode ToolMode) {
			this.ToolMode = ToolMode;
			this.Mode = ToolName;
			try {
				 Image img = ImageIO.read(getClass().getResource(filename));
				 setIcon(new ImageIcon(img));
				} catch (Exception ex) {
				 System.out.println(ex);
				 System.out.println("haha");
				}
			setBackground(new Color(60,60,60));
			setToolTipText(ToolName);
			addActionListener(new BtnListener());
			
			
			
		}
		class BtnListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panel.selectedArea = null;
				panel.selectLine = null;
				panel.selectObj = null;
				if(holdBtn != null)
					holdBtn.setBackground(new Color(60,60,60));
				holdBtn = (JButton) e.getSource();
				holdBtn.setBackground(Color.PINK);
				panel.Mode = Mode;
				panel.currentMode = ToolMode;
				panel.updateMode();
				panel.repaint();
			}
			
		}
		
	}
	
}
