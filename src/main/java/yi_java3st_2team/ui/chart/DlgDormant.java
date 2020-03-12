package yi_java3st_2team.ui.chart;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import yi_java3st_2team.dto.AccountInfo;
import yi_java3st_2team.ui.service.BankBookService;
import yi_java3st_2team.ui.table.DormantInfoTblPanel;

@SuppressWarnings("serial")
public class DlgDormant extends JDialog implements ActionListener {
	private BankBookService service;
	private final DormantInfoTblPanel contentPanel = new DormantInfoTblPanel();
	private JButton okButton;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgDormant dialog = new DlgDormant();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgDormant() {
		service = new BankBookService();
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		List<AccountInfo> itemList;
		try {
			itemList = service.showDormantAccountInfo();
			contentPanel.loadTableData(itemList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == okButton) {
			okButtonActionPerformed(e);
		}
	}
	protected void okButtonActionPerformed(ActionEvent e) {
		dispose();
	}
}
