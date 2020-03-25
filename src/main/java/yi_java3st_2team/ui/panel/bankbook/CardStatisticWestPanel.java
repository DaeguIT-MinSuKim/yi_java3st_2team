package yi_java3st_2team.ui.panel.bankbook;

import javax.swing.JPanel;

import yi_java3st_2team.ui.absPanel.AbsCenterStatisticWestPanel;

@SuppressWarnings("serial")
public class CardStatisticWestPanel extends AbsCenterStatisticWestPanel {

	/**
	 * Create the panel.
	 */
	public CardStatisticWestPanel() {
		setPanelInit(this.panel1, this.panel2, this.panel3, this.panel4, this.panel5, this.panel6, this.panel7);
		setLabelInit(this.menu1, this.menu2, this.menu3, this.menu4, this.menu5, this.menu6, this.menu7);
	}

	@Override
	protected String[] getTexts() {
		return new String[] {"고객별 카드 보유 현황","","","","","",""};
	}

	@Override
	public JPanel[] getPanels() {
		return new JPanel[] {panel1};
	}

}
