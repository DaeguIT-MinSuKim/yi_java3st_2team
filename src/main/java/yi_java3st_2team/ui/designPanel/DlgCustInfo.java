package yi_java3st_2team.ui.designPanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import yi_java3st_2team.dto.Customer;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
			cmbCustRank = new JComboBox();
			contentPanel.add(cmbCustRank);
		}
		{
			JLabel lblCustCredit = new JLabel("신용등급");
			lblCustCredit.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblCustCredit);
		}
		{
			cmbCustCredit = new JComboBox();
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
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if(tfCustCode==null || tfCustName==null || cmbCustRank.getSelectedIndex()==-1
						  || cmbCustCredit.getSelectedIndex()==-1 || tfCustAddr==null || tfCustTel==null) {
							JOptionPane.showMessageDialog(null, "빈 칸을 모두 입력해주세요.");
						}
						
						
						
					}
					
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
	}
	
	public Customer getItem() {
		String custCode = tfCustCode.getText();
		String custName = tfCustName.getText();
		String custRank = (String) cmbCustRank.getSelectedItem();
		int custCredit = (int) cmbCustCredit.getSelectedItem();
		String custAddr = tfCustAddr.getText();
		String custTel = tfCustTel.getText();
		return new Customer(custCode, custName, custRank, custCredit, custAddr, custTel);
	}

}
