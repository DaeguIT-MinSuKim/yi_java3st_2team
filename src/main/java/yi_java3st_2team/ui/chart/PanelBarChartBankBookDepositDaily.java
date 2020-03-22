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
import javafx.scene.chart.XYChart.Series;
import javafx.scene.paint.Color;
import yi_java3st_2team.dto.AccountInfo;
import yi_java3st_2team.ui.service.BankBookService;

@SuppressWarnings("serial")
public class PanelBarChartBankBookDepositDaily extends JFXPanel implements InitScene{
	private BarChart<String, Number> barChart;
	private BankBookService service;
	public PanelBarChartBankBookDepositDaily() {
		
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

		barChart = new BarChart<>(xAxis, yAxis);
		barChart.setTitle("고객별 예금 일일 거래 내역 조회");
		barChart.setLegendSide(Side.BOTTOM);
		barChart.setPrefSize(1100, 500);
		barChart.setStyle("-fx-font-size: " + 20 + "px;");
		barChart.setData(getChartData());
		root.getChildren().add(barChart);
		return scene;
	}
	public XYChart.Series<String, Number> getChartData(AccountInfo info) {
		XYChart.Series<String, Number> dataSeries = new Series<String, Number>();
		dataSeries.getData().add(new XYChart.Data<>(info.getCustName(), info.getCount()));
		return dataSeries;
	}
	
	private ObservableList<XYChart.Series<String, Number>> getChartData() {
		ObservableList<XYChart.Series<String, Number>> list = FXCollections.observableArrayList();
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
			list1 = service.bankBookInfoDaily("김가나");
			list2 = service.bankBookInfoDaily("김다라");
			list3 = service.bankBookInfoDaily("김마바");
			list4 = service.bankBookInfoDaily("김사아");
			list5 = service.bankBookInfoDaily("김자차");
			if(list1.size()==0) {
				accountInfo1 = new AccountInfo("김가나", 0);
			}
			else {
				for(AccountInfo info : list1) {
					if(info.getDiv().equals("예금")) {
						accountInfo1 = new AccountInfo(info.getCustName(), info.getCount());
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
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list.add(getChartData(accountInfo1));
		list.add(getChartData(accountInfo2));
		list.add(getChartData(accountInfo3));
		list.add(getChartData(accountInfo4));
		list.add(getChartData(accountInfo5));
		return list;
	}

}
