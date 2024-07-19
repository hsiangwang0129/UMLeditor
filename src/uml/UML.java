package uml;


import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

public class UML extends JFrame{
	//private Canvas canvas;
	private ToolBar toolbar;
	private Panel panel;
	private MenuBar menubar;
	public UML() {
		panel = Panel.getInstance();
		toolbar = new ToolBar();
		menubar = new MenuBar();
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(toolbar, BorderLayout.WEST);
		getContentPane().add(menubar,BorderLayout.NORTH);
		panel.setBackground(Color.cyan);
		getContentPane().add(panel, BorderLayout.CENTER);
	}
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UML mainWindow = new UML();
		mainWindow.setTitle("UML editor");
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setSize(1200, 700);
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setVisible(true);
	}

}
