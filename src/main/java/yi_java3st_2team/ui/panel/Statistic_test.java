package yi_java3st_2team.ui.panel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import yi_java3st_2team.ui.absPanel.AbsStatisticTabPanel;
import yi_java3st_2team.ui.chart.PanelBarChart;
import yi_java3st_2team.ui.chart.PanelPieChart;

public class Statistic_test extends AbsStatisticTabPanel {

	/**
	 * Create the panel.
	 */
	public Statistic_test() {

	}

	@Override
	protected List<JFXPanel> getJFXs() {
		List<JFXPanel> list = new ArrayList<>();
		
		PanelPieChart panel_VIP= new PanelPieChart();
		PanelBarChart barchart = new PanelBarChart();
		
		panel_VIP.setName("VIP고객 비율");
		barchart.setName("등급별 고객 숫자");
		
		list.add(panel_VIP);
		list.add(barchart);
		
		Platform.runLater(() -> initFX(panel_VIP));
		Platform.runLater(() -> initFX(barchart));
		
		return list;
	}
	
	

}
