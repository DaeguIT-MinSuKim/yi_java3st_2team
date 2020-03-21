package yi_java3st_2team.ui.absPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import yi_java3st_2team.ui.chart.InitScene;

import java.awt.Dimension;
import javax.swing.border.MatteBorder;

@SuppressWarnings("serial")
abstract public class AbsCenterStatisticCenterPanel_test extends JPanel {
	protected JPanel center_panel;

	public AbsCenterStatisticCenterPanel_test() {
		initialize();
	}
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		center_panel = new JPanel();
		center_panel.setBorder(new EmptyBorder(20, 20, 20, 20));
		center_panel.setBackground(Color.WHITE);
		add(center_panel, BorderLayout.CENTER);
		center_panel.setLayout(new BorderLayout(0, 0));
	}
}
