package yi_java3st_2team.ui.panel;

import yi_java3st_2team.ui.absPanel.AbsWestMenuPanel;

@SuppressWarnings("serial")
public class CardWestMenuPanel extends AbsWestMenuPanel {

	public CardWestMenuPanel() {
		setPanelInit(pMenu1,pMenu2,pMenu3,pMenu4,pMenu5);
		setLabelInit(lblMenu1,lblMenu2,lblMenu3,lblMenu4,lblMenu5);
	}
	@Override
	protected String[] getTexts() {
		return new String[] {"고객 개인 정보","고객 통계 조회","고객 상품관리","입출금 관리","은행 업무 관리"};
	}

}
