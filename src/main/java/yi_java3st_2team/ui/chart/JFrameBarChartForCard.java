package yi_java3st_2team.ui.chart;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;

import javafx.application.Platform;
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
import yi_java3st_2team.dto.CardInfo;
import yi_java3st_2team.ui.service.CardService;

public class JFrameBarChartForCard {
	private static CardService service;
	private static BarChart<String, Number> barChart;
	public static void initAndShowGUI() {
		service = new CardService();
		JFrame frame = new JFrame("Swing and JavaFX");
		frame.setBounds(620, 50, 500, 500);
		
		final JFXPanel fxPanel = new JFXPanel();

		frame.add(fxPanel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		Platform.runLater(() -> initFX(fxPanel));
	}
	
	private static void initFX(JFXPanel fxPanel) {
		Scene scene = createScene();
		fxPanel.setScene(scene);
	}

	public static Scene createScene() {
		Group root = new Group();
		Scene scene = new Scene(root, Color.ALICEBLUE);
		root.setAutoSizeChildren(true);
		
		//막 대형 차트의 X 축과 Y 축을 정의하고 레이블을 설정
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("고객이름");

		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("건수");

		barChart = new BarChart<>(xAxis, yAxis);
		barChart.setTitle("Bar Chart");
		
		barChart.setPrefSize(500, 250);
		try {
			barChart.setData(getChartData());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		root.getChildren().add(barChart);

		return scene;
	}

	private static ObservableList<XYChart.Series<String, Number>> getChartData() throws SQLException {
		ObservableList<XYChart.Series<String, Number>> list = FXCollections.observableArrayList();
		CardInfo data1 = service.cardInfo("김가나");
		CardInfo data2 = service.cardInfo("김다라");
		CardInfo data3 = service.cardInfo("김마바");
		CardInfo data4 = service.cardInfo("김사아");
		CardInfo data5 = service.cardInfo("김자차");
		list.add(getChartData(data1));
		list.add(getChartData(data2));
		list.add(getChartData(data3));
		list.add(getChartData(data4));
		list.add(getChartData(data5));
		return list;
	}
	public static XYChart.Series<String, Number> getChartData(CardInfo cardInfo) {
		XYChart.Series<String, Number> dataSeries = new Series<String, Number>();
		dataSeries.getData().add(new XYChart.Data<>(cardInfo.getCustname(), cardInfo.getTransCount()));
		return dataSeries;
	}
	
}
