package yi_java3st_2team.ui.panel;

import yi_java3st_2team.ui.absPanel.AbsCenterStatisticPanel;

@SuppressWarnings("serial")
public class CardCenterStatisticPanel extends AbsCenterStatisticPanel {
	/**
	 * Create the panel.
	 */
	public CardCenterStatisticPanel() {
		setLabelInit(lblStat1);
		setLblMouseListener(lblStat1);
	}

	@Override
	protected String[] getTexts() {
		return new String[] {"고객별 카드 상품 조회"};
	}

}
