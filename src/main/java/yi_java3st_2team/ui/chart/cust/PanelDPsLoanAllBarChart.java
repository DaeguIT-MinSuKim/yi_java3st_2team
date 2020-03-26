package yi_java3st_2team.ui.chart.cust;

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
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import yi_java3st_2team.ui.chart.InitScene;
import yi_java3st_2team.ui.service.BankBookService;
 
public class PanelDPsLoanAllBarChart extends JFXPanel  implements InitScene {

	private static BarChart<String, Number> barChart;
	private static BankBookService service = new BankBookService();

	
	
	public PanelDPsLoanAllBarChart() {
	}


	public Scene createScene() {
		Group root = new Group();
		Scene scene = new Scene(root, Color.WHITE);

		//막 대형 차트의 X 축과 Y 축을 정의하고 레이블을 설정
		CategoryAxis xAxis = new CategoryAxis();

		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("총 금액");

		barChart = new BarChart<>(xAxis, yAxis);
		barChart.setTitle("항목별 총 금액");
		
		barChart.setPrefSize(1000, 500);
		//System.out.println(barChart.getOpaqueInsets());
		
		//barChart.getData().add(getBarChartData());
		barChart.setData(getChartData());
		root.getChildren().add(barChart);

		return scene;
	}

	public static XYChart.Series<String, Number> getBarChartData(String name,String balance) {
		XYChart.Series<String, Number> dataSeries = new Series<String, Number>();
		try {
			dataSeries.getData().add(new XYChart.Data<>(name, Integer.parseInt(balance)));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} 
		return dataSeries;
	}
	
	private static ObservableList<XYChart.Series<String, Number>> getChartData() {
		ObservableList<XYChart.Series<String, Number>> list = FXCollections.observableArrayList();
		
		String dpBalance = null;
		String savingBalance = null;
		String LoanBalance = null;
		try {
			dpBalance = service.showDpBalance();
			savingBalance = service.showSvBalance();
			LoanBalance = service.showLoBalance();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		list.add(getBarChartData("예금",dpBalance));
		list.add(getBarChartData("적금", savingBalance));
		list.add(getBarChartData("대출", LoanBalance));
		
		return list;
	}


	public void initAndShowGUI() {
		// TODO Auto-generated method stub
		
	}
	
}
