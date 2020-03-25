package yi_java3st_2team.ui.panel.bankbook;

import java.awt.Color;

import javax.swing.JPanel;

import yi_java3st_2team.ui.absPanel.AbsCenterStatisticWestPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class BankBookTransInfoWestMenuPanel extends AbsCenterStatisticWestPanel {

	/**
	 * Create the panel.
	 */
	public BankBookTransInfoWestMenuPanel() {
		initialize();
	}
	private void initialize() {
		setPanelInit(this.panel1, this.panel2, this.panel3, this.panel4, this.panel5, this.panel6, this.panel7);
		setLabelInit(this.menu1, this.menu2, this.menu3, this.menu4, this.menu5, this.menu6, this.menu7);
		panel1.setBackground(new Color(254,208,64));
	}

	@Override
	protected String[] getTexts() {
		return new String[] {"예금","적금","마이너스","","","",""};
	}

	@Override
	public JPanel[] getPanels() {
		return new JPanel[] {panel1,panel2,panel3};
	}

}
