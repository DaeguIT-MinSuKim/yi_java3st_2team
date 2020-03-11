package yi_java3st_2team.ui.panel;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import yi_java3st_2team.ui.dialog.DlgCustDW;
import yi_java3st_2team.ui.service.BankBookService;
import yi_java3st_2team.ui.service.CustomerService;
import yi_java3st_2team.dto.BankBook;
import yi_java3st_2team.dto.Customer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustDWUIPanel extends JPanel {
	CustomerService custService = new CustomerService();
	BankBookService bankService = new BankBookService();
	private CustDWCenterCenterTblPanel panel_1;
	private CustInfoCenterNorthSearchPanel panel;
	
	public CustDWUIPanel() {

		initialize();
	}
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		panel = new CustInfoCenterNorthSearchPanel();
		panel.getBtnCancel().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				panel.tfClear();
				try {
					panel_1.loadTableData(custService.showCustomersByBalance());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		panel.getBtnSearch().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String custName = panel.getTfSearch().getText().trim();
				List<Customer> listForCustName = new ArrayList<>();
				try {
					
					listForCustName = custService.showCustomerBankInfoByName(custName);
					if(listForCustName.size()==0) {
						JOptionPane.showMessageDialog(null, "해당 고객이 없습니다.");
						return;
					}
					panel_1.loadTableData(listForCustName);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		add(panel, BorderLayout.NORTH);
		
		panel_1 = new CustDWCenterCenterTblPanel();
		try {
			panel_1.loadTableData(custService.showCustomersByBalance());
			panel_1.setPopupMenu(createPopup());
		} catch (SQLException e) {
			System.out.println("해당 고객이 없습니다.");
			e.printStackTrace();
		}
		add(panel_1, BorderLayout.CENTER);
	}
	private JPopupMenu createPopup() {
		JPopupMenu popup = new JPopupMenu();
		
		JMenuItem deposit = new JMenuItem("입금");
		deposit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DlgCustDW dlgcustDW = new DlgCustDW();
				dlgcustDW.getLblAmount().setText("입금 금액");
				
				Customer customer = panel_1.getSelectedItem();
				dlgcustDW.getTfCustName().setText(customer.getCustName());
				Long custBalance = customer.getBankbook().getAccountBalance();
				String balance = Long.toString(custBalance);
				dlgcustDW.getTfBalance().setText(balance);
				
				dlgcustDW.getOkButton().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if(dlgcustDW.getTfCustAmount()==null) {
							JOptionPane.showMessageDialog(null, "금액을 입력해주세요.");
							return;
						}
						
						String custAmount = dlgcustDW.getTfCustAmount().getText();
						int amount = Integer.parseInt(custAmount);
						String accountNum = customer.getBankbook().getAccountNum();
						
						customer.getBankbook().setAccountBalance(custBalance + amount);
						customer.getBankbook().setAccountNum(accountNum);
						
						try {
							bankService.updateBankBalance(customer);
							JOptionPane.showMessageDialog(null, "입금 되었습니다.");
							panel_1.loadTableData(custService.showCustomersByBalance());
							dlgcustDW.dispose();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
						
					}
					
				});
				
				dlgcustDW.getCancelButton().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						dlgcustDW.getTfCustAmount().setText("");
					}
					
				});
				dlgcustDW.setVisible(true);
				
				
			}
			
		});
		JMenuItem withdrawal = new JMenuItem("출금");
		withdrawal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DlgCustDW dlgcustDW = new DlgCustDW();
				dlgcustDW.getLblAmount().setText("출금 금액");
				
				Customer customer = panel_1.getSelectedItem();
				dlgcustDW.getTfCustName().setText(customer.getCustName());
				Long custBalance = customer.getBankbook().getAccountBalance();
				String balance = Long.toString(custBalance);
				dlgcustDW.getTfBalance().setText(balance);
				
				dlgcustDW.getOkButton().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if(dlgcustDW.getTfCustAmount()==null) {
							JOptionPane.showMessageDialog(null, "금액을 입력해주세요.");
							return;
						}
						
						String custAmount = dlgcustDW.getTfCustAmount().getText();
						int amount = Integer.parseInt(custAmount);
						String accountNum = customer.getBankbook().getAccountNum();
						
						customer.getBankbook().setAccountBalance(custBalance - amount);
						customer.getBankbook().setAccountNum(accountNum);
						
						try {
							bankService.updateBankBalance(customer);
							JOptionPane.showMessageDialog(null, "출금 되었습니다.");
							panel_1.loadTableData(custService.showCustomersByBalance());
							dlgcustDW.dispose();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
						
					}
					
				});
				dlgcustDW.setVisible(true);
			}
			
		});
		popup.add(deposit);
		popup.add(withdrawal);
		
	
		return popup;
	}

}
