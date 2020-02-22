package yi_java3st_2team.ui.panel;

import java.awt.Color;

import javax.swing.JPanel;

import yi_java3st_2team.dto.Plan;
import yi_java3st_2team.ui.absPanel.AbsCenterNorthSearchPanel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class CustPlanCenterNorthSearchPanel extends AbsCenterNorthSearchPanel<Plan> {

	public CustPlanCenterNorthSearchPanel() {
		initialize();
	}
	private void initialize() {
		getLblSearch().setFont(new Font("맑은 고딕", Font.BOLD, 20));
		getLblSearch().setHorizontalAlignment(SwingConstants.CENTER);
		this.getLblSearch().setText("상품명 입력");
		this.getLblSearch().setForeground(new Color(0,0,0));
	}

	@Override
	public void tfClear() {
		this.getTfSearch().setText("");
		
	}

}
