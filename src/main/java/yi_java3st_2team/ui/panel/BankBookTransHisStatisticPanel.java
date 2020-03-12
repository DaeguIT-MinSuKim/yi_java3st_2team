package yi_java3st_2team.ui.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import yi_java3st_2team.ui.absPanel.AbsCenterStatisticPanel;
import yi_java3st_2team.ui.chart.JFrameBarChartForBankBookDaily;
import yi_java3st_2team.ui.chart.JFrameBarChartForBankBookMonthly;
import yi_java3st_2team.ui.chart.JFrameBarChartForBankBookWeekly;
import yi_java3st_2team.ui.chart.JFrameBarChartForBankBookYearly;

@SuppressWarnings("serial")
public class BankBookTransHisStatisticPanel extends AbsCenterStatisticPanel implements ActionListener {

	/**
	 * Create the panel.
	 */
	public BankBookTransHisStatisticPanel() {
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
	protected void thisBtnSearchActionPerformed(ActionEvent e) {
		for(Component c : pCenter.getComponents()) {
			JLabel label = (JLabel)c;
			if(label.getForeground().equals(new Color(254,208,64))) {
				switch(label.getText()) {
				case "일간 거래 내역":
					JOptionPane.showMessageDialog(null, label.getText());
					JFrameBarChartForBankBookDaily.initAndShowGUI();
					break;
				case "주간 거래 내역":
					JOptionPane.showMessageDialog(null, label.getText());
					JFrameBarChartForBankBookWeekly.initAndShowGUI();
					break;
				case "월간 거래 내역":
					JOptionPane.showMessageDialog(null, label.getText());
					JFrameBarChartForBankBookMonthly.initAndShowGUI();
					break;
				case "연간 거래 내역":
					JOptionPane.showMessageDialog(null, label.getText());
					JFrameBarChartForBankBookYearly.initAndShowGUI();
					break;
				}
			}
		}
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSearch) {
			thisBtnSearchActionPerformed(e);
		}
	}
}
