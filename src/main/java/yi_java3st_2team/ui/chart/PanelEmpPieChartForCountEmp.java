package yi_java3st_2team.ui.chart;

import java.sql.SQLException;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import yi_java3st_2team.ui.service.CustomerService;
import yi_java3st_2team.ui.service.EmployeeUIService;

@SuppressWarnings("serial")
public class PanelEmpPieChartForCountEmp extends JFXPanel implements InitScene{
	
	private PieChart pieChart;
	private static EmployeeUIService service = new EmployeeUIService();
	private static double numOfCS;
	private static double numOfHR;
	private static int totalnum;
	@Override
	public Scene createScene() {
		totalnum = service.countAllEmpNum();
		numOfHR = service.countMemberByDepartment(1);			
		numOfCS = service.countMemberByDepartment(2);
		
		Group root = new Group();
		Scene scene = new Scene(root);
		root.setAutoSizeChildren(true);
		
		String subject = "Employee Pie Chart";
		String totalEmp = "전체 직원 수 : ";
		
		
		pieChart = new PieChart();
		pieChart.setPrefSize(1000, 350);
		pieChart.setData(getChartData());
		pieChart.setTitle(subject+"\n"+totalEmp+totalnum+"명");
		pieChart.setLegendVisible(false);	// 범례 표시 유무
		pieChart.setLegendSide(Side.BOTTOM);// 범례 위치
		pieChart.setLabelLineLength(30);	// 원의 둘레 가장자리와 라벨간의 거리 지정
		pieChart.setClockwise(true); 		// 시계방향 배치여부
		pieChart.setLabelsVisible(true);	// 레이블 표시여부
		
				
//		pieChart.getData().forEach(data -> data.nameProperty().bind(Bindings.concat(data.getName(), " ", data.pieValueProperty(), " %")));
		for(Data d : pieChart.getData()) {
			d.nameProperty().bind(Bindings.concat(d.getName(), " ", d.pieValueProperty(), " %"));
		}
		
		root.getChildren().add(pieChart);

		return scene;
	}
	
	private ObservableList<Data> getChartData() {
          ObservableList<Data> answer = FXCollections.observableArrayList();
		
		
		answer.addAll(new PieChart.Data("인사팀", Math.round((numOfHR/totalnum)*100)), new PieChart.Data("고객팀", Math.round((numOfCS/totalnum)*100)));
		return answer;
	}

	public static void main(String[] args) {
	  JFrame frame = new JFrame();
	  frame.setSize(600,600);
	  PanelEmpPieChartForCountEmp panel = new PanelEmpPieChartForCountEmp();
	  frame.getContentPane().add(panel);
	  Platform.runLater(() -> initFX(panel));
	  frame.setVisible(true);
	  
	}
	public static void initFX(InitScene fxPanel) {
		Scene scene = fxPanel.createScene();
		JFXPanel panel = (JFXPanel) fxPanel;
		panel.setScene(scene);
	}
}
