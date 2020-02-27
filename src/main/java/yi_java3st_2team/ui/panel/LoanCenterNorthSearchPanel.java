package yi_java3st_2team.ui.panel;

import yi_java3st_2team.dto.BankBook;
import yi_java3st_2team.ui.absPanel.AbsCenterNorthSearchPanel;

@SuppressWarnings("serial")
public class LoanCenterNorthSearchPanel extends AbsCenterNorthSearchPanel<BankBook> {

	/**
	 * Create the panel.
	 */
	public LoanCenterNorthSearchPanel() {
		setText("고객 이름 검색");
	}

	@Override
	protected void tfClear() {
		getTfSearch().setText("");	
	}

}
