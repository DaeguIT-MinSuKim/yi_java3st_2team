package yi_java3st_2team.ui.chart;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;

@SuppressWarnings("serial")
public class JFrameMonthlyDpWdBarChart extends JFrame implements ActionListener {

	private JPanel contentPane;
	private PanelMonthlyDepositOpenNumBarChart pDpBarChart;
	private PanelMonthlyWithDrawalOpenNumBarChart pWdBarChart;
	private Scene scene;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JFrameMonthlyDpWdBarChart() {
		initComponents();
	}
	
	private void initComponents() {
		setTitle("Chart 예제");
		setBounds(100, 50, 520, 980);  // (250+30) * 3 + 40
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		pDpBarChart = new PanelMonthlyDepositOpenNumBarChart();
		pWdBarChart = new PanelMonthlyWithDrawalOpenNumBarChart();
		contentPane.add(pDpBarChart);
		contentPane.add(pWdBarChart);
		
		Platform.runLater(() -> initFX(pDpBarChart));
		Platform.runLater(() -> initFX(pWdBarChart));
		
	}
	
	public void initFX(InitScene fxPanel) {
		scene = fxPanel.createScene();
		JFXPanel panel = (JFXPanel) fxPanel;
		panel.setScene(scene);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public Scene getScene() {
		return scene;
	}
	
	



}
