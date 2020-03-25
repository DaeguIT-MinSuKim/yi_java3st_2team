package yi_java3st_2team.ui.panel.cust;

import javax.swing.JButton;

import yi_java3st_2team.ui.absPanel.AbsCenterStatisticNorthPanel;

@SuppressWarnings("serial")
public class CustStatistic_NorthPanel extends AbsCenterStatisticNorthPanel {
	public CustStatistic_NorthPanel() {
		initialize();
	}
	private void initialize() {
		setBtns();
	}
	
	@Override
	protected String[] getText() {
		String[] texts = {"예금", "적금"};
		return texts;
	}
	@Override
	public JButton[] getBtns() {
		return buttons;
	}
	
	
	

	

}
