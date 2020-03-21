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
import java.awt.FlowLayout;
import javax.swing.border.MatteBorder;

@SuppressWarnings("serial")
abstract public class AbsCenterStatisticNortPanel_test extends JPanel {
	protected JPanel panel;
	protected JButton[] buttons;
	public AbsCenterStatisticNortPanel_test() {
		initialize();
	}
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new EmptyBorder(20, 20, 20, 20));
		add(panel);
		panel.setLayout(new GridLayout(0, 2, 20, 20));
}

	public void setBtns() {
		String[] buttonNames = getText();
		buttons = new JButton[buttonNames.length];
		for(int i=0; i<buttonNames.length; i++) {
			buttons[i] = new JButton(buttonNames[i]);
			buttons[i].setFont(new Font("굴림", Font.BOLD, 15));
			panel.add(buttons[i]);
		}
	}
	public abstract JButton[] getBtns();
	
	protected abstract String[] getText();
}
