package yi_java3st_2team.ui.chart;

import java.sql.SQLException;

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

@SuppressWarnings("serial")
public class PanelPieChart extends JFXPanel implements InitScene{
	
	private PieChart pieChart;
	private CustomerService service = new CustomerService();
	
	@Override
	public Scene createScene() {
		Group root = new Group();
		Scene scene = new Scene(root);
		root.setAutoSizeChildren(true);
		
		pieChart = new PieChart();
		pieChart.setPrefSize(600, 350);
		pieChart.setData(getChartData());
		pieChart.setTitle("일반 고객 대비 VIP 고객(%)");
		pieChart.setLegendVisible(true);	// 범례 표시 유무
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
		ObservableList<Data> list = FXCollections.observableArrayList();
		try {
			PieChart.Data data = new PieChart.Data("고객(VIP제외)", (service.showNormalCustNum())*20);
			PieChart.Data data2 = new PieChart.Data("VIP고객", (service.showVIPCustNum())*20);
			list.addAll(data, data2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	
	
}
