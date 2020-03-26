package yi_java3st_2team.ui.chart.bankbook;

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
import yi_java3st_2team.ui.chart.InitScene;
import yi_java3st_2team.ui.service.BankBookService;

@SuppressWarnings("serial")
public class PanelBarChartBankBookMinusYearly extends JFXPanel implements InitScene{
	private BarChart<String, Number> barChart;
	private BankBookService service;
	public PanelBarChartBankBookMinusYearly() {
		
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
		barChart.setTitle("고객별 마이너스통장 연간 거래 내역 조회");
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
		List<AccountInfo> infoList = null;
		try {
			infoList = service.bankBookInfoYearly();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XYChart.Series<String, Number> series = new Series<String, Number>();
		for(AccountInfo a : infoList) {
			if(a.getDiv().equals("마이너스")) {
				series.getData().add(new Data<String, Number>(a.getCustName(),a.getCount()));
			}
		}
		list.add(series);
		return list;
	}

}
