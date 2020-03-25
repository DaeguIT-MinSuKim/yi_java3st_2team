package yi_java3st_2team.ui.panel.emp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import yi_java3st_2team.ui.absPanel.AbsCenterStatisticWestPanel;

public class EmpStatistic_WestPanel extends AbsCenterStatisticWestPanel implements ActionListener {

	/**
	 * Create the panel.
	 */
	public EmpStatistic_WestPanel() {
		setLabelInit(this.menu1, this.menu2, this.menu3, this.menu4, this.menu5, this.menu6, this.menu7);
		setPanelInit(this.panel1, this.panel2, this.panel3, this.panel4, this.panel5, this.panel6, this.panel7);
	}

	@Override
	protected String[] getTexts() {
		return new String[] {
				"전체 직원 수/ 부서별 직원 비율",
				"급여 총액/ 1인 평균 급여액",
				"보너스 현황",
				"",
				"",
				"",
				""
				//총 고객 숫자/전체 고객 대비 VIP 고객 비율/등급별 고객 숫자
			};
	}

	@Override
	public JPanel[] getPanels() {
		return new JPanel[] {this.panel1,this.panel2,this.panel3};
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
}
