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
import yi_java3st_2team.ui.table.CustDWCenterCenterTblPanel;
import yi_java3st_2team.dto.BankBook;
import yi_java3st_2team.dto.Customer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustDWUIPanel extends JPanel {
	CustomerService custService = new CustomerService();
	BankBookService bankService = new BankBookService();
	private CustDWCenterCenterTblPanel panel_1;
	private CustDWCenterNorthSearchPanel panel;
	
	public CustDWUIPanel() {

		initialize();
	}
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		panel = new CustDWCenterNorthSearchPanel();
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
					if(listForCustName==null) {
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
			List<Customer> list = custService.showCustomersByBalance();
			panel_1.loadTableData(list);
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
				if(customer != null) {
				dlgcustDW.getTfCustName().setText(customer.getCustName());
				Long custBalance = customer.getBankbook().getAccountBalance();
				
				dlgcustDW.getTfBalance().setText(String.format("%,d",custBalance));
				
				dlgcustDW.getOkButton().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if(dlgcustDW.getTfCustAmount()==null) {
							JOptionPane.showMessageDialog(null, "금액을 입력해주세요.");
							return;
						}
						
						String custAmount = dlgcustDW.getTfCustAmount().getText();
						//금액(숫자) 외에 문자를 입력했을 경우
						char tmp;
						Boolean output = true;
						for(int i=0; i<custAmount.length(); i++) {
							tmp = custAmount.charAt(i);
							if(Character.isDigit(tmp)==false) {
								output = false;
							}
						}
						if(output==true) {
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
						
						}else {
							JOptionPane.showMessageDialog(null, "숫자만 입력하세요.");
						}
					}
					
				});
				
				
				dlgcustDW.getCancelButton().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						dlgcustDW.getTfCustAmount().setText("");
					}
					
				});
				dlgcustDW.setLocation(890, 100);
				dlgcustDW.setVisible(true);
				
				}else {
					JOptionPane.showMessageDialog(null, "고객을 선택하세요.");
				}
			}//--
			
		});
		JMenuItem withdrawal = new JMenuItem("출금");
		withdrawal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DlgCustDW dlgcustDW = new DlgCustDW();
				dlgcustDW.getLblAmount().setText("출금 금액");
				
				Customer customer = panel_1.getSelectedItem();
				if(customer != null) {
					dlgcustDW.getTfCustName().setText(customer.getCustName());
					Long custBalance = customer.getBankbook().getAccountBalance();
					dlgcustDW.getTfBalance().setText(String.format("%,d", custBalance));
					
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
								if(customer.getBankbook().getAccountBalance() >= 0) {
									bankService.updateBankBalance(customer);
									JOptionPane.showMessageDialog(null, "출금 되었습니다.");
									panel_1.loadTableData(custService.showCustomersByBalance());
									dlgcustDW.dispose();
								}else {
									JOptionPane.showMessageDialog(null, "잔액이 부족합니다.");
									try {
										panel_1.loadTableData(custService.showCustomersByBalance());
									} catch (SQLException e1) {
										e1.printStackTrace();
									}
									
								}
							
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
					dlgcustDW.setLocation(890, 100);
					dlgcustDW.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "고객을 선택하세요.");
				}
			}
		});
		
		popup.add(deposit);
		popup.add(withdrawal);
		
	
		return popup;
	}

}
