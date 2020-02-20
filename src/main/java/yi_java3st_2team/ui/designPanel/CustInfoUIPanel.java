package yi_java3st_2team.ui.designPanel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import yi_java3st_2team.ui.panel.CustInfoCenterNorthSearchPanel;
import yi_java3st_2team.ui.panel.CustInfoCenterCenterTblPanel;

public class CustInfoUIPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public CustInfoUIPanel() {

		initialize();
	}
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		CustInfoCenterNorthSearchPanel panel = new CustInfoCenterNorthSearchPanel();
		add(panel, BorderLayout.NORTH);
		
		CustInfoCenterCenterTblPanel panel_1 = new CustInfoCenterCenterTblPanel();
		add(panel_1, BorderLayout.CENTER);
	}

}
