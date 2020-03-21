package yi_java3st_2team.ui.chart;

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
import yi_java3st_2team.ui.service.BankBookService;

@SuppressWarnings("serial")
public class PanelBarChartBankBookDeposit extends JFXPanel implements InitScene{
	private BarChart<String, Number> barChart;
	private BankBookService service = new BankBookService();
	public PanelBarChartBankBookDeposit() {
		
	}
	
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
		
		barChart.setPrefSize(1000, 600);
		barChart.setData(getChartData());
		root.getChildren().add(barChart);
		return scene;
	}
	
	public XYChart.Series<String, Number> getChartData(String name, int rankB) {
		XYChart.Series<String, Number> dataSeries = new Series<String, Number>();
		dataSeries.setName(name);
		dataSeries.getData().add(new XYChart.Data<>());
		return dataSeries;
	}
	
	private ObservableList<XYChart.Series<String, Number>> getChartData() {
		ObservableList<XYChart.Series<String, Number>> list = FXCollections.observableArrayList();
		return list;
	}

}
