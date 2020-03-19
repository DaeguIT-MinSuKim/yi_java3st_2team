package yi_java3st_2team.ui.absPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public abstract class AbsCenterNorthSearchPanel<T> extends JPanel {
	private JButton btnSearch;
	private JButton btnCancel;
	private JPanel pBtn;
	private JTextField tfSearch;
	private JComboBox cmbSearchList;
	private JPanel panel;
	private JPanel panel_1;
	
	
	public AbsCenterNorthSearchPanel() {
		initialize();
	}
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel pSearch = new JPanel();
		pSearch.setBorder(new EmptyBorder(20, 20, 20, 20));
		add(pSearch, BorderLayout.CENTER);
		pSearch.setLayout(new GridLayout(0, 2, 20, 20));
		
		cmbSearchList = new JComboBox(setSearchList());
		pSearch.add(cmbSearchList);
		
		panel = new JPanel();
		panel.setVisible(false);
		pSearch.add(panel);
		
		panel_1 = new JPanel();
		panel_1.setVisible(false);
		pSearch.add(panel_1);
		
		pBtn = new JPanel();
		add(pBtn, BorderLayout.SOUTH);
		pBtn.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnSearch = new JButton("조회");
		btnSearch.setFont(new Font("맑은 고딕",Font.BOLD,15));
		pBtn.add(btnSearch);
		
		btnCancel = new JButton("취소");
		btnCancel.setFont(new Font("맑은 고딕",Font.BOLD,15));
		pBtn.add(btnCancel);
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
	abstract protected void tfClear();
}
