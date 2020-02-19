package yi_java3st_2team.ui.absPanel;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public abstract class AbsCenterNorthMenuPanel extends JPanel {
	protected JLabel lblMenu1;
	protected JLabel lblMenu2;
	public AbsCenterNorthMenuPanel() {
		initialize();
	}
	private void initialize() {
		setBorder(new EmptyBorder(20, 20, 20, 20));
		setLayout(new GridLayout(0, 2, 50, 0));
		
		lblMenu1 = new JLabel("");
		add(lblMenu1);
		
		lblMenu2 = new JLabel("");
		add(lblMenu2);
	}
	
	public JLabel getLblMenu1() {
		return lblMenu1;
	}
	public JLabel getLblMenu2() {
		return lblMenu2;
	}
	protected void setLabelInit(JLabel ... menu) {
		String[] texts = getTexts();
		for(int i=0;i<menu.length;i++) {
			menu[i].setFont(new Font("굴림",Font.BOLD,25));
			menu[i].setForeground(Color.white);
			menu[i].setBackground(new Color(18,66,43));
			menu[i].setOpaque(true);
			menu[i].setText(texts[i]);
		}
	}
	protected abstract String[] getTexts();
}
