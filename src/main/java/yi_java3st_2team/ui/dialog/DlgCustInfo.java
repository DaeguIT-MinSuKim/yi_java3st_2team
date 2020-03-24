package yi_java3st_2team.ui.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import yi_java3st_2team.dto.Customer;
import yi_java3st_2team.ui.panel.CustInfoUIPanel;
import yi_java3st_2team.ui.service.CustomerService;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

public class DlgCustInfo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfCustCode;
	private JTextField tfCustName;
	private JTextField tfCustAddr;
	private JTextField tfCustTel;
	private JComboBox cmbCustRank;
	private JComboBox cmbCustCredit;
	private CustInfoUIPanel custInfoUI = new CustInfoUIPanel();
	private String[] rankList = {"Bronze", "Silver", "Gold", "Platinum", "Diamond"};
	private String[] creditList = {"1","2","3","4","5"};
	private JButton okButton;	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgCustInfo dialog = new DlgCustInfo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgCustInfo() {
		initialize();
	}
	
	

	public JButton getOkButton() {
		return okButton;
	}
	
	
	
	
	
	public JPanel getContentPanel() {
		return contentPanel;
	}

	public JTextField getTfCustCode() {
		return tfCustCode;
	}

	public JTextField getTfCustName() {
		return tfCustName;
	}

	public JTextField getTfCustAddr() {
		return tfCustAddr;
	}

	public JTextField getTfCustTel() {
		return tfCustTel;
	}

	public JComboBox getCmbCustRank() {
		return cmbCustRank;
	}

	public JComboBox getCmbCustCredit() {
		return cmbCustCredit;
	}

	public CustInfoUIPanel getCustInfoUI() {
		return custInfoUI;
	}

	public String[] getRankList() {
		return rankList;
	}

	public String[] getCreditList() {
		return creditList;
	}

	private void initialize() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 2, 0, 0));
		{
			JLabel lblCustCode = new JLabel("코드");
			lblCustCode.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblCustCode);
		}
		{
			tfCustCode = new JTextField();
			tfCustCode.setEditable(false);
			contentPanel.add(tfCustCode);
			tfCustCode.setColumns(10);
		}
		{
			JLabel lblCustName = new JLabel("이름");
			lblCustName.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblCustName);
		}
		{
			tfCustName = new JTextField();
			tfCustName.setColumns(10);
			contentPanel.add(tfCustName);
		}
		{
			JLabel lblCustRank = new JLabel("등급");
			lblCustRank.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblCustRank);
		}
		{
			cmbCustRank = new JComboBox(rankList);
			cmbCustRank.setSelectedIndex(-1);
			
			contentPanel.add(cmbCustRank);
		}
		{
			JLabel lblCustCredit = new JLabel("신용등급");
			lblCustCredit.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblCustCredit);
		}
		{
			cmbCustCredit = new JComboBox(creditList);
			cmbCustCredit.setSelectedIndex(-1);
			contentPanel.add(cmbCustCredit);
		}
		{
			JLabel lblCustAddr = new JLabel("주소");
			lblCustAddr.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblCustAddr);
		}
		{
			tfCustAddr = new JTextField();
			tfCustAddr.setColumns(10);
			contentPanel.add(tfCustAddr);
		}
		{
			JLabel lblCustTel = new JLabel("전화번호");
			lblCustTel.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblCustTel);
		}
		{
			tfCustTel = new JTextField();
			tfCustTel.setColumns(10);
			contentPanel.add(tfCustTel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null, "취소합니다.");
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
	}
	
	public Customer getItem() {
		
		String custCode = tfCustCode.getText();
		String custName = tfCustName.getText();
		//String custRank = (String) cmbCustRank.getSelectedItem();
		String rank = (String) cmbCustRank.getSelectedItem();
		String new_rank;
		if(rank.equals("Diamond")) {
			new_rank = "D";
		}else if(rank.equals("Platinum")) {
			new_rank = "P";
		}else if(rank.equals("Gold")) {
			new_rank = "G";
		}else if(rank.equals("Silver")) {
			new_rank = "S";
		}else{
			new_rank = "B";
		}
		int custCredit = Integer.parseInt((String)cmbCustCredit.getSelectedItem());
		String custAddr = tfCustAddr.getText();
		String custTel = tfCustTel.getText();
		return new Customer(custCode, custName, new_rank, custCredit, custAddr, custTel);
	}
	
	public void setItem(Customer customer) {
		tfCustCode.setText(customer.getCustCode());
		tfCustName.setText(customer.getCustName());
		cmbCustRank.setSelectedItem(customer.getCustRank());
		cmbCustCredit.setSelectedItem(Integer.toString(customer.getCustCredit()));
		tfCustAddr.setText(customer.getCustAddr());
		tfCustTel.setText(customer.getCustTel());
	}
	
	public void setActiontoAdd() {
		okButton.setActionCommand("추가");
	}
	
	public void setActiontoEdit() {
		okButton.setActionCommand("수정");
	}

}
