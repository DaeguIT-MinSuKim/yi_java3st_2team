package yi_java3st_2team.ui.panel.cust;

import java.awt.Color;

import javax.swing.JPanel;

import yi_java3st_2team.dto.Plan;
import yi_java3st_2team.ui.absPanel.AbsCenterNorthSearchPanel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class CustPlanCenterNorthSearchPanel extends AbsCenterNorthSearchPanel<Plan> {

	public CustPlanCenterNorthSearchPanel() {
		
	}


	@Override
	public void tfClear() {
		this.getTfSearch().setText("");
		
	}

	@Override
	public String[] setSearchList() {
		String[] list = {"검색 구분", "상품 코드(A)","상품 세부코드(AB)", "상품 이름"};
		return list;
	}

}
