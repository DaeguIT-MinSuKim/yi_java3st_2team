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
abstract public class AbsCenterStatisticPanel_test extends JPanel {
	protected JPanel west_panel;
	protected JPanel panel;
	protected JPanel panel_1;
	protected JPanel panel_2;
	protected JPanel panel_3;
	protected JLabel menu1;
	protected JLabel menu2;
	protected JLabel menu3;
	protected JLabel menu4;
	protected JPanel center_panel;
	protected JPanel panel_4;
	protected JPanel panel_5;
	protected JPanel panel_6;
	protected JPanel btn_panel;
	protected JPanel panel_7;
	protected JLabel menu5;

	public AbsCenterStatisticPanel_test() {
		initialize();
	}
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		
		
		west_panel = new JPanel();
		add(west_panel, BorderLayout.WEST);
		west_panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		panel = new JPanel();
		west_panel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		menu1 = new JLabel("New label");
		panel.add(menu1);
		
		panel_1 = new JPanel();
		west_panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		menu2 = new JLabel("New label");
		panel_1.add(menu2);
		
		panel_2 = new JPanel();
		west_panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		menu3 = new JLabel("New label");
		panel_2.add(menu3);
		
		panel_3 = new JPanel();
		west_panel.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		menu4 = new JLabel("New label");
		panel_3.add(menu4);
		
		panel_7 = new JPanel();
		west_panel.add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		menu5 = new JLabel("");
		panel_7.add(menu5);
		
		center_panel = new JPanel();
		add(center_panel, BorderLayout.CENTER);
		center_panel.setLayout(new BorderLayout(0, 0));
		
		panel_4 = new JPanel();
		center_panel.add(panel_4, BorderLayout.NORTH);
		panel_4.setBorder(new EmptyBorder(10,0,10,0));
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		panel_6 = new JPanel();
		panel_4.add(panel_6);
		
		btn_panel = new JPanel();
		panel_4.add(btn_panel);
		
		
		panel_5 = new JPanel();
		center_panel.add(panel_5, BorderLayout.CENTER);
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
				selMenu.setBackground(new Color(254,208,64));
			}
		};
		for(int i=0;i<menus.length;i++) {
			menus[i].addMouseListener(myLblListener);
		}
	}
	
	
	//버튼 세팅
	protected void setBtnInit(JButton...button) {
		for(int i=0; i<button.length; i++) {
			button[i].setPreferredSize(new Dimension(110,30));
			btn_panel.add(button[i]);
		}
	}
	
	public void initFX(InitScene fxPanel) {
		Scene scene = fxPanel.createScene();
		JFXPanel panel = (JFXPanel) fxPanel;
		panel.setScene(scene);
	}

	
	
	abstract protected String[] getTexts();
}
