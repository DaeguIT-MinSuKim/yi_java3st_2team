package yi_java3st_2team.ui.designPanel;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import yi_java3st_2team.ui.chart.InitScene;

@SuppressWarnings("serial")
public class JFrameMonthlyDpWdBarChart extends JFrame implements ActionListener {

	private JPanel contentPane;
	private PanelMonthlyDepositOpenNumBarChart pDpBarChart;
	private PanelMonthlyWithDrawalOpenNumBarChart pWdBarChart;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameMonthlyDpWdBarChart frame = new JFrameMonthlyDpWdBarChart();
					frame.setVisible(true);
					
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
		contentPane.add(pDpBarChart);
		
		pWdBarChart = new PanelMonthlyWithDrawalOpenNumBarChart();
		contentPane.add(pWdBarChart);
		
		Platform.runLater(() -> initFX(pDpBarChart));
		Platform.runLater(() -> initFX(pWdBarChart));
		
	}
	
	public void initFX(InitScene fxPanel) {
		Scene scene = fxPanel.createScene();
		JFXPanel panel = (JFXPanel) fxPanel;
		panel.setScene(scene);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}



}