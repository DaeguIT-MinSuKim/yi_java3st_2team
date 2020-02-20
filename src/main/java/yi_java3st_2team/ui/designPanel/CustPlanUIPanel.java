package yi_java3st_2team.ui.designPanel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import yi_java3st_2team.ui.panel.CustPlanCenterNorthSearchPanel;
import yi_java3st_2team.ui.panel.CustPlanCenterCenterTblPanel;

public class CustPlanUIPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public CustPlanUIPanel() {

		initialize();
	}
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		CustPlanCenterNorthSearchPanel panel = new CustPlanCenterNorthSearchPanel();
		add(panel, BorderLayout.NORTH);
		
		CustPlanCenterCenterTblPanel panel_1 = new CustPlanCenterCenterTblPanel();
		add(panel_1, BorderLayout.CENTER);
	}

}
