package yi_java3st_2team.ui.panel;

import yi_java3st_2team.ui.absPanel.AbsCenterStatisticPanel;

@SuppressWarnings("serial")
public class BankBookTransHisStatisticPanel extends AbsCenterStatisticPanel {

	/**
	 * Create the panel.
	 */
	public BankBookTransHisStatisticPanel() {
		setLabelInit(lblStat1,lblStat2,lblStat3,lblStat4);
		setLblMouseListener(lblStat1,lblStat2,lblStat3,lblStat4);
	}

	@Override
	protected String[] getTexts() {
		return new String[] {"일간 거래 내역","주간 거래 내역","월간 거래 내역","년간 거래 내역"};
	}

}
