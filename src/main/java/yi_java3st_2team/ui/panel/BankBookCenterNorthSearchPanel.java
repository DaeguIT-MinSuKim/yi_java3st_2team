package yi_java3st_2team.ui.panel;

import yi_java3st_2team.dto.BankBook;
import yi_java3st_2team.ui.absPanel.AbsCenterNorthSearchPanel;

@SuppressWarnings("serial")
public class BankBookCenterNorthSearchPanel extends AbsCenterNorthSearchPanel<BankBook> {

	@Override
	public String[] setSearchList() {
		return new String[] {"계좌번호","고객이름","상품명","예금","적금","마이너스"};
	}

	@Override
	protected void tfClear() {
		getCmbSearchList().setSelectedIndex(-1);
	}

}
