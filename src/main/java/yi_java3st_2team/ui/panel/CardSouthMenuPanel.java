package yi_java3st_2team.ui.panel;

import yi_java3st_2team.ui.absPanel.AbsSouthMenuPanel;

@SuppressWarnings("serial")
public class CardSouthMenuPanel extends AbsSouthMenuPanel {

	/**
	 * Create the panel.
	 */
	public CardSouthMenuPanel() {
		setPanelInit(pMenu1,pMenu2);
		setLabelInit(lblMenu1,lblMenu2);
	}

	@Override
	protected String[] getTexts() {
		return new String[] {"카드 검색","카드 조회"};
	}

}
