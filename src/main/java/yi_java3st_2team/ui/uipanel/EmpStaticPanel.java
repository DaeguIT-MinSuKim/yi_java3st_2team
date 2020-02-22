package yi_java3st_2team.ui.uipanel;

import yi_java3st_2team.ui.absPanel.AbsCenterStatisticPanel;

public class EmpStaticPanel extends AbsCenterStatisticPanel {

	
	
	public EmpStaticPanel() {
		setLabelInit(lblStat1,lblStat2,lblStat3,lblStat4,lblStat5,lblStat6);
	}
	@Override
	protected String[] getTexts() {
		
		return new String[] {
				"전체 직원 수",
				"부서별 직원 수",
				"직급별 직원 수(등급)",
				"연간 급여 총액 / 월별 급여 총액",
				"1인 평균 급여액",
				"보너스 총액"
				};
	}

}
