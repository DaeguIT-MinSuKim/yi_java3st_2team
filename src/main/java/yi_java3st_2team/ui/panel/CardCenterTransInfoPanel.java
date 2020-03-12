package yi_java3st_2team.ui.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import yi_java3st_2team.ui.absPanel.AbsCenterStatisticPanel;
import yi_java3st_2team.ui.chart.JFrameBarChartForCardDaily;
import yi_java3st_2team.ui.chart.JFrameBarChartForCardMonthly;
import yi_java3st_2team.ui.chart.JFrameBarChartForCardWeekly;
import yi_java3st_2team.ui.chart.JFrameBarChartForCardYearly;

import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class CardCenterTransInfoPanel extends AbsCenterStatisticPanel implements ActionListener {
	public CardCenterTransInfoPanel() {
		initialize();
	}
	private void initialize() {
		btnSearch.addActionListener(this);
		setLabelInit(lblStat1,lblStat2,lblStat3,lblStat4);
		setLblMouseListener(lblStat1,lblStat2,lblStat3,lblStat4);
	}

	@Override
	protected String[] getTexts() {
		return new String[] {"일간 거래 내역","주간 거래 내역","월간 거래 내역","연간 거래 내역"};
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSearch) {
			thisBtnSearchActionPerformed(e);
		}
	}
	protected void thisBtnSearchActionPerformed(ActionEvent e) {
		for(Component c : pCenter.getComponents()) {
			JLabel label = (JLabel)c;
			if(label.getForeground().equals(new Color(254,208,64))) {
				switch(label.getText()) {
				case "일간 거래 내역":
					JFrameBarChartForCardDaily.initAndShowGUI();
					break;
				case "주간 거래 내역":
					JFrameBarChartForCardWeekly.initAndShowGUI();
					break;
				case "월간 거래 내역":
					JFrameBarChartForCardMonthly.initAndShowGUI();
					break;
				case "연간 거래 내역":
					JFrameBarChartForCardYearly.initAndShowGUI();
					break;
				}
			}
		}
	}
}
