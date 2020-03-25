package yi_java3st_2team.ui.absPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import yi_java3st_2team.ui.chart.InitScene;

import java.awt.Dimension;

@SuppressWarnings("serial")
abstract public class AbsCenterStatisticWestPanel extends JPanel {
	protected JPanel west_panel;
	protected JPanel panel1;
	protected JPanel panel2;
	protected JPanel panel3;
	protected JPanel panel4;
	protected JLabel menu1;
	protected JLabel menu2;
	protected JLabel menu3;
	protected JLabel menu4;
	protected JPanel panel5;
	protected JLabel menu5;
	protected JPanel panel6;
	protected JLabel menu6;
	protected JPanel panel7;
	protected JLabel menu7;

	public AbsCenterStatisticWestPanel() {
		initialize();
	}
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		
		
		west_panel = new JPanel();
		add(west_panel, BorderLayout.CENTER);
		west_panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		panel1 = new JPanel();
		west_panel.add(panel1);
		panel1.setLayout(new BorderLayout(0, 0));
		
		menu1 = new JLabel("");
		panel1.add(menu1);
		
		panel2 = new JPanel();
		west_panel.add(panel2);
		panel2.setLayout(new BorderLayout(0, 0));
		
		menu2 = new JLabel("");
		panel2.add(menu2);
		
		panel3 = new JPanel();
		west_panel.add(panel3);
		panel3.setLayout(new BorderLayout(0, 0));
		
		menu3 = new JLabel("");
		panel3.add(menu3);
		
		panel4 = new JPanel();
		west_panel.add(panel4);
		panel4.setLayout(new BorderLayout(0, 0));
		
		menu4 = new JLabel("");
		panel4.add(menu4);
		
		panel5 = new JPanel();
		west_panel.add(panel5);
		panel5.setLayout(new BorderLayout(0, 0));
		
		menu5 = new JLabel("");
		panel5.add(menu5);
		
		panel6 = new JPanel();
		west_panel.add(panel6);
		panel6.setLayout(new BorderLayout(0, 0));
		
		menu6 = new JLabel("");
		panel6.add(menu6);
		
		panel7 = new JPanel();
		west_panel.add(panel7);
		panel7.setLayout(new BorderLayout(0, 0));
		
		menu7 = new JLabel("");
		panel7.add(menu7);
			}
	
	//메뉴 텍스트 세팅
	protected void setLabelInit(JLabel ... menu) {
		String[] texts = getTexts();
		for(int i=0;i<menu.length;i++) {
			menu[i].setFont(new Font("맑은 고딕",Font.PLAIN,15));
			menu[i].setHorizontalAlignment(JLabel.CENTER);
			menu[i].setBorder(new EmptyBorder(0,10,0,10));
			menu[i].setText(texts[i]);
			
		}
	}
	
	//메뉴 패널 세팅 
	protected void setPanelInit(JPanel ... menu) {
		for(int i=0;i<menu.length;i++) {
		     menu[i].setBorder(new EmptyBorder(20,20,20,20));
			 menu[i].setBackground(new Color(255,255,255));
		}
	}
	
	//메뉴 마우스 리스너
	protected void setLblMouseListener(JPanel... menus) {
		MouseListener myLblListener = new MouseAdapter() {
			@Override 
			public void mouseClicked(MouseEvent e) {
				for(int i=0; i<menus.length; i++) {
					menus[i].setBackground(new Color(255,255,255));
				}
				JPanel selMenu = (JPanel)e.getSource();
				JLabel selPanel = (JLabel) selMenu.getComponent(0);
				String text = selPanel.getText();
				if(text.equals("")) {
					selMenu.setBackground(new Color(255,255,255));
					return;
				}
				selMenu.setBackground(new Color(254,208,64));
			}
		};
		for(int i=0;i<menus.length;i++) {
			menus[i].addMouseListener(myLblListener);
		}
	}
	
	abstract protected String[] getTexts();
	
	abstract public JPanel[] getPanels();
}
