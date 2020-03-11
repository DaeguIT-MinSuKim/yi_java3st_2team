package yi_java3st_2team.ui.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class DlgCustDW extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfCustName;
	private JTextField tfBalance;
	private JTextField tfCustAmount;
	private JButton okButton;
	private JButton cancelButton;
	private JLabel lblAmount;

	
	public static void main(String[] args) {
		try {
			DlgCustDW dialog = new DlgCustDW();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	
	public JTextField getTfCustName() {
		return tfCustName;
	}




	public JTextField getTfBalance() {
		return tfBalance;
	}




	public JTextField getTfCustAmount() {
		return tfCustAmount;
	}




	public JButton getOkButton() {
		return okButton;
	}




	public JButton getCancelButton() {
		return cancelButton;
	}

	





	public JLabel getLblAmount() {
		return lblAmount;
	}




	public DlgCustDW() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new EmptyBorder(30, 10, 30, 10));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JLabel lblCustName = new JLabel("고객 명");
				lblCustName.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblCustName);
			}
			{
				tfCustName = new JTextField();
				tfCustName.setEditable(false);
				panel.add(tfCustName);
				tfCustName.setColumns(10);
			}
			{
				lblAmount = new JLabel("금액");
				lblAmount.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblAmount);
			}
			{
				tfCustAmount = new JTextField();
				tfCustAmount.setColumns(10);
				panel.add(tfCustAmount);
			}
			{
				JLabel lblBalance = new JLabel("잔액");
				lblBalance.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblBalance);
			}
			{
				tfBalance = new JTextField();
				tfBalance.setEditable(false);
				tfBalance.setColumns(10);
				panel.add(tfBalance);
			}
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
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
