package yi_java3st_2team.ui.panel.bankbook;

import yi_java3st_2team.dto.BankBook;
import yi_java3st_2team.ui.absPanel.AbsCenterNorthSearchPanel;

@SuppressWarnings("serial")
public class LoanCenterNorthSearchPanel extends AbsCenterNorthSearchPanel<BankBook> {

	@Override
	protected void tfClear() {
		getTfSearch().setText("");
	}

	@Override
	public String[] setSearchList() {
		return new String[] {"검색구분","계좌번호","고객이름","상품명","대출구분"};
	}

}
