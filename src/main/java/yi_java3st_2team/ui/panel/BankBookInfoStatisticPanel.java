package yi_java3st_2team.ui.panel;

import yi_java3st_2team.ui.absPanel.AbsCenterStatisticPanel;

@SuppressWarnings("serial")
public class BankBookInfoStatisticPanel extends AbsCenterStatisticPanel {

	/**
	 * Create the panel.
	 */
	public BankBookInfoStatisticPanel() {
		setLabelInit(lblStat1,lblStat2,lblStat3,lblStat4);
		setLblMouseListener(lblStat1,lblStat2,lblStat3,lblStat4);
	}

	@Override
	protected String[] getTexts() {
		return new String[] {"입출금 건수","TBD","TBD","TBD"};
	}

}
