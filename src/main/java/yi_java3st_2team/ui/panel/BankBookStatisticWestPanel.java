package yi_java3st_2team.ui.panel;

import javax.swing.JPanel;

import yi_java3st_2team.ui.absPanel.AbsCenterStatisticWestPanel_test;

@SuppressWarnings("serial")
public class BankBookStatisticWestPanel extends AbsCenterStatisticWestPanel_test {

	/**
	 * Create the panel.
	 */
	public BankBookStatisticWestPanel() {
		setPanelInit(this.panel1, this.panel2, this.panel3, this.panel4, this.panel5, this.panel6, this.panel7);
		setLabelInit(this.menu1, this.menu2, this.menu3, this.menu4, this.menu5, this.menu6, this.menu7);
	}

	@Override
	protected String[] getTexts() {
		return new String[] {"휴면 계좌 조회","해지 계좌 조회","","","","",""};
	}

	@Override
	public JPanel[] getPanels() {
		return new JPanel[] {panel1,panel2};
	}

}
