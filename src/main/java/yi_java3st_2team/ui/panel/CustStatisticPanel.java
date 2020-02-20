package yi_java3st_2team.ui.panel;

import yi_java3st_2team.ui.absPanel.AbsCenterStatisticPanel;

public class CustStatisticPanel extends AbsCenterStatisticPanel {

	/**
	 * Create the panel.
	 */
	public CustStatisticPanel() {

	}

	@Override
	protected String[] getTexts() {
		return new String[] {"예/적금건수(월별/연도별)", "입/출금 건수(월별/연도별)"};
	}

}
