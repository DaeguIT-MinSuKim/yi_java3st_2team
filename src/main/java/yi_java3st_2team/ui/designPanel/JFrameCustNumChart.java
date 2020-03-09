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

@SuppressWarnings("serial")
public class JFrameCustNumChart extends JFrame implements ActionListener {

	private JPanel contentPane;
	private PanelPieChart pPieChart;
	private PanelBarChart pBarChart;
	private PanelCustNumAll custNumAll;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameCustNumChart frame = new JFrameCustNumChart();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JFrameCustNumChart() {
		initComponents();
	}
	
	private void initComponents() {
		setTitle("Chart 예제");
		setBounds(100, 50, 520, 980);  // (250+30) * 3 + 40
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		pPieChart = new PanelPieChart();
		contentPane.add(pPieChart);
		
		pBarChart = new PanelBarChart();
		contentPane.add(pBarChart);
		
		custNumAll = new PanelCustNumAll();
		contentPane.add(custNumAll);
		
		Platform.runLater(() -> initFX(pPieChart));
		Platform.runLater(() -> initFX(pBarChart));
		
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
