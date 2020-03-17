package yi_java3st_2team.ui.panel;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import yi_java3st_2team.ui.dialog.DlgCustInfo;
import yi_java3st_2team.ui.service.CustomerService;
import yi_java3st_2team.ui.table.CustInfoCenterCenterTblPanel;
import yi_java3st_2team.dto.Customer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustInfoUIPanel extends JPanel implements ActionListener {
	CustomerService custService = new CustomerService();
	private CustInfoCenterNorthSearchPanel panel;
	private List<Customer> list = new ArrayList<>();
	private CustInfoCenterCenterTblPanel panel_1;
	
	public CustInfoUIPanel() {

		initialize();
	}
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		panel = new CustInfoCenterNorthSearchPanel();
		panel.getBtnCancel().addActionListener(this);
		panel.getBtnSearch().addActionListener(this);
		add(panel, BorderLayout.NORTH);
		
		panel_1 = new CustInfoCenterCenterTblPanel();
		try {
			list = custService.showCustomers();
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
		
		JMenuItem addMenu = new JMenuItem("추가");
		addMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DlgCustInfo dlgCustInfo = new DlgCustInfo();
				dlgCustInfo.setActiontoAdd();
				try {
					List<Customer> list = custService.showCustomers();
					dlgCustInfo.getTfCustCode().setText("C"+String.format("%03d",list.size()+1));
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
				dlgCustInfo.getOkButton().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if(dlgCustInfo.getTfCustCode()==null || dlgCustInfo.getTfCustName()==null || dlgCustInfo.getCmbCustRank().getSelectedIndex()==-1
								  || dlgCustInfo.getCmbCustCredit().getSelectedIndex()==-1 || dlgCustInfo.getTfCustAddr()==null || dlgCustInfo.getTfCustTel()==null) {
									JOptionPane.showMessageDialog(null, "빈 칸을 모두 입력해주세요.");
								}
						if(e.getActionCommand().equals("추가")) {
									try {
										boolean falsechk = false;
										String custCode = dlgCustInfo.getItem().getCustCode();
										//등록된 고객인지 확인
										List<String> list = custService.custExistChk();
										dlgCustInfo.getTfCustCode().setText("C"+String.format("%03d",list.size()+1));
										for(int i=0; i<list.size(); i++) {											
											if(custCode.equals(list.get(i))) {												
												falsechk = false;
												break;
											}else {											
												falsechk = true;
											}
										}
										
										if(falsechk == true) {
											addItemTbl(dlgCustInfo.getItem());
											JOptionPane.showMessageDialog(null, "신규 고객이 추가되었습니다.");
											refreshTbl();
											dlgCustInfo.dispose();
										}else {
											JOptionPane.showMessageDialog(null, "이미 등록된 고객입니다.");
										}
										
									} catch (SQLException e1) {
										e1.printStackTrace();
									}
						}
						
						
					}
					
				});
				dlgCustInfo.setLocation(890, 100);
				dlgCustInfo.setVisible(true);
				
			}
			
		});
		popup.add(addMenu);
		
		JMenuItem editMenu = new JMenuItem("수정");
		editMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DlgCustInfo dlgCustInfo = new DlgCustInfo();
				Customer customer = panel_1.getSelectedItem();
				if(customer!=null) {
					dlgCustInfo.setItem(customer);
					dlgCustInfo.setActiontoEdit();
					dlgCustInfo.getOkButton().addActionListener(new ActionListener() {
	
						@Override
						public void actionPerformed(ActionEvent e) {
							if(dlgCustInfo.getTfCustCode()==null || dlgCustInfo.getTfCustName()==null || dlgCustInfo.getCmbCustRank().getSelectedIndex()==-1
									  || dlgCustInfo.getCmbCustCredit().getSelectedIndex()==-1 || dlgCustInfo.getTfCustAddr()==null || dlgCustInfo.getTfCustTel()==null) {
										JOptionPane.showMessageDialog(null, "빈 칸을 모두 입력해주세요.");
									}
							if(e.getActionCommand().equals("수정")) {
								try {
									updateTbl(dlgCustInfo.getItem());
									JOptionPane.showMessageDialog(null, "수정 되었습니다.");
									refreshTbl();
									dlgCustInfo.dispose();
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
							}
						}
					
						
						});
					dlgCustInfo.setLocation(890, 100);
					dlgCustInfo.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "고객을 선택하세요.");
				}
				
				
				
			}
			
		});
		popup.add(editMenu);
		
		JMenuItem deleteMenu = new JMenuItem("삭제");
		deleteMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Customer customer = panel_1.getSelectedItem();
				try {
					if(customer!=null) {
						custService.removeCustomer(customer);
						JOptionPane.showMessageDialog(null, "삭제되었습니다.");
						refreshTbl();
					}else {
						JOptionPane.showMessageDialog(null, "고객을 선택하세요.");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
			
		});
		popup.add(deleteMenu);
		return popup;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == panel.getBtnCancel()) {
			panelBtnCancelActionPerformed(e);
		}
		if (e.getSource() == panel.getBtnSearch()) {
			panelBtnSearchActionPerformed(e);
		}
	}
	protected void panelBtnSearchActionPerformed(ActionEvent e) {
		String custName = panel.getTfSearch().getText().trim();
		List<Customer> listForCustName = new ArrayList<>();
		try {
			Customer newCust = custService.showCustomerByName(custName);
			
			if(listForCustName.size()==0) {
				if(newCust==null) {
					JOptionPane.showMessageDialog(null, "해당 고객이 없습니다.");
					return;
				}
				listForCustName.add(newCust);
			}
			panel_1.loadTableData(listForCustName);
		} catch (SQLException e1) {
			System.out.println("해당 고객이 없습니다.");
			e1.printStackTrace();
		}
	}
	protected void panelBtnCancelActionPerformed(ActionEvent e) {
		panel.tfClear();
		try {
			panel_1.loadTableData(custService.showCustomers());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void addItemTbl(Customer customer) throws SQLException {
		custService.AddCustomer(customer);
		
	}

	
	public void updateTbl(Customer customer) throws SQLException{
		custService.editCustomer(customer);
	}
	
	public void refreshTbl() throws SQLException {
		List<Customer> list = custService.showCustomers();
		panel_1.loadTableData(list);
	}
	
}
