package yi_java3st_2team.ui.panel;

import yi_java3st_2team.dto.Card;
import yi_java3st_2team.ui.absPanel.AbsCenterNorthSearchPanel;

@SuppressWarnings("serial")
public class CardCenterNorthSearchPanel extends AbsCenterNorthSearchPanel<Card> {

	public CardCenterNorthSearchPanel() {
		setText("고객코드 검색");
	}

	@Override
	protected void tfClear() {
		getTfSearch().setText("");
	}

}
