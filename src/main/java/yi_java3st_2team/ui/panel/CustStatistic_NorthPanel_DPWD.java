package yi_java3st_2team.ui.panel;

import javax.swing.JButton;

import yi_java3st_2team.ui.absPanel.AbsCenterStatisticNorthPanel_test;

@SuppressWarnings("serial")
public class CustStatistic_NorthPanel_DPWD extends AbsCenterStatisticNorthPanel_test {
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
