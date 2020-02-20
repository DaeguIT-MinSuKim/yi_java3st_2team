package yi_java3st_2team.ui.panel;

import yi_java3st_2team.ui.absPanel.AbsCenterStatisticPanel;

public class CustStatisticPanel extends AbsCenterStatisticPanel {

	public CustStatisticPanel() {
		this.setLabelInit(this.lblStat1, this.lblStat2, this.lblStat3, this.lblStat4, this.lblStat5, this.lblStat6, this.lblStat7, this.lblStat8);
	}

	@Override
	protected String[] getTexts() {
		return new String[] {
				"예/적금건수(월별/연도별)",
				"입/출금 건수(월별/연도별)",
				"예금 총 금액",
				"대출 총 금액",
				"적금 총 금액",
				"총 고객 숫자",
				"전체 고객 대비 VIP 고객 비율",
				"등급별 고객 숫자"
			};
		
	}

}
