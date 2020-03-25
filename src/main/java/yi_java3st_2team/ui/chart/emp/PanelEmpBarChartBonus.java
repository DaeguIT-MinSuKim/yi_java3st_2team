package yi_java3st_2team.ui.chart.emp;

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
import javafx.scene.chart.XYChart.Series;
import javafx.scene.paint.Color;
import yi_java3st_2team.dto.Employee;
import yi_java3st_2team.ui.chart.InitScene;
import yi_java3st_2team.ui.service.BankBookService;
import yi_java3st_2team.ui.service.EmployeeUIService;
 
public class PanelEmpBarChartBonus extends JFXPanel  implements InitScene {

	private static BarChart<String, Number> barChart;
	private static EmployeeUIService service = new EmployeeUIService();

	private static double numOfCS;
	private static double numOfHR;
	private static int totalnum;
	
	public PanelEmpBarChartBonus() {
	}


	public Scene createScene() {
		Group root = new Group();
		Scene scene = new Scene(root, Color.WHITE);
		totalnum = service.countAllEmpNum();
		numOfHR = service.countMemberByDepartment(1);			
		numOfCS = service.countMemberByDepartment(2);

		//막 대형 차트의 X 축과 Y 축을 정의하고 레이블을 설정
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("사원");

		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("금액");
		//yAxis.setAutoRanging(false); // 눈금단위 조절하기 위해 
		//yAxis.setUpperBound(totalnum);
		//yAxis.setTickUnit(1);

		barChart = new BarChart<>(xAxis, yAxis);
		barChart.setTitle("보너스현황");
		
		barChart.setPrefSize(800, 600);
		barChart.setData(getChartData());
		
		root.getChildren().add(barChart);

		return scene;
	}

	public static XYChart.Series<String, Number> getBarChartData(Employee emp) {
         XYChart.Series<String, Number> dataSeries = new Series<String, Number>();
		
		dataSeries.setName(emp.getEmpName());
		dataSeries.getData().add(new XYChart.Data<>("보너스",emp.getBonus()));
		return dataSeries;
	
	}
	
	private static ObservableList<XYChart.Series<String, Number>> getChartData() {
		ObservableList<XYChart.Series<String, Number>> list = FXCollections.observableArrayList();
		
		List<Employee> empList = service.showEmpPerformance();
		for(int i=0; i<empList.size(); i++) {
			Employee emp = empList.get(i);
			if(emp.getBonus() != 0) {
			  list.add(getBarChartData(emp));
			}
		}
		
		return list;
	}


	public void initAndShowGUI() {
		// TODO Auto-generated method stub
		
	}
	
}
