package yi_java3st_2team.ui.panel.bankbook;

import javax.swing.JButton;
import javax.swing.JPanel;

import yi_java3st_2team.ui.absPanel.AbsCenterStatisticNorthPanel;

@SuppressWarnings("serial")
public class CardTransInfoNorthPanel extends AbsCenterStatisticNorthPanel {
	/**
	 * Create the panel.
	 */
	public CardTransInfoNorthPanel() {
		setBtns();
	}

	@Override
	public JButton[] getBtns() {
		return buttons;
	}

	@Override
	protected String[] getText() {
		return new String[] {"일간 거래 내역","주간 거래 내역","월간 거래 내역","연간 거래 내역"};
	}

}
