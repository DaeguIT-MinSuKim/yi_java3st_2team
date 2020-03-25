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
import yi_java3st_2team.ui.chart.InitScene;
import yi_java3st_2team.ui.service.BankBookService;
import yi_java3st_2team.ui.service.CardService;

@SuppressWarnings("serial")
public class PanelBarChartCardCheckWeekly extends JFXPanel implements InitScene{
	private BarChart<String, Number> barChart;
	private CardService service;
	public PanelBarChartCardCheckWeekly() {
		
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
		yAxis.setLabel("거래량");
		yAxis.autosize();
		yAxis.setTickLabelFont(new javafx.scene.text.Font(15));

		barChart = new BarChart<String, Number>(xAxis, yAxis);
		barChart.setTitle("고객별 체크카드 주간 거래 내역 조회");
		barChart.setPrefSize(1100, 500);
		barChart.setStyle("-fx-font-size: " + 20 + "px;");
		barChart.setData(getChartData());
		barChart.setLegendVisible(false);
		root.getChildren().add(barChart);
		return scene;
	}
	
	private ObservableList<XYChart.Series<String, Number>> getChartData() {
		ObservableList<Series<String, Number>> list = FXCollections.observableArrayList();
		service = new CardService();
		List<CardInfo> list1 = null;
		List<CardInfo> list2 = null;
		List<CardInfo> list3 = null;
		List<CardInfo> list4 = null;
		List<CardInfo> list5 = null;
		AccountInfo accountInfo1 = null;
		AccountInfo accountInfo2 = null;
		AccountInfo accountInfo3 = null;
		AccountInfo accountInfo4 = null;
		AccountInfo accountInfo5 = null;
		try {
			list1 = service.cardInfoWeekly("김가나");
			list2 = service.cardInfoWeekly("김다라");
			list3 = service.cardInfoWeekly("김마바");
			list4 = service.cardInfoWeekly("김사아");
			list5 = service.cardInfoWeekly("김자차");
			if(list1.size()==0) {
				accountInfo1 = new AccountInfo("김가나", 0);
			}
			else {
				for(CardInfo info : list1) {
					if(info.getDiv().equals("체크카드")) {
						accountInfo1 = new AccountInfo(info.getCustName(), info.getCount());
						break;
					}
					else {
						accountInfo1 = new AccountInfo("김가나", 0);
					}
				}
			}
			if(list2.size()==0) {
				accountInfo2 = new AccountInfo("김다라", 0);
			}
			else {
				for(CardInfo info : list2) {
					if(info.getDiv().equals("체크카드")) {
						accountInfo2 = new AccountInfo(info.getCustName(), info.getCount());
						break;
					}
					else {
						accountInfo2 = new AccountInfo("김다라", 0);
					}
				}
			}
			if(list3.size()==0) {
				accountInfo3 = new AccountInfo("김마바", 0);
			}
			else {
				for(CardInfo info : list3) {
					if(info.getDiv().equals("체크카드")) {
						accountInfo3 = new AccountInfo(info.getCustName(), info.getCount());
						break;
					}
					else {
						accountInfo3 = new AccountInfo("김마바", 0);
					}
				}
			}
			if(list4.size()==0) {
				accountInfo4 = new AccountInfo("김사아", 0);
			}
			else {
				for(CardInfo info : list4) {
					if(info.getDiv().equals("체크카드")) {
						accountInfo4 = new AccountInfo(info.getCustName(), info.getCount());
						break;
					}
					else {
						accountInfo4 = new AccountInfo("김사아", 0);
					}
				}
			}
			if(list5.size()==0) {
				accountInfo5 = new AccountInfo("김자차", 0);
			}
			else {
				for(CardInfo info : list5) {
					if(info.getDiv().equals("체크카드")) {
						accountInfo5 = new AccountInfo(info.getCustName(), info.getCount());
						break;
					}
					else {
						accountInfo5 = new AccountInfo("김자차", 0);
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XYChart.Series<String, Number> series = new Series<String, Number>();
		series.getData().add(new Data<String, Number>(accountInfo1.getCustName(), accountInfo1.getCount()));
		series.getData().add(new Data<String, Number>(accountInfo2.getCustName(), accountInfo2.getCount()));
		series.getData().add(new Data<String, Number>(accountInfo3.getCustName(), accountInfo3.getCount()));
		series.getData().add(new Data<String, Number>(accountInfo4.getCustName(), accountInfo4.getCount()));
		series.getData().add(new Data<String, Number>(accountInfo5.getCustName(), accountInfo5.getCount()));
		
		series.getData().get(0).nodeProperty().addListener(new ChangeListener<Node>() {
			@Override
			public void changed(ObservableValue<? extends Node> observable, Node oldValue, Node newValue) {
				newValue.setStyle("-fx-bar-fill: red;");
			}
		});
		series.getData().get(1).nodeProperty().addListener(new ChangeListener<Node>() {
			@Override
			public void changed(ObservableValue<? extends Node> observable, Node oldValue, Node newValue) {
				newValue.setStyle("-fx-bar-fill: orange;");
			}
		});
		series.getData().get(2).nodeProperty().addListener(new ChangeListener<Node>() {
			@Override
			public void changed(ObservableValue<? extends Node> observable, Node oldValue, Node newValue) {
				newValue.setStyle("-fx-bar-fill: yellow;");
			}
		});
		series.getData().get(3).nodeProperty().addListener(new ChangeListener<Node>() {
			@Override
			public void changed(ObservableValue<? extends Node> observable, Node oldValue, Node newValue) {
				newValue.setStyle("-fx-bar-fill: green;");
			}
		});
		series.getData().get(4).nodeProperty().addListener(new ChangeListener<Node>() {
			@Override
			public void changed(ObservableValue<? extends Node> observable, Node oldValue, Node newValue) {
				newValue.setStyle("-fx-bar-fill: blue;");
			}
		});
		
		list.add(series);
		return list;
	}

}
