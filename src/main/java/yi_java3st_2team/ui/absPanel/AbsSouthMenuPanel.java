package yi_java3st_2team.ui.absPanel;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

@SuppressWarnings("serial")
abstract public class AbsSouthMenuPanel extends JPanel {
	protected JPanel pMenu1;
	protected JPanel pMenu2;
	protected JLabel lblMenu1;
	protected JLabel lblMenu2;
	public AbsSouthMenuPanel() {
		initialize();
	}
	private void initialize() {
		setLayout(new GridLayout(0, 2, 0, 0));
		
		pMenu1 = new JPanel();
		add(pMenu1);
		pMenu1.setLayout(new BorderLayout(0, 0));
		
		lblMenu1 = new JLabel();
		lblMenu1.setHorizontalAlignment(SwingConstants.CENTER);
		pMenu1.add(lblMenu1, BorderLayout.CENTER);
		
		pMenu2 = new JPanel();
		add(pMenu2);
		pMenu2.setLayout(new BorderLayout(0, 0));
		
		lblMenu2 = new JLabel();
		lblMenu2.setHorizontalAlignment(SwingConstants.CENTER);
		pMenu2.add(lblMenu2, BorderLayout.CENTER);
		
	}
	
	public JLabel getLblMenu1() {
		return lblMenu1;
	}
	public JLabel getLblMenu2() {
		return lblMenu2;
	}
	protected void setPanelInit(JPanel ...menu) {
		for(JPanel p : menu) {
			p.setBackground(new Color(18,66,43));
		}
	}
	protected void setLabelInit(JLabel ... menu) {
		String[] texts = getTexts();
		for(int i=0;i<menu.length;i++) {
			menu[i].setFont(new Font("맑은 고딕",Font.BOLD,20));
			menu[i].setForeground(Color.white);
			menu[i].setBackground(new Color(18,66,43));
			menu[i].setOpaque(true);
			menu[i].setText(texts[i]);
		}
	}
	protected abstract String[] getTexts();
}
