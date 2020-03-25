package yi_java3st_2team.ui.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Closeable;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import yi_java3st_2team.dto.BankBook;
import yi_java3st_2team.dto.Card;
import yi_java3st_2team.ui.service.CardService;
import yi_java3st_2team.ui.table.BankBookAvailableDepositPanel;

@SuppressWarnings("serial")
public class DlgConnectBankBookInfo extends JDialog implements ActionListener {
	private DlgCard dlgCard;
	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private BankBookAvailableDepositPanel panel;
	private CardService service;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgConnectBankBookInfo dialog = new DlgConnectBankBookInfo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgConnectBankBookInfo() {
		service = new CardService();
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("확인");
				okButton.addActionListener(this);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		panel = new BankBookAvailableDepositPanel();
		contentPanel.add(panel,BorderLayout.CENTER);
		{
			lblNewLabel = new JLabel("발급가능한 예금통장");
			lblNewLabel.setFont(new Font("굴림", Font.BOLD, 20));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(lblNewLabel, BorderLayout.NORTH);
		}
	}

	public DlgCard getDlgCard() {
		return dlgCard;
	}

	public void setDlgCard(DlgCard dlgCard) {
		this.dlgCard = dlgCard;
	}
	public BankBookAvailableDepositPanel getPanel() {
		return panel;
	}

	public void setPanel(BankBookAvailableDepositPanel panel) {
		this.panel = panel;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == okButton) {
			okButtonActionPerformed(e);
		}
	}
	protected void okButtonActionPerformed(ActionEvent e) {
		try {
			BankBook bankbook = panel.getSelectedItem();
			Card card = dlgCard.getItem();
			card.setBankbook(bankbook);
			try {
				service.insertCheckCardProcedure(card);
				JOptionPane.showMessageDialog(null, "추가되었습니다");
				dlgCard.getUiPanel().getpCenter().loadTableData(service.showCards());
				dispose();
				dlgCard.dispose();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		catch(RuntimeException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} 
	}
}
