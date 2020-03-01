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

@SuppressWarnings("serial")
abstract public class AbsCenterStatisticPanel extends JPanel {
	protected JLabel lblStat1;
	protected JLabel lblStat2;
	protected JLabel lblStat3;
	protected JLabel lblStat4;
	protected JLabel lblStat5;
	protected JLabel lblStat6;
	protected JLabel lblStat7;
	protected JLabel lblStat8;

	public AbsCenterStatisticPanel() {

		initialize();
	}
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel pCenter = new JPanel();
		pCenter.setBorder(new EmptyBorder(20, 20, 20, 20));
		add(pCenter, BorderLayout.CENTER);
		pCenter.setLayout(new GridLayout(0, 1, 10, 10));
		
		lblStat1 = new JLabel("");
		pCenter.add(lblStat1);
		
		lblStat2 = new JLabel("");
		pCenter.add(lblStat2);
		
		lblStat3 = new JLabel("");
		pCenter.add(lblStat3);
		
		lblStat4 = new JLabel("");
		pCenter.add(lblStat4);
		
		lblStat5 = new JLabel("");
		pCenter.add(lblStat5);
		
		lblStat6 = new JLabel("");
		pCenter.add(lblStat6);
		
		lblStat7 = new JLabel("");
		pCenter.add(lblStat7);
		
		lblStat8 = new JLabel("");
		pCenter.add(lblStat8);
		
		JPanel pSouth = new JPanel();
		pSouth.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(pSouth, BorderLayout.SOUTH);
		pSouth.setLayout(new BorderLayout(0, 0));
		
		JButton btnSearch = new JButton("조회");
		btnSearch.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		pSouth.add(btnSearch, BorderLayout.EAST);
	}
	
	protected void setLabelInit(JLabel ... menu) {
		String[] texts = getTexts();
		for(int i=0;i<menu.length;i++) {
			menu[i].setFont(new Font("맑은 고딕",Font.PLAIN,20));
			menu[i].setText(texts[i]);
		}
	}
	protected void setLblMouseListener(JLabel... menus) {
		MouseListener myLblListener = new MouseAdapter() {
			@Override 
			public void mouseClicked(MouseEvent e) {
				for(JLabel menu : menus) {
					menu.setForeground(Color.BLACK);
				}
				JLabel selMenu = (JLabel)e.getSource();
				selMenu.setForeground(new Color(254,208,64));
			}
		};
		for(int i=0;i<menus.length;i++) {
			menus[i].addMouseListener(myLblListener);
		}
	}
	abstract protected String[] getTexts();
}
