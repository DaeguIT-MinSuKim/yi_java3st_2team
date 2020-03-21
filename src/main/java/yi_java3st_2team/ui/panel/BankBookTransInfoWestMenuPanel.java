package yi_java3st_2team.ui.panel;

import javax.swing.JPanel;

import yi_java3st_2team.ui.absPanel.AbsCenterStatisticWestPanel_test;

@SuppressWarnings("serial")
public class BankBookTransInfoWestMenuPanel extends AbsCenterStatisticWestPanel_test {

	/**
	 * Create the panel.
	 */
	public BankBookTransInfoWestMenuPanel() {

	}

	@Override
	protected String[] getTexts() {
		return new String[] {"예금","적금","마이너스"};
	}

	@Override
	public JPanel[] getPanels() {
		return new JPanel[] {panel1,panel2,panel3};
	}

}
