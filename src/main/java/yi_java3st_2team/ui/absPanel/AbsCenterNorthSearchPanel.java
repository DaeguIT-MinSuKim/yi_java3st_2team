package yi_java3st_2team.ui.absPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;

@SuppressWarnings("serial")
public abstract class AbsCenterNorthSearchPanel<T> extends JPanel {
	private JTextField tfSearch;
	private JLabel lblSearch;

	public AbsCenterNorthSearchPanel() {
		initialize();
	}
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel pSearch = new JPanel();
		pSearch.setBorder(new EmptyBorder(20, 20, 20, 20));
		add(pSearch, BorderLayout.CENTER);
		pSearch.setLayout(new GridLayout(0, 2, 20, 20));
		
		lblSearch = new JLabel("");
		lblSearch.setForeground(Color.white);
		lblSearch.setBackground(new Color(18,66,43));
		lblSearch.setFont(new Font("맑은 고딕",Font.BOLD,25));
		lblSearch.setOpaque(true);
		pSearch.add(lblSearch);
		
		tfSearch = new JTextField();
		pSearch.add(tfSearch);
		tfSearch.setColumns(10);
		
		JPanel pBtn = new JPanel();
		add(pBtn, BorderLayout.SOUTH);
		pBtn.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnSearch = new JButton("조회");
		btnSearch.setFont(new Font("맑은 고딕",Font.BOLD,20));
		pBtn.add(btnSearch);
		
		JButton btnCancel = new JButton("취소");
		btnCancel.setFont(new Font("맑은 고딕",Font.BOLD,20));
		pBtn.add(btnCancel);
	}
	protected void setText(String text) {
		lblSearch.setText(text);
	}
	abstract protected void tfClear();
}
