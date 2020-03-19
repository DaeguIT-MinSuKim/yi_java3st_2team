package yi_java3st_2team.ui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javafx.application.Platform;
import yi_java3st_2team.ui.absPanel.AbsCenterStatisticPanel_test;
import yi_java3st_2team.ui.chart.PanelPieChart;

import javax.swing.JButton;

public class CustStatistic_panels extends AbsCenterStatisticPanel_test implements ActionListener{
	private JButton btnNewButton;

	/**
	 * Create the panel.
	 */
	public CustStatistic_panels() {

		initialize();
	}
	private void initialize() {
		this.setPanelInit(this.panel, this.panel_1, this.panel_2, this.panel_3, this.panel_7);
		this.setLblMouseListener(this.panel, this.panel_1, this.panel_2, this.panel_3, this.panel_7);
		this.setLabelInit(this.menu1, this.menu2, this.menu3, this.menu4);
		btnNewButton = new JButton("예금");
		btnNewButton.addActionListener(this);
		btn_panel.add(btnNewButton);
		JButton btnNewButton_1 = new JButton("New button");
		btn_panel.add(btnNewButton_1);
		
		this.setBtnInit(btnNewButton,btnNewButton_1);
	}

	@Override
	protected String[] getTexts() {
		return new String[] {
				"예/적금건수(월별)",
				"입/출금 건수(월별)",
				"예금/적금/대출 총 금액",
				"일반고객/VIP고객",
				""
				//총 고객 숫자/전체 고객 대비 VIP 고객 비율/등급별 고객 숫자
			};
	}
	
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


	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewButton) {
			btnNewButtonActionPerformed(e);
		}
	}
	protected void btnNewButtonActionPerformed(ActionEvent e) {
		PanelPieChart panel_fx = new PanelPieChart();
		panel_5.add(panel_fx);
		Platform.runLater(() -> initFX(panel_fx));
		center_panel.revalidate();
		center_panel.repaint();
	}
}
