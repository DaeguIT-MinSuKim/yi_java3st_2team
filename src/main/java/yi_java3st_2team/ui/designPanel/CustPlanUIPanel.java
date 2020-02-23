package yi_java3st_2team.ui.designPanel;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import yi_java3st_2team.ui.panel.CustPlanCenterNorthSearchPanel;
import yi_java3st_2team.ui.service.PlanService;
import yi_java3st_2team.dto.Customer;
import yi_java3st_2team.dto.Plan;
import yi_java3st_2team.ui.panel.CustPlanCenterCenterTblPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustPlanUIPanel extends JPanel{
	PlanService planService = new PlanService();
	private CustPlanCenterCenterTblPanel panel_1;
	private CustPlanCenterNorthSearchPanel panel;
	
	public CustPlanUIPanel() {

		initialize();
	}
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		panel = new CustPlanCenterNorthSearchPanel();
		panel.getBtnSearch().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String planName = panel.getTfSearch().getText().trim();
				List<Plan> list = new ArrayList<>();
				try {
					if(list.size()==0){
						list = planService.showPlansByName(planName);
					}
					if(list==null) {
						JOptionPane.showMessageDialog(null, "해당 상품이 없습니다.");
						return;
					}
					panel_1.loadTableData(list);
				}catch (SQLException e1) {
					System.out.println("해당 상품이 없습니다.");
					e1.printStackTrace();
				}
				
			}
			
		});
		panel.getBtnCancel().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panel.tfClear();
				try {
					panel_1.loadTableData(planService.showPlans());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
		});
		add(panel, BorderLayout.NORTH);
		
		panel_1 = new CustPlanCenterCenterTblPanel();
		try {
			panel_1.loadTableData(planService.showPlans());
		} catch (SQLException e) {
			System.out.println("해당 상품이 없습니다.");
			e.printStackTrace();
		}
		add(panel_1, BorderLayout.CENTER);
	}
	


}
