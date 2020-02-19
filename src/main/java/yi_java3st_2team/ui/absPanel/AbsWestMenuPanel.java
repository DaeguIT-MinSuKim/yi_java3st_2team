package yi_java3st_2team.ui.absPanel;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

@SuppressWarnings("serial")
public abstract class AbsWestMenuPanel extends JPanel {
	protected JLabel lblMenu1;
	protected JLabel lblMenu2;
	protected JLabel lblMenu3;
	protected JLabel lblMenu4;
	protected JLabel lblMenu5;
	protected JPanel pMenu1;
	protected JPanel pMenu2;
	protected JPanel pMenu3;
	protected JPanel pMenu4;
	protected JPanel pMenu5;
	public AbsWestMenuPanel() {
		initialize();
	}
	private void initialize() {
		setLayout(new GridLayout(0, 1, 0, 0));
		
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
		
		pMenu3 = new JPanel();
		add(pMenu3);
		pMenu3.setLayout(new BorderLayout(0, 0));
		
		lblMenu3 = new JLabel();
		lblMenu3.setHorizontalAlignment(SwingConstants.CENTER);
		pMenu3.add(lblMenu3, BorderLayout.CENTER);
		
		pMenu4 = new JPanel();
		add(pMenu4);
		pMenu4.setLayout(new BorderLayout(0, 0));
		
		lblMenu4 = new JLabel();
		lblMenu4.setHorizontalAlignment(SwingConstants.CENTER);
		pMenu4.add(lblMenu4, BorderLayout.CENTER);
		
		pMenu5 = new JPanel();
		add(pMenu5);
		pMenu5.setLayout(new BorderLayout(0, 0));
		
		lblMenu5 = new JLabel();
		lblMenu5.setHorizontalAlignment(SwingConstants.CENTER);
		pMenu5.add(lblMenu5, BorderLayout.CENTER);
		setPanelInit(pMenu1,pMenu2,pMenu3,pMenu4,pMenu5);
		
	}
	
	public JLabel getLblMenu1() {
		return lblMenu1;
	}
	public JLabel getLblMenu2() {
		return lblMenu2;
	}
	public JLabel getLblMenu3() {
		return lblMenu3;
	}
	public JLabel getLblMenu4() {
		return lblMenu4;
	}
	public JLabel getLblMenu5() {
		return lblMenu5;
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
