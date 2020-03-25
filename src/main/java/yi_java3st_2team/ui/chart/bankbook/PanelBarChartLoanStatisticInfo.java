package yi_java3st_2team.ui.chart.bankbook;

import java.sql.SQLException;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.paint.Color;
import yi_java3st_2team.dto.AccountInfo;
import yi_java3st_2team.dto.CardInfo;
import yi_java3st_2team.dto.LoanInfo;
import yi_java3st_2team.ui.chart.InitScene;
import yi_java3st_2team.ui.service.BankBookService;
import yi_java3st_2team.ui.service.CardService;
import yi_java3st_2team.ui.service.LoanService;

@SuppressWarnings("serial")
public class PanelBarChartLoanStatisticInfo extends JFXPanel implements InitScene{
	private BarChart<String, Number> barChart;
	private LoanService service;
	public PanelBarChartLoanStatisticInfo() {
		
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
		yAxis.setLabel("카드개수");
		yAxis.autosize();
		yAxis.setTickLabelFont(new javafx.scene.text.Font(15));

		barChart = new BarChart<String, Number>(xAxis, yAxis);
		barChart.setTitle("고객별 카드 보유 현황");
		barChart.setPrefSize(1000, 500);
		barChart.setStyle("-fx-font-size: " + 20 + "px;");
		barChart.setData(getChartData());
		barChart.setLegendVisible(true);
		root.getChildren().add(barChart);
		return scene;
	}
	public XYChart.Series<String, Number> getChartData(LoanInfo info) {
		XYChart.Series<String, Number> dataSeries = new Series<String, Number>();
		dataSeries.setName(info.getCustname());
		dataSeries.getData().add(new XYChart.Data<>("일반대출",info.getNormal()));
		dataSeries.getData().add(new XYChart.Data<>("신용대출",info.getCredit()));
		dataSeries.getData().add(new XYChart.Data<>("카드론",info.getCard()));
		return dataSeries;
	}
	
	private ObservableList<XYChart.Series<String, Number>> getChartData() {
		ObservableList<XYChart.Series<String, Number>> list = FXCollections.observableArrayList();
		service = new LoanService();
		List<LoanInfo> loanList = null;
		try {
			loanList = service.showLoanInfo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(LoanInfo l : loanList) {
			list.add(getChartData(l));
		}
		return list;
	}

}
