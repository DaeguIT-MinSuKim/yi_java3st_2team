package yi_java3st_2team.ui.designPanel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.sql.SQLException;

import yi_java3st_2team.ui.panel.CustInfoCenterNorthSearchPanel;
import yi_java3st_2team.ui.service.CustomerService;
import yi_java3st_2team.ui.panel.CustDWCenterCenterTblPanel;

public class CustDWUIPanel extends JPanel {
	CustomerService service = new CustomerService();
	
	public CustDWUIPanel() {

		initialize();
	}
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		CustInfoCenterNorthSearchPanel panel = new CustInfoCenterNorthSearchPanel();
		add(panel, BorderLayout.NORTH);
		
		CustDWCenterCenterTblPanel panel_1 = new CustDWCenterCenterTblPanel();
		try {
			panel_1.loadTableData(service.showCustomersByBalance());
		} catch (SQLException e) {
			System.out.println("해당 고객이 없습니다.");
			e.printStackTrace();
		}
		add(panel_1, BorderLayout.CENTER);
	}

}
