package yi_java3st_2team.ui.absPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import yi_java3st_2team.ui.chart.InitScene;
import yi_java3st_2team.ui.chart.PanelBarChart;
import yi_java3st_2team.ui.chart.PanelPieChart;

public abstract class AbsStatisticTabPanel extends JPanel {
	public AbsStatisticTabPanel() {

		initialize();
	}
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel =new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane pane = new JTabbedPane(JTabbedPane.LEFT);
		addTabs(pane);
		int count = pane.getTabCount();
		
		panel.add(pane);
	}
	
	private void addTabs(JTabbedPane pane) {
		List<JFXPanel> list = getJFXs();
		for(int i=0; i<list.size(); i++) {
			pane.addTab(list.get(i).getName(), getPanel(list.get(i)));
		}
	
	}
	
	protected List<JFXPanel> getJFXs() {
		List<JFXPanel> list = new ArrayList<>();
		
		PanelPieChart panel_fx = new PanelPieChart();
		PanelBarChart barchart = new PanelBarChart();
		
		panel_fx.setName("통계");
		barchart.setName("통계2");
		
		list.add(panel_fx);
		list.add(barchart);
		
		Platform.runLater(() -> initFX(panel_fx));
		Platform.runLater(() -> initFX(barchart));
		
		return list;
	}
	
	protected JPanel getPanel_1() {
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
	
	protected JPanel getPanel(JFXPanel panel_fx) {
		JPanel smPanel = new JPanel();
		EmptyBorder border = new EmptyBorder(100,300,100,300);
		Color bg = new Color(255,255,255,255);
		smPanel.setLayout(new BorderLayout(0,0));
		smPanel.setBackground(bg);
		smPanel.setBorder(border);
		smPanel.add(panel_fx, BorderLayout.CENTER);
		return smPanel;
	}
	
	
	public void initFX(InitScene fxPanel) {
		Scene scene = fxPanel.createScene();
		JFXPanel panel = (JFXPanel) fxPanel;
		panel.setScene(scene);
	}
	
}
