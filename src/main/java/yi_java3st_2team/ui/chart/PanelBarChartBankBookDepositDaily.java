package yi_java3st_2team.ui.chart;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

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
		Scene scene = new Scene(root, Color.ALICEBLUE);
		root.setAutoSizeChildren(true);
		
		//막 대형 차트의 X 축과 Y 축을 정의하고 레이블을 설정
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("고객");

		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("거래량");

		barChart = new BarChart<>(xAxis, yAxis);
		barChart.setTitle("고객별 예금 일일 거래 내역 조회");
		
		barChart.setPrefSize(800, 600);
		barChart.setData(getChartData());
		root.getChildren().add(barChart);
		return scene;
	}
	public XYChart.Series<String, Number> getChartData(AccountInfo info) {
		JOptionPane.showMessageDialog(null, info);
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(AccountInfo info : list1) {
			if(info.getDiv().equals("예금")) {
				
			}
		}
		for(AccountInfo info : list2) {
			if(info.getDiv().equals("예금")) {
				
			}
		}
		for(AccountInfo info : list3) {
			if(info.getDiv().equals("예금")) {
				
			}
		}
		for(AccountInfo info : list4) {
			if(info.getDiv().equals("예금")) {
				
			}
		}
		for(AccountInfo info : list5) {
			if(info.getDiv().equals("예금")) {
				
			}
		}
		list.add(getChartData(accountInfo1));
		list.add(getChartData(accountInfo2));
		list.add(getChartData(accountInfo3));
		list.add(getChartData(accountInfo4));
		list.add(getChartData(accountInfo5));
		return list;
	}

}
