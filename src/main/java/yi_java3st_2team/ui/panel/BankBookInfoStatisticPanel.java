package yi_java3st_2team.ui.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import yi_java3st_2team.ui.absPanel.AbsCenterStatisticPanel;
import yi_java3st_2team.ui.chart.DlgDormant;
import yi_java3st_2team.ui.chart.DlgTermination;

@SuppressWarnings("serial")
public class BankBookInfoStatisticPanel extends AbsCenterStatisticPanel implements ActionListener {
	private DlgDormant dlgDormant;
	private DlgTermination dlgTermination;
	/**
	 * Create the panel.
	 */
	public BankBookInfoStatisticPanel() {
		btnSearch.addActionListener(this);
		setLabelInit(lblStat1,lblStat2);
		setLblMouseListener(lblStat1,lblStat2);
	}

	@Override
	protected String[] getTexts() {
		return new String[] {"휴면 계좌 조회","해지 계좌 조회"};
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSearch) {
			thisBtnSearchActionPerformed(e);
		}
	}
	protected void thisBtnSearchActionPerformed(ActionEvent e) {
		for(Component c : pCenter.getComponents()) {
			JLabel label = (JLabel)c;
			if(label.getForeground().equals(new Color(254,208,64))) {
				switch(label.getText()) {
				case "휴면 계좌 조회":
					dlgDormant = new DlgDormant();
					dlgDormant.setModal(true);
					dlgDormant.setVisible(true);
					break;
				case "해지 계좌 조회":
					dlgTermination = new DlgTermination();
					dlgTermination.setModal(true);
					dlgTermination.setVisible(true);
					break;
				}
				return;
			}
		}
	}
}
