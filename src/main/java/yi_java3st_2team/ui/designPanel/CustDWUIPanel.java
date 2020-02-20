package yi_java3st_2team.ui.designPanel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import yi_java3st_2team.ui.panel.CustInfoCenterNorthSearchPanel;
import yi_java3st_2team.ui.panel.CustDWCenterCenterTblPanel;

public class CustDWUIPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public CustDWUIPanel() {

		initialize();
	}
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		CustInfoCenterNorthSearchPanel panel = new CustInfoCenterNorthSearchPanel();
		add(panel, BorderLayout.NORTH);
		
		CustDWCenterCenterTblPanel panel_1 = new CustDWCenterCenterTblPanel();
		add(panel_1, BorderLayout.CENTER);
	}

}
