package yi_java3st_2team.ui.panel;

import yi_java3st_2team.dto.Card;
import yi_java3st_2team.ui.absPanel.AbsCenterNorthSearchPanel;

@SuppressWarnings("serial")
public class CardCenterNorthSearchPanel extends AbsCenterNorthSearchPanel<Card> {


	@Override
	protected void tfClear() {
		getTfSearch().setText("");
	}

	@Override
	public String[] setSearchList() {
		// TODO Auto-generated method stub
		return new String[] {"검색구분","고객이름","상품명","카드구분"};
	}

}
