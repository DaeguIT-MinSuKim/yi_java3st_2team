package yi_java3st_2team.ui.chart;

import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.paint.Color;
import yi_java3st_2team.ui.service.CustomerService;

@SuppressWarnings("serial")
public class PanelBarChart extends JFXPanel implements InitScene{
	public PanelBarChart() {
	}

	private BarChart<String, Number> barChart;
	private CustomerService service = new CustomerService();
	
	@Override
	public Scene createScene() {
		Group root = new Group();
		Scene scene = new Scene(root, Color.ALICEBLUE);
		root.setAutoSizeChildren(true);
		
		//막 대형 차트의 X 축과 Y 축을 정의하고 레이블을 설정
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("등급");

		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("고객 숫자");

		barChart = new BarChart<>(xAxis, yAxis);
		barChart.setTitle("등급 별 고객 숫자");
		
		barChart.setPrefSize(1000, 250);
		barChart.setData(getChartData());
		
		root.getChildren().add(barChart);

		return scene;
	}
	
	
	

	
	
	public XYChart.Series<String, Number> getChartData(String name, int rankB) {
		XYChart.Series<String, Number> dataSeries = new Series<String, Number>();
		dataSeries.setName(name);
		dataSeries.getData().add(new XYChart.Data<>(name, rankB));
		return dataSeries;
	}
	
	private ObservableList<XYChart.Series<String, Number>> getChartData() {
		ObservableList<XYChart.Series<String, Number>> list = FXCollections.observableArrayList();
		//BSGPD
		int rankB = 0;
		int rankS = 0;
		int rankG =0;
		int rankP =0;
		int rankD = 0;
		try {
			rankB = service.showBRankCustNum();
			rankS = service.showSRankCustNum();
			rankG = service.showGRankCustNum();
			rankP = service.showPRankCustNum();
			rankD = service.showVIPCustNum();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		list.add(getChartData("Bronze", rankB));
		list.add(getChartData("Silver", rankS));
		list.add(getChartData("Gold", rankG));
		list.add(getChartData("Platinum", rankP));
		list.add(getChartData("Diamond", rankD));
		
		return list;
	}

}
