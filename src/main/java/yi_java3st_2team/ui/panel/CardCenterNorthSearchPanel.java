package yi_java3st_2team.ui.panel;

import yi_java3st_2team.dto.Card;
import yi_java3st_2team.ui.absPanel.AbsCenterNorthSearchPanel;

@SuppressWarnings("serial")
public class CardCenterNorthSearchPanel extends AbsCenterNorthSearchPanel<Card> {


	@Override
	protected void tfClear() {
		getCmbSearchList().setSelectedIndex(-1);
	}

	@Override
	public String[] setSearchList() {
		// TODO Auto-generated method stub
		return null;
	}

}
