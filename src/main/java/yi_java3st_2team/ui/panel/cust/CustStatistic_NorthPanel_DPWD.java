package yi_java3st_2team.ui.panel.cust;

import javax.swing.JButton;

import yi_java3st_2team.ui.absPanel.AbsCenterStatisticNorthPanel;

@SuppressWarnings("serial")
public class CustStatistic_NorthPanel_DPWD extends AbsCenterStatisticNorthPanel {
	public CustStatistic_NorthPanel_DPWD() {
		initialize();
	}
	private void initialize() {
		setBtns();
	}
	
	@Override
	protected String[] getText() {
		String[] texts = {"입금", "출금"};
		return texts;
	}
	@Override
	public JButton[] getBtns() {
		return buttons;
	}
	
	
	

	

}
