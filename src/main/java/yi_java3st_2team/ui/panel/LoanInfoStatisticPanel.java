package yi_java3st_2team.ui.panel;

import yi_java3st_2team.ui.absPanel.AbsCenterStatisticPanel;

@SuppressWarnings("serial")
public class LoanInfoStatisticPanel extends AbsCenterStatisticPanel {

	/**
	 * Create the panel.
	 */
	public LoanInfoStatisticPanel() {
		setLabelInit(lblStat1,lblStat2,lblStat3,lblStat4);
		setLblMouseListener(lblStat1,lblStat2,lblStat3,lblStat4);
	}

	@Override
	protected String[] getTexts() {
		return new String[] {"대출 상환 내역","남은 상환 일자","대출상품추천"};
	}

}
