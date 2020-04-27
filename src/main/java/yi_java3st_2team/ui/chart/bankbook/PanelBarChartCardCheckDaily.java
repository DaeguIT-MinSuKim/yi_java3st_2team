package yi_java3st_2team.ui.chart.bankbook;

import java.sql.SQLException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.paint.Color;
import yi_java3st_2team.dto.CardInfo;
import yi_java3st_2team.ui.chart.InitScene;
import yi_java3st_2team.ui.service.CardService;

@SuppressWarnings("serial")
public class PanelBarChartCardCheckDaily extends JFXPanel implements InitScene{
	private BarChart<String, Number> barChart;
	private CardService service;
	public PanelBarChartCardCheckDaily() {
		
	}
	
	@Override
	public Scene createScene() {
		Group root = new Group();
		Scene scene = new Scene(root, Color.WHITE);
		root.setAutoSizeChildren(true);
		
		//막 대형 차트의 X 축과 Y 축을 정의하고 레이블을 설정
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setTickLabelFont(new javafx.scene.text.Font(15));
		xAxis.autosize();
		xAxis.setLabel("고객");

		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("거래량");
		yAxis.autosize();
		yAxis.setTickLabelFont(new javafx.scene.text.Font(15));

		barChart = new BarChart<String, Number>(xAxis, yAxis);
		barChart.setTitle("고객별 체크카드 일간 거래 내역 조회");
		barChart.setPrefSize(1100, 500);
		barChart.setStyle("-fx-font-size: " + 20 + "px;");
		barChart.setData(getChartData());
		barChart.setLegendVisible(false);
		root.getChildren().add(barChart);
		return scene;
	}
	
	private ObservableList<XYChart.Series<String, Number>> getChartData() {
		ObservableList<Series<String, Number>> list = FXCollections.observableArrayList();
		service = new CardService();
		List<CardInfo> infoList = null;
		try {
			infoList = service.cardInfoDaily();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XYChart.Series<String, Number> series = new Series<String, Number>();
		for(CardInfo c : infoList) {
			if(c.getDiv().equals("체크카드")) {
				series.getData().add(new Data<String, Number>(c.getCustName(),c.getCount()));
			}
		}
		list.add(series);
		return list;
	}

}
