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
abstract public class AbsCenterStatisticWestPanel_test extends JPanel {
	protected JPanel west_panel;
	protected JPanel panel;
	protected JPanel panel_1;
	protected JPanel panel_2;
	protected JPanel panel_3;
	protected JLabel menu1;
	protected JLabel menu2;
	protected JLabel menu3;
	protected JLabel menu4;
	protected JPanel panel_4;
	protected JLabel menu5;
	protected JPanel panel_5;
	protected JLabel menu6;
	protected JPanel panel_6;
	protected JLabel menu7;

	public AbsCenterStatisticWestPanel_test() {
		initialize();
	}
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		
		
		west_panel = new JPanel();
		add(west_panel, BorderLayout.CENTER);
		west_panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		panel = new JPanel();
		west_panel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		menu1 = new JLabel("");
		panel.add(menu1);
		
		panel_1 = new JPanel();
		west_panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		menu2 = new JLabel("");
		panel_1.add(menu2);
		
		panel_2 = new JPanel();
		west_panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		menu3 = new JLabel("");
		panel_2.add(menu3);
		
		panel_3 = new JPanel();
		west_panel.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		menu4 = new JLabel("");
		panel_3.add(menu4);
		
		panel_4 = new JPanel();
		west_panel.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		menu5 = new JLabel("");
		panel_4.add(menu5);
		
		panel_5 = new JPanel();
		west_panel.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		menu6 = new JLabel("");
		panel_5.add(menu6);
		
		panel_6 = new JPanel();
		west_panel.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		menu7 = new JLabel("");
		panel_6.add(menu7);
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
			menu[0].setBackground(new Color(254,208,64));
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
	
	
	public JPanel[] getPanels(JPanel...panel) {
		JPanel[] panels = {this.panel, this.panel_1, this.panel_2, this.panel_3, this.panel_4, this.panel_5, this.panel_6};
		return panels;
	}
	
	
	abstract protected String[] getTexts();
}
