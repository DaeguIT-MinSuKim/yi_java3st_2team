package yi_java3st_2team.ui.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import yi_java3st_2team.ui.absPanel.AbsCenterStatisticWestPanel_test;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class CustStatistic_WestPanel extends AbsCenterStatisticWestPanel_test implements ActionListener  {

	/**
	 * Create the panel.
	 */
	public CustStatistic_WestPanel() {
		
		initialize();
	}
	private void initialize() {

		setLabelInit(this.menu1, this.menu2, this.menu3, this.menu4, this.menu5, this.menu6, this.menu7);
		setPanelInit(this.panel_1, this.panel_2, this.panel_3, this.panel_4, this.panel_5, this.panel_6, this.panel);
		setLblMouseListener(this.panel_1, this.panel_2, this.panel_3, this.panel_4, this.panel_5, this.panel_6, this.panel);
		getPanels(this.panel_1, this.panel_2, this.panel_3, this.panel_4, this.panel_5, this.panel_6, this.panel);
	}

	@Override
	protected String[] getTexts() {
		return new String[] {
				"예/적금건수(월별)",
				"입/출금 건수(월별)",
				"예금/적금/대출 총 금액",
				"일반고객/VIP고객",
				"",
				"",
				""
				//총 고객 숫자/전체 고객 대비 VIP 고객 비율/등급별 고객 숫자
			};
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}


	
}
