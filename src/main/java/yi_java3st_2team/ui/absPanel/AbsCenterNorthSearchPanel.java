package yi_java3st_2team.ui.absPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public abstract class AbsCenterNorthSearchPanel<T> extends JPanel {
	private JButton btnSearch;
	private JButton btnCancel;
	private JPanel pBtn;
	private JTextField tfSearch;
	private JComboBox cmbSearchList;
	private JPanel panel;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_8;
	private JPanel panel_9;
	private JPanel panel_10;
	private JPanel pSearch;
	
	
	public AbsCenterNorthSearchPanel() {
		initialize();
	}
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		pSearch = new JPanel();
		//pSearch.setBorder(new TitledBorder(new LineBorder(new Color(240,240,240)), "고객 정보 관리 > 고객 개인 정보", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));
		add(pSearch, BorderLayout.CENTER);
		pSearch.setLayout(new GridLayout(0, 4, 20, 20));
		
		panel = new JPanel();
		panel.setVisible(false);
		pSearch.add(panel);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new EmptyBorder(10, 0, 10, 0));
		pSearch.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		cmbSearchList = new JComboBox(setSearchList());
		panel_2.add(cmbSearchList);
		
		panel_3 = new JPanel();
		panel_3.setBorder(new EmptyBorder(10, 0, 10, 0));
		pSearch.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));
		
		tfSearch = new JTextField();
		panel_3.add(tfSearch);
		tfSearch.setColumns(10);
		
		panel_4 = new JPanel();
		pSearch.add(panel_4);
		
		pBtn = new JPanel();
		add(pBtn, BorderLayout.SOUTH);
		pBtn.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnSearch = new JButton("조회");
		btnSearch.setFont(new Font("맑은 고딕",Font.BOLD,15));
		pBtn.add(btnSearch);
		
		btnCancel = new JButton("취소");
		btnCancel.setFont(new Font("맑은 고딕",Font.BOLD,15));
		pBtn.add(btnCancel);
		
		panel_8 = new JPanel();
		add(panel_8, BorderLayout.NORTH);
		
		panel_9 = new JPanel();
		add(panel_9, BorderLayout.WEST);
		
		panel_10 = new JPanel();
		add(panel_10, BorderLayout.EAST);
	}
	
	
	
	public JPanel getpSearch() {
		return pSearch;
	}
	public JTextField getTfSearch() {
		return tfSearch;
	}
	public void setTfSearch(JTextField tfSearch) {
		this.tfSearch = tfSearch;
	}
	public JComboBox getCmbSearchList() {
		return cmbSearchList;
	}
	public void setCmbSearchList(JComboBox cmbSearchList) {
		this.cmbSearchList = cmbSearchList;
	}
	
	public abstract String[] setSearchList();

	public JButton getBtnSearch() {
		return btnSearch;
	}
	public JButton getBtnCancel() {
		return btnCancel;
	}
	
	public void changeTitleBorder(String title) {
		pSearch.setBorder(new TitledBorder(new LineBorder(new Color(240,240,240)), title, TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));
	}
	
	abstract protected void tfClear();
	
	
	
}
