package yi_java3st_2team.ui.panel;

import yi_java3st_2team.ui.absPanel.AbsCenterStatisticPanel;

@SuppressWarnings("serial")
public class LoanInfoStatisticPanel extends AbsCenterStatisticPanel {

	/**
	 * Create the panel.
	 */
	public LoanInfoStatisticPanel() {
		setLabelInit(lblStat1,lblStat2);
		setLblMouseListener(lblStat1,lblStat2);
	}

	@Override
	protected String[] getTexts() {
		return new String[] {"대출 잔액 조회","고객별 대출 상품 현황"};
	}

}
