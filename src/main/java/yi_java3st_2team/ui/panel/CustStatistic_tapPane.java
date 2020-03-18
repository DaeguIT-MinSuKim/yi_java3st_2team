package yi_java3st_2team.ui.panel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import yi_java3st_2team.ui.chart.InitScene;
import yi_java3st_2team.ui.chart.PanelPieChart;

public class CustStatistic_tapPane extends JPanel {

	public CustStatistic_tapPane() {

		initialize();
	}
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel =new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane pane = new JTabbedPane(JTabbedPane.LEFT);
	//	PanelPieChart panel_fx = new PanelPieChart();
		addTabs(pane);
		panel.add(pane);
	}
	private void addTabs(JTabbedPane pane) {
		pane.addTab("Tab 1", getPanel());
		pane.addTab("Tab 2", new JLabel("menu2"));
		pane.addTab("Tab 3", new JLabel("menu3"));
		pane.addTab("Tab 4", new JLabel("menu4"));
		pane.addTab("Tab 5", new JLabel("menu5"));
		pane.addTab("Tab 6", new JLabel("menu6"));
		pane.addTab("Tab 7", new JLabel("menu7"));
		pane.addTab("Tab 8", new JLabel("menu8"));
		pane.addTab("Tab 9", new JLabel("menu9"));
		pane.addTab("Tab 10", new JLabel("menu10"));
		pane.addTab("Tab 11", new JLabel("menu11"));
		pane.addTab("Tab 12", new JLabel("menu12"));
	}
	private JPanel getPanel() {
		JPanel smPanel = new JPanel();
		EmptyBorder border = new EmptyBorder(100,300,100,300);
		Color bg = new Color(255,255,255,255);
		smPanel.setLayout(new BorderLayout(0,0));
		smPanel.setBackground(bg);
		smPanel.setBorder(border);
		PanelPieChart panel_fx = new PanelPieChart();
		smPanel.add(panel_fx, BorderLayout.CENTER);
		Platform.runLater(() -> initFX(panel_fx));
		return smPanel;
	}
	
	
	public void initFX(InitScene fxPanel) {
		Scene scene = fxPanel.createScene();
		JFXPanel panel = (JFXPanel) fxPanel;
		panel.setScene(scene);
	}

}
