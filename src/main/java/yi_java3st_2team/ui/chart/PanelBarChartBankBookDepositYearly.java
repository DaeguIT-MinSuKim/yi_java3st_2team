package yi_java3st_2team.ui.chart;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JOptionPane;

import com.sun.javafx.charts.Legend;
import com.sun.javafx.charts.Legend.LegendItem;
import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;

import javafx.beans.InvalidationListener;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Side;
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
import yi_java3st_2team.ui.service.BankBookService;

@SuppressWarnings("serial")
public class PanelBarChartBankBookDepositYearly extends JFXPanel implements InitScene{
	private BarChart<String, Number> barChart;
	private BankBookService service;
	public PanelBarChartBankBookDepositYearly() {
		
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
		barChart.setTitle("고객별 예금 연간 거래 내역 조회");
		barChart.setPrefSize(1100, 500);
		barChart.setStyle("-fx-font-size: " + 20 + "px;");
		barChart.setLegendVisible(false);
		barChart.setData(getChartData());
		root.getChildren().add(barChart);
		return scene;
	}
	
	private ObservableList<Series<String, Number>> getChartData() {
		ObservableList<Series<String, Number>> list = FXCollections.observableArrayList();
		service = new BankBookService();
		List<AccountInfo> list1 = null;
		List<AccountInfo> list2 = null;
		List<AccountInfo> list3 = null;
		List<AccountInfo> list4 = null;
		List<AccountInfo> list5 = null;
		AccountInfo accountInfo1 = null;
		AccountInfo accountInfo2 = null;
		AccountInfo accountInfo3 = null;
		AccountInfo accountInfo4 = null;
		AccountInfo accountInfo5 = null;
		try {
			list1 = service.bankBookInfoYearly("김가나");
			list2 = service.bankBookInfoYearly("김다라");
			list3 = service.bankBookInfoYearly("김마바");
			list4 = service.bankBookInfoYearly("김사아");
			list5 = service.bankBookInfoYearly("김자차");
			if(list1.size()==0) {
				accountInfo1 = new AccountInfo("김가나", 0);
			}
			else {
				for(AccountInfo info : list1) {
					if(info.getDiv().equals("예금")) {
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
				for(AccountInfo info : list2) {
					if(info.getDiv().equals("예금")) {
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
				for(AccountInfo info : list3) {
					if(info.getDiv().equals("예금")) {
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
				for(AccountInfo info : list4) {
					if(info.getDiv().equals("예금")) {
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
				for(AccountInfo info : list5) {
					if(info.getDiv().equals("예금")) {
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
