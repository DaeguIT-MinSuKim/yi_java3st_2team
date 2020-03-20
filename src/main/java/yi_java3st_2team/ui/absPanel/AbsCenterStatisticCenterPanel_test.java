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
abstract public class AbsCenterStatisticCenterPanel_test extends JPanel {
	protected JPanel center_panel;
	protected JPanel panel_4;
	protected JPanel panel_5;
	protected JPanel panel_6;
	protected JPanel btn_panel;

	public AbsCenterStatisticCenterPanel_test() {
		initialize();
	}
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
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


}
