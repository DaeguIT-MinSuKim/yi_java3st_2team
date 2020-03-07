package yi_java3st_2team.ui.uipanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.text.Text;
import yi_java3st_2team.ui.service.EmployeeUIService;

@SuppressWarnings("serial")
public class DlgStatisticCountEmp extends JDialog {
	private static PieChart pieChart;
	private static EmployeeUIService service = new EmployeeUIService();

	private final JPanel contentPanel = new JPanel();
	private static double numOfCS;
	private static double numOfHR;
	private static int totalnum;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
		
			DlgStatisticCountEmp dialog = new DlgStatisticCountEmp();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgStatisticCountEmp() {
		
		    totalnum = service.countAllEmpNum();
		    System.out.println(totalnum);
			numOfHR = service.countMemberByDepartment(1);			
			numOfCS = service.countMemberByDepartment(2);
		
		setBounds(700,50,600,600);
		
		final JFXPanel fxPanel = new JFXPanel();
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.add(fxPanel, BorderLayout.CENTER); 
		
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		Platform.runLater(() -> initFX(fxPanel));
		
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private static void initFX(JFXPanel fxPanel) {
		Scene scene = createScene();
		fxPanel.setScene(scene);
	}

	private static Scene createScene() {
		Group root = new Group();
		Scene scene = new Scene(root);
		root.setAutoSizeChildren(true);
		
		pieChart = new PieChart() {
			@Override
			protected void layoutChartChildren(double top, double left, double contentWidth, double contentHeight) {
				super.layoutChartChildren(top, left, contentWidth, contentHeight);
				if (getLabelsVisible()) {
					getData().forEach(d -> {
						Optional<Node> opTextNode = pieChart.lookupAll(".chart-pie-label").stream()
								.filter(n -> n instanceof Text && ((Text) n).getText().contains(d.getName())).findAny();
						if (opTextNode.isPresent()) {
							((Text) opTextNode.get()).setText(d.getName() + " " + d.getPieValue() + " %");
						}
					});
				}
				
			}
		};
		
		pieChart.setPrefSize(550, 500);
		pieChart.setData(getChartData());
		pieChart.setTitle("Employee Pie Chart");
		pieChart.setLegendSide(Side.BOTTOM);// 범례 위치
		pieChart.setLabelLineLength(30);// 원의 둘레 가장자리와 라벨간의 거리 지정
		pieChart.setClockwise(true); // 시계방향 배치여부
		pieChart.setLabelsVisible(true);// 레이블 표시여부

		root.getChildren().add(pieChart);

		return scene;
	}
	
	private static ObservableList<Data> getChartData() {
		       
		
		ObservableList<Data> answer = FXCollections.observableArrayList();
		
		
		answer.addAll(new PieChart.Data("인사", (numOfHR/totalnum)*100), new PieChart.Data("고객", (numOfCS/totalnum)*100));
		return answer;
	}

}
