package yi_java3st_2team.ui.panel;

import javax.swing.JButton;

import yi_java3st_2team.ui.absPanel.AbsCenterStatisticNortPanel_test;

@SuppressWarnings("serial")
public class CustStatistic_NorthPanel_CustNum extends AbsCenterStatisticNortPanel_test {
	public CustStatistic_NorthPanel_CustNum() {
		initialize();
	}
	private void initialize() {
		setBtns();
	}
	
	@Override
	protected String[] getText() {
		String[] texts = {"등급별 고객수", "VIP 고객 비율"};
		return texts;
	}
	@Override
	public JButton[] getBtns() {
		return buttons;
	}
	
	
	

	

}
