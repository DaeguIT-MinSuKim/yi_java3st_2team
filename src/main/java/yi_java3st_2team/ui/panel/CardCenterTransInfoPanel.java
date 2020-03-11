package yi_java3st_2team.ui.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import yi_java3st_2team.ui.absPanel.AbsCenterStatisticPanel;
import yi_java3st_2team.ui.chart.JFrameBarChartForCard;

@SuppressWarnings("serial")
public class CardCenterTransInfoPanel extends AbsCenterStatisticPanel {
	public CardCenterTransInfoPanel() {
		setLabelInit(lblStat1,lblStat2,lblStat3,lblStat4);
		setLblMouseListener(lblStat1,lblStat2,lblStat3,lblStat4);
	}

	@Override
	protected String[] getTexts() {
		return new String[] {"일간 거래 내역","주간 거래 내역","월간 거래 내역","년간 거래 내역"};
	}
	protected void thisBtnSearchActionPerformed(ActionEvent e) {
		for(Component c : pCenter.getComponents()) {
			JLabel label = (JLabel)c;
			if(label.getForeground().equals(new Color(254,208,64))) {
				JFrameBarChartForCard.initAndShowGUI();
			}
		}
	}
}
