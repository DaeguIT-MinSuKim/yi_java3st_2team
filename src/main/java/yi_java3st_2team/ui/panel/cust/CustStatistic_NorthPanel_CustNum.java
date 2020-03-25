package yi_java3st_2team.ui.panel.cust;

import javax.swing.JButton;

import yi_java3st_2team.ui.absPanel.AbsCenterStatisticNorthPanel;

@SuppressWarnings("serial")
public class CustStatistic_NorthPanel_CustNum extends AbsCenterStatisticNorthPanel {
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
