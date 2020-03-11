package yi_java3st_2team.ui.uipanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import yi_java3st_2team.ui.absPanel.AbsCenterStatisticPanel;

public class EmpStaticPanel extends AbsCenterStatisticPanel implements ActionListener {

	private static final EmpStaticPanel empStaticPanel = new EmpStaticPanel();

	
	
	
	public static EmpStaticPanel getEmpstaticpanel() {
		return empStaticPanel;
	}
	
	
	
   // 전체직원수, 부서별 직원 수 
	private DlgStatisticCountEmp empPieCount;
   // 직급별 직원 수 

	private DlgStatisticCountEmpBonus empBarCount;
	

	public EmpStaticPanel() {

		initialize();
	}

	private void initialize() {
		btnSearch.addActionListener(this);
		this.setLabelInit(this.lblStat1, this.lblStat2, this.lblStat3, this.lblStat4);
		setLblMouseListener(lblStat1, lblStat2, lblStat3, lblStat4);
	}

	@Override
	protected String[] getTexts() {

		return new String[] { "전체 직원 수/ 부서별 직원 비율", "연간 급여 총액 / 월별 급여 총액", "1인 평균 급여액", "보너스 현황" };
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSearch) {
			thisBtnSearchActionPerformed(e);
		}

	}

	protected void thisBtnSearchActionPerformed(ActionEvent e) {
		for (Component chart : pCenter.getComponents()) {

			JLabel label = (JLabel) chart;
			if (label.getForeground().equals(new Color(254, 208, 64))
					&& label.getText().contentEquals("전체 직원 수/ 부서별 직원 비율")) {
				empPieCount = new DlgStatisticCountEmp();

				empPieCount.setTitle(label.getText());
				empPieCount.setModal(true);
				empPieCount.setVisible(true);

			}
			if (label.getForeground().equals(new Color(254, 208, 64))
					&& label.getText().contentEquals("보너스 현황")) {
				empBarCount = new DlgStatisticCountEmpBonus();

				empBarCount.setTitle(label.getText());
				empBarCount.setModal(true);
				empBarCount.setVisible(true);

			}
			
		}

	}

	public DlgStatisticCountEmp getEmpPieCount() {
		return empPieCount;
	}

	public void closeEmpPieCount() {
		if(empPieCount != null) {
		  empPieCount.setVisible(false);
		}
	}
}
