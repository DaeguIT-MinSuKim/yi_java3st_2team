package yi_java3st_2team.ui.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import yi_java3st_2team.ui.absPanel.AbsCenterStatisticPanel;
import yi_java3st_2team.ui.chart.JFrameBarChart;
import yi_java3st_2team.ui.chart.JFrameCustNumChart;
import yi_java3st_2team.ui.chart.JFrameMonthlyDpWdBarChart;
import yi_java3st_2team.ui.chart.JFrameMonthlyOpenNumBarChart;

public class CustStatisticPanel extends AbsCenterStatisticPanel implements ActionListener{

	public CustStatisticPanel() {
		btnSearch.addActionListener(this);
		this.setLabelInit(this.lblStat1, this.lblStat2, this.lblStat3, this.lblStat4);
		this.setLblMouseListener(this.lblStat1, this.lblStat2, this.lblStat3, this.lblStat4);
	}

	@Override
	protected String[] getTexts() {
		return new String[] {
				"예/적금건수(월별/연도별)",
				"입/출금 건수(월별/연도별)",
				"예금/적금/대출 총 금액",
				"총 고객 숫자/전체 고객 대비 VIP 고객 비율/등급별 고객 숫자"
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
				if(label.getText().equals("예금/적금/대출 총 금액")) {
					JFrameBarChart barchart = new JFrameBarChart();
					barchart.initAndShowGUI();
				}else if(label.getText().equals("총 고객 숫자/전체 고객 대비 VIP 고객 비율/등급별 고객 숫자")){
					JFrameCustNumChart custNumChart = new JFrameCustNumChart();
					custNumChart.setVisible(true);
				}else if(label.getText().equals("예/적금건수(월별/연도별)")) {
					JFrameMonthlyOpenNumBarChart monthlyOpenChart = new JFrameMonthlyOpenNumBarChart();
					monthlyOpenChart.setVisible(true);
				}
				else if(label.getText().equals("입/출금 건수(월별/연도별)")) {
					JFrameMonthlyDpWdBarChart monthlyDpWdChart = new JFrameMonthlyDpWdBarChart();
					monthlyDpWdChart.setVisible(true);
				}
				
			}
		}
	}


}
