package uml;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.w3c.dom.Text;





public class MenuBar extends JMenuBar{
	private Panel panel;
	public MenuBar() {
		panel = Panel.getInstance();
		
		JMenu menu;
		JMenu menu2;
		JMenuItem menuitem;
		
		menu = new JMenu("File");
		add(menu);
		
		menu = new JMenu("Edit");
		add(menu);
		
		menuitem = new JMenuItem("Change object name");
		menu.add(menuitem);
		menuitem.addActionListener(new ChangeNameListener());
		
		menuitem = new JMenuItem("Group");
		menu.add(menuitem);
		menuitem.addActionListener(new GroupObjectListener());
		
		menuitem = new JMenuItem("UnGroup");
		menu.add(menuitem);
		menuitem.addActionListener(new UnGroupObjectListener());
	}
	
	
	public void changeName() {
        JFrame inputTextFrame = new JFrame("Change Object Name");
        inputTextFrame.setSize(400, 200);
        inputTextFrame.getContentPane().setBackground(Color.lightGray); // 更改背景顏色
        inputTextFrame.getContentPane().setLayout(new GridLayout(0, 1));

        JPanel jpanel = new JPanel();
        jpanel.setBackground(Color.lightGray); // 更改面板顏色
        jpanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // 更改布局

        JTextField textField = new JTextField("Object Name", 20);
        textField.setBackground(Color.white); // 更改文本框背景顏色
        jpanel.add(textField);

        JButton confirm = new JButton("OK");
        confirm.setBackground(new Color(50,155,255)); // 更改按鈕背景顏色
        jpanel.add(confirm);

        JButton cancel = new JButton("Cancel");
        cancel.setBackground(new Color(255,80,50)); // 更改按鈕背景顏色
        jpanel.add(cancel);

        inputTextFrame.getContentPane().add(jpanel);

        inputTextFrame.setLocationRelativeTo(null);
        inputTextFrame.setVisible(true);

        confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	panel.changeObjName(textField.getText());
                inputTextFrame.dispose();
            }
        });

        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inputTextFrame.dispose();
            }
        });
    }
	
	
	class ChangeNameListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			changeName();
		}
	}
	class GroupObjectListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//canvas.addGroup();
			panel.groupfunction();
		}
	}
	
	class UnGroupObjectListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			panel.UnGroup();
		}
	}
}
