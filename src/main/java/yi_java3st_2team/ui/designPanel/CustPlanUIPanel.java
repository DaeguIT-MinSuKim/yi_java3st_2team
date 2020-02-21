package yi_java3st_2team.ui.designPanel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.sql.SQLException;

import yi_java3st_2team.ui.panel.CustPlanCenterNorthSearchPanel;
import yi_java3st_2team.ui.service.PlanService;
import yi_java3st_2team.ui.panel.CustPlanCenterCenterTblPanel;

public class CustPlanUIPanel extends JPanel {
	PlanService planService = new PlanService();
	
	public CustPlanUIPanel() {

		initialize();
	}
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		CustPlanCenterNorthSearchPanel panel = new CustPlanCenterNorthSearchPanel();
		add(panel, BorderLayout.NORTH);
		
		CustPlanCenterCenterTblPanel panel_1 = new CustPlanCenterCenterTblPanel();
		try {
			panel_1.loadTableData(planService.showPlans());
		} catch (SQLException e) {
			System.out.println("해당 상품이 없습니다.");
			e.printStackTrace();
		}
		add(panel_1, BorderLayout.CENTER);
	}

}
