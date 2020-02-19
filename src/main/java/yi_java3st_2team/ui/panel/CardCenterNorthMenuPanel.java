package yi_java3st_2team.ui.panel;

import yi_java3st_2team.ui.absPanel.AbsCenterNorthMenuPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class CardCenterNorthMenuPanel extends AbsCenterNorthMenuPanel {
	/**
	 * Create the panel.
	 */
	public CardCenterNorthMenuPanel() {
		initialize();
	}
	private void initialize() {
		lblMenu2.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenu1.setHorizontalAlignment(SwingConstants.CENTER);
		setLabelInit(lblMenu1,lblMenu2);
	}

	@Override
	protected String[] getTexts() {
		return new String[] {"카드 거래 내역 조회","카드 정보 조회"};
	}

}
