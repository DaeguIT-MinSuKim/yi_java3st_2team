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

@SuppressWarnings("serial")
abstract public class AbsCenterStatisticNortPanel_test extends JPanel {
	protected JPanel panel;

	public AbsCenterStatisticNortPanel_test() {
		initialize();
	}
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		panel = new JPanel();
		add(panel);
}

	public void setBtns(JButton...button) {
		for(int i=0; i<button.length; i++) {
			panel.add(button[i]);
		}
		panel.revalidate();
		panel.repaint();
	}
	
	protected abstract String[] getText();
}
