package yi_java3st_2team.ui.chart;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import yi_java3st_2team.dto.Department;
import yi_java3st_2team.dto.Employee;
import yi_java3st_2team.ui.panel.EmpStaticPanel;
import yi_java3st_2team.ui.service.EmployeeUIService;

@SuppressWarnings("serial")
public class DlgStatisticEmpSalary extends JDialog {
	private static PieChart pieChart;
	private static EmployeeUIService service = new EmployeeUIService();

	private final JPanel contentPanel = new JPanel();
	private static double numOfCS;
	private static double numOfHR;
	private static int totalnum;
	private JButton okButton;

	
	private BarChart<String, Number> barChart;
	
	public DlgStatisticEmpSalary() {
		
		    totalnum = service.countAllEmpNum();
			numOfHR = service.countMemberByDepartment(1);			
			numOfCS = service.countMemberByDepartment(2);
		
		setBounds(700,50,600,400);
		
		final JFXPanel fxPanel = new JFXPanel();
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.add(fxPanel, BorderLayout.CENTER); 
		
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		Platform.runLater(() -> initFX(fxPanel));


	}
	
	private void initFX(JFXPanel fxPanel) {
		Scene scene = createScene();
		fxPanel.setScene(scene);
	}

	private Scene createScene() {
		Group root = new Group();
		Scene scene = new Scene(root);
		root.setAutoSizeChildren(true);
		
		
		//막 대형 차트의 X 축과 Y 축을 정의하고 레이블을 설정
				CategoryAxis xAxis = new CategoryAxis();
				xAxis.setLabel("");

				NumberAxis yAxis = new NumberAxis();
				yAxis.setLabel("금액");
				//yAxis.setAutoRanging(false); // 눈금단위 조절하기 위해 
				//yAxis.setUpperBound(totalnum);
				//yAxis.setTickUnit(1);

				barChart = new BarChart<>(xAxis, yAxis);
				barChart.setTitle("전체/인당 평균 급여액");
				
				barChart.setPrefSize(500, 250);
				barChart.setData(getChartData());
				
				root.getChildren().add(barChart);

				return scene;
	}
	
	public XYChart.Series<String, Number> getChartData(List list) {
		XYChart.Series<String, Number> dataSeries = new Series<String, Number>();
		
		dataSeries.setName(list.toString());
		
		dataSeries.getData().add(new XYChart.Data<>(list.get(0).toString(),service.totalSalary()));
		dataSeries.getData().add(new XYChart.Data<>(list.get(1).toString(),service.avgOfSalary()));
		

		return dataSeries;
		
		//emp.getKorScore())
	}
	

	private ObservableList<XYChart.Series<String, Number>> getChartData() {
		ObservableList<XYChart.Series<String, Number>> list = FXCollections.observableArrayList();	
		List<String> strList = new ArrayList<String>();
	
		strList.add("전체 월급");
		strList.add("1인당 월급");
		
		list.add(getChartData(strList));
		
		return list;
	}
	
}
