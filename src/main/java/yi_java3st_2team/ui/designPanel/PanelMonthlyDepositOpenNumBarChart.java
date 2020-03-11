package yi_java3st_2team.ui.designPanel;

import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.chart.XYChart.Series;
import javafx.scene.paint.Color;
import yi_java3st_2team.ui.chart.InitScene;
import yi_java3st_2team.ui.service.BankBookService;
import yi_java3st_2team.ui.service.CustomerService;

@SuppressWarnings("serial")
public class PanelMonthlyDepositOpenNumBarChart extends JFXPanel implements InitScene{
	public PanelMonthlyDepositOpenNumBarChart() {
	}

	private BarChart<String, Number> barChart;
	private BankBookService service = new BankBookService();
	
	@Override
	public Scene createScene() {
		Group root = new Group();
		Scene scene = new Scene(root, Color.ALICEBLUE);
		root.setAutoSizeChildren(true);
		
		//막 대형 차트의 X 축과 Y 축을 정의하고 레이블을 설정
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("월별");

		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("건수");

		barChart = new BarChart<>(xAxis, yAxis);
		barChart.setTitle("월별 입금 건수");
		barChart.setLegendVisible(false);
		
		barChart.setPrefSize(500, 250);
		barChart.setData(getChartData());
		
		root.getChildren().add(barChart);

		return scene;
	}
	
	
	

	
	
	public XYChart.Series<String, Number> getChartData(String name, String num) {
		XYChart.Series<String, Number> dataSeries = new Series<String, Number>();
		dataSeries.setName(name);
		dataSeries.getData().add(new XYChart.Data<>(name, Integer.parseInt(num)));
		return dataSeries;
	}
	
	private ObservableList<XYChart.Series<String, Number>> getChartData() {
		ObservableList<XYChart.Series<String, Number>> list = FXCollections.observableArrayList();

		List<String> listDp = new ArrayList<>();//입금건수
		List<String> listZero = null;
		
		try {
			listDp = service.showDepositMonth();
			listZero = new ArrayList<>(listDp.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//bankbook 의 accountOpenDate에서 월을 잘라서 가져온 후 list 로 받음
		//i = 월 
		//j = 받은 list의 갯수 = 해당 월의 건수
		//list의 요소(01,02,03...)를 하나씩 가져와서 i의 1월,2월..과 맞는지 stringEqual 체크 
		//해당 월이 맞고, j가 list 사이즈보다 1이 작으면 차트 리스트에 월과 건수를 넣음 
		//j가 list사이즈보다 1이 작은 이유 : 건수가 받은 list 사이즈만큼 입력되는 것을 막기위해서 마지막 인덱스에서만 차트 리스트에 추가  
		for(int i=1; i<13; i++) {
			for(int j=0; j<listDp.size(); j++) {
				if(("0"+Integer.toString(i)).equals(listDp.get(j)) && j==(listDp.size()-1)) {
						list.add(getChartData(i+"월", Integer.toString(listDp.size())));
				}else {
					listZero.add("0");
					list.add(getChartData(i+"월", listZero.get(j)));
				}
			}
		}
		
		return list;
	}

}
