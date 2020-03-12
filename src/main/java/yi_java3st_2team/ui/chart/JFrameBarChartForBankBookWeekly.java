package yi_java3st_2team.ui.chart;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalExclusionType;
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
import yi_java3st_2team.dto.Info;
import yi_java3st_2team.ui.service.BankBookService;
import yi_java3st_2team.ui.service.CardService;

public class JFrameBarChartForBankBookWeekly {
	private static BankBookService service;
	private static BarChart<String, Number> barChart;
	public static void initAndShowGUI() {
		service = new BankBookService();
		JFrame frame = new JFrame();
		frame.setBounds(620, 50, 500, 500);
		
		final JFXPanel fxPanel = new JFXPanel();

		frame.add(fxPanel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setModalExclusionType(ModalExclusionType.NO_EXCLUDE);

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
		barChart.setTitle("주간 거래 내역");
		barChart.setLegendVisible(false);
		
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
		Info data1 = service.bankBookInfoWeekly("김가나");
		Info data2 = service.bankBookInfoWeekly("김다라");
		Info data3 = service.bankBookInfoWeekly("김마바");
		Info data4 = service.bankBookInfoWeekly("김사아");
		Info data5 = service.bankBookInfoWeekly("김자차");
		list.add(getChartData(data1));
		list.add(getChartData(data2));
		list.add(getChartData(data3));
		list.add(getChartData(data4));
		list.add(getChartData(data5));
		return list;
	}
	public static XYChart.Series<String, Number> getChartData(Info cardInfo) {
		XYChart.Series<String, Number> dataSeries = new Series<String, Number>();
		dataSeries.getData().add(new XYChart.Data<>(cardInfo.getCustname(), cardInfo.getTransCount()));
		return dataSeries;
	}
	
}
