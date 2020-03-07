package yi_java3st_2team.ui.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import yi_java3st_2team.ui.absPanel.AbsCenterStatisticPanel;
import yi_java3st_2team.ui.designPanel.JFrameBarChart;

public class CustStatisticPanel extends AbsCenterStatisticPanel implements ActionListener{

	public CustStatisticPanel() {
		btnSearch.addActionListener(this);
		this.setLabelInit(this.lblStat1, this.lblStat2, this.lblStat3, this.lblStat4, this.lblStat5, this.lblStat6, this.lblStat7, this.lblStat8);
		this.setLblMouseListener(this.lblStat1, this.lblStat2, this.lblStat3, this.lblStat4, this.lblStat5, this.lblStat6, this.lblStat7, this.lblStat8);
	}

	@Override
	protected String[] getTexts() {
		return new String[] {
				"예/적금건수(월별/연도별)",
				"입/출금 건수(월별/연도별)",
				"예금 총 금액",
				"대출 총 금액",
				"적금 총 금액",
				"총 고객 숫자",
				"전체 고객 대비 VIP 고객 비율",
				"등급별 고객 숫자"
			};
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSearch) {
			BtnSearchActionPerformed(e);
		}
	}
	protected void BtnSearchActionPerformed(ActionEvent e) {
		for(Component c : pCenter.getComponents()) {
			JLabel label = (JLabel)c;
			if(label.getForeground().equals(new Color(254,208,64))) {
				JFrameBarChart barchart = new JFrameBarChart();
				barchart.initAndShowGUI();
			}
		}
	}


}
