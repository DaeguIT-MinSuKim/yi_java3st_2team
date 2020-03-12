package yi_java3st_2team.ui.chart;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javafx.application.Platform;
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

public class JFrameBarChartByCardPlan {
	private static BarChart<String, Number> barChart;
	private static CardService service;
	public static void initAndShowGUI() {
		service = new CardService();
		JFrame frame = new JFrame();
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

	private static Scene createScene() {
		Group root = new Group();
		Scene scene = new Scene(root, Color.ALICEBLUE);

		//막 대형 차트의 X 축과 Y 축을 정의하고 레이블을 설정
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("고객");

		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("카드 상품");

		barChart = new BarChart<>(xAxis, yAxis);
		barChart.setTitle("고객별 카드 상품");
		
		barChart.setPrefSize(500, 400);
		try {
			for(CardInfo c : service.showCardInfo()) {
				barChart.getData().add(getBarChartData(c));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		root.getChildren().add(barChart);

		return scene;
	}

	public static XYChart.Series<String, Number> getBarChartData(CardInfo c) {
		XYChart.Series<String, Number> dataSeries = new Series<String, Number>();
		dataSeries.setName(c.getCustname());
		dataSeries.getData().add(new XYChart.Data<>("체크카드", c.getCheck()));
		dataSeries.getData().add(new XYChart.Data<>("신용카드", c.getCredit()));
		return dataSeries;
	}
	
}
