package yi_java3st_2team.ui.panel;

import yi_java3st_2team.ui.absPanel.AbsCenterStatisticPanel;
import yi_java3st_2team.ui.chart.JFrameBarChartByLoanPlan;

import java.awt.event.ActionListener;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class LoanInfoStatisticPanel extends AbsCenterStatisticPanel implements ActionListener {

	/**
	 * Create the panel.
	 */
	public LoanInfoStatisticPanel() {
		initialize();
	}
	private void initialize() {
		btnSearch.addActionListener(this);
		setLabelInit(lblStat1);
		setLblMouseListener(lblStat1);
	}

	@Override
	protected String[] getTexts() {
		return new String[] {"고객별 대출 상품"};
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
				JFrameBarChartByLoanPlan.initAndShowGUI();
			}
		}
	}
}
