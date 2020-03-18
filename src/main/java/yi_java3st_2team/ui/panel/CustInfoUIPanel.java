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
				dlgCustInfo.setLocation(1310, 100);
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
					dlgCustInfo.setLocation(1310, 100);
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
		//조회 버튼을 눌렀을 때 검색범위 선택 콤보박스의 값에 따라 다른 결과가 테이블에 표시되도록 하는 부분 
		String custInfo = panel.getTfSearch().getText().trim();
		List<Customer> list = new ArrayList<>();
		try {
			String search = (String) panel.getCmbSearchList().getSelectedItem();
			//경고창이 떠야할 때 : DB에서 받아오는 객체가 null 일 때 ,콤보박스에서 선택한 값이 통합검색 일 때
			if(search.equals("통합검색")) {
				JOptionPane.showMessageDialog(null, "검색 범위를 선택해주세요.");
				return;
			}
			if(list.size()==0) {
				if(search.equals("고객코드")) {
					Customer custByCode = custService.showCustomerByCode(custInfo);
					if(custByCode==null) {
						JOptionPane.showMessageDialog(null, "해당 고객이 없습니다.");
						return;
					}
					list.add(custByCode);
				}else if(search.equals("고객명")) {
					Customer custByName = custService.showCustomerByName(custInfo);
					if(custByName==null) {
						JOptionPane.showMessageDialog(null, "해당 고객이 없습니다.");
						return;
					}
					list.add(custByName);
				}else if(search.equals("연락처")) {
					Customer custByTel = custService.showCustomerByTel(custInfo);
					if(custByTel==null) {
						JOptionPane.showMessageDialog(null, "해당 고객이 없습니다.");
						return;
					}
					list.add(custByTel);
				}
				panel_1.loadTableData(list);
				
			}
			
		} catch (SQLException e1) {
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
