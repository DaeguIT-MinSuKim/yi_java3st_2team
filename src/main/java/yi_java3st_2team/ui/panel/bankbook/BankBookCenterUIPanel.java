package yi_java3st_2team.ui.panel.bankbook;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import yi_java3st_2team.dto.BankBook;
import yi_java3st_2team.dto.Customer;
import yi_java3st_2team.dto.Plan;
import yi_java3st_2team.ui.MainFrame;
import yi_java3st_2team.ui.dialog.DlgBankBookAdd;
import yi_java3st_2team.ui.dialog.DlgBankBookMod;
import yi_java3st_2team.ui.service.BankBookService;
import yi_java3st_2team.ui.table.BankBookCenterTblPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class BankBookCenterUIPanel extends JPanel implements ActionListener {
	private MainFrame main;
	private BankBookCenterNorthSearchPanel pNorth;
	private BankBookCenterTblPanel pCenter;
	private BankBookService service;
	private DlgBankBookAdd dlgBankBookAdd;
	private DlgBankBookMod dlgBankBookMod;
	private int selIdx;
	/**
	 * Create the panel.
	 */
	public BankBookCenterUIPanel() {
		initialize();
	}
	private void initialize() {
		if(service==null) {
			service = new BankBookService();
		}
		setLayout(new BorderLayout(0, 0));
		
		pNorth = new BankBookCenterNorthSearchPanel();
		pNorth.getBtnCancel().addActionListener(this);
		pNorth.getBtnSearch().addActionListener(this);
		add(pNorth, BorderLayout.NORTH);
		
		pCenter = new BankBookCenterTblPanel();
		pCenter.setBorder(new EmptyBorder(10, 50, 30, 50));
		add(pCenter, BorderLayout.CENTER);
		try {
			pCenter.loadTableData(service.showBankBooks());
			pCenter.setPopupMenu(getTblPopMenu());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public MainFrame getMain() {
		return main;
	}
	public void setMain(MainFrame main) {
		this.main = main;
	}
	private JPopupMenu getTblPopMenu() {
		JPopupMenu popMenu = new JPopupMenu();
		ActionListener myDlgListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("추가")) {
					try {
						BankBook bankbook = dlgBankBookAdd.getItem();
						pCenter.addItem(bankbook);
						service.insertBankBook(bankbook);
						pCenter.loadTableData(service.showBankBooks());
						JOptionPane.showMessageDialog(null, "추가되었습니다");
						dlgBankBookAdd.dispose();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "계좌번호가 중복되었습니다 다시 한번 확인해주세요");
						return;
					}
				}
				else {
					try {
						BankBook bankbook = dlgBankBookMod.getItem();
						pCenter.updateRow(bankbook, pCenter.getSelectedRowIdx());
						service.updateBankBook(bankbook);
						pCenter.loadTableData(service.showBankBooks());
						JOptionPane.showMessageDialog(null, "수정되었습니다");
						dlgBankBookMod.dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block  
						e1.printStackTrace();
					}
				}
			}
		};
		ActionListener popMenuListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("추가")) {
					dlgBankBookAdd = new DlgBankBookAdd();
					dlgBankBookAdd.setEmp(main.getEmpAuth());
					dlgBankBookAdd.setTitle("통장 " + e.getActionCommand());
					dlgBankBookAdd.getBtnOk().setText(e.getActionCommand());
					dlgBankBookAdd.getBtnOk().addActionListener(myDlgListener);
					dlgBankBookAdd.setModal(true);
					dlgBankBookAdd.setVisible(true);	
				}
				else if(e.getActionCommand().equals("수정")) {
					try {
						BankBook bankbook = pCenter.getSelectedItem();
						dlgBankBookMod = new DlgBankBookMod();
						dlgBankBookMod.setEmp(main.getEmpAuth());
						dlgBankBookMod.setTitle("통장" + e.getActionCommand());
						dlgBankBookMod.getBtnOk().setText(e.getActionCommand());
						dlgBankBookMod.getBtnOk().addActionListener(myDlgListener);
						dlgBankBookMod.getTfCardNum().setEditable(false);
						dlgBankBookMod.getCmbCust().setEnabled(false);
						dlgBankBookMod.getCmbPlan().setEnabled(false);
						dlgBankBookMod.setItem(bankbook);
						dlgBankBookMod.setModal(true);
						dlgBankBookMod.setVisible(true);
					}
					catch(RuntimeException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
					
				}
				else if(e.getActionCommand().equals("삭제")) {
					try {
						int idx = pCenter.getSelectedRowIdx();
						BankBook bankbook = pCenter.getSelectedItem();
						int res = JOptionPane.showConfirmDialog(null, "정말 삭제하시겠습니까?");
						if(res==0) {
							try {
								pCenter.removeItem(idx);
								service.insertTerminationAccountProcedure(bankbook);
								service.deleteBankBook(bankbook);
								pCenter.loadTableData(service.showBankBooks());
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							JOptionPane.showMessageDialog(null, "삭제가 완료되었습니다");
						}
						else {
							JOptionPane.showMessageDialog(null, "삭제가 취소되었습니다");
						}
					}
					catch(RuntimeException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
					
				}
				else {
					BankBook bankbook = pCenter.getSelectedItem();
					if(bankbook.getAccountNum().substring(7, 8).equals("2")) {
						JOptionPane.showMessageDialog(null, "이미 전환된 계좌입니다");
						return;
					}
					int res = JOptionPane.showConfirmDialog(null, "정말 전환하시겠습니까?");
					if(res==0) {
						try {
							pCenter.removeItem(selIdx);
							service.insertDormantAccountProcedure(bankbook);
							service.deleteBankBook(bankbook);
							pCenter.loadTableData(service.showBankBooks());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "휴면계좌로 전환되었습니다");
					}
					else {
						JOptionPane.showMessageDialog(null, "전환이 취소되었습니다");
					}
				}
				
			}
		};
		JMenuItem insertMenu = new JMenuItem("추가");
		JMenuItem updateMenu = new JMenuItem("수정");
		JMenuItem deleteMenu = new JMenuItem("삭제");
		JMenuItem changeMenu = new JMenuItem("휴면계좌전환");
		insertMenu.addActionListener(popMenuListener);
		updateMenu.addActionListener(popMenuListener);
		deleteMenu.addActionListener(popMenuListener);
		changeMenu.addActionListener(popMenuListener);
		popMenu.add(insertMenu);
		popMenu.add(updateMenu);
		popMenu.add(deleteMenu);
		popMenu.add(changeMenu);
		return popMenu;
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == pNorth.getBtnCancel()) {
			pNorthBtnCancelActionPerformed(e);
		}
		if (e.getSource() == pNorth.getBtnSearch()) {
			pNorthBtnSearchActionPerformed(e);
		}
	}
	protected void pNorthBtnSearchActionPerformed(ActionEvent e) {
		String searchMenu = (String)pNorth.getCmbSearchList().getSelectedItem();
		switch(searchMenu) {
		case "검색구분":
			pNorth.tfClear();
			JOptionPane.showMessageDialog(null, "검색할 구분을 선택하세요");
			break;
		case "계좌번호":
			BankBook bankbook = new BankBook();
			bankbook.setAccountNum(pNorth.getTfSearch().getText().trim());
			try {
				List<BankBook>list = service.showBankBookByAccoutNum(bankbook);
				if(service.showBankBookByAccoutNum(bankbook).size()==0) {
					JOptionPane.showMessageDialog(null, "그런 계좌번호는 찾을 수 없습니다");
					return;
				}
				else {
					pCenter.loadTableData(list);
					JOptionPane.showMessageDialog(null, "검색이 완료되었습니다");
					pNorth.tfClear();

				}
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			break;
		case "고객이름":
			Customer cust = new Customer();
			cust.setCustName(pNorth.getTfSearch().getText().trim());
			bankbook = new BankBook(cust);
			try {
				List<BankBook> list = service.showBankBookByCustName(bankbook);
				if(list.size()==0) {
					JOptionPane.showMessageDialog(null, "그런 고객을 찾을 수 없습니다");
				}
				else {
					pCenter.loadTableData(list);
					JOptionPane.showMessageDialog(null, "검색이 완료되었습니다");
					pNorth.tfClear();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "상품명":
			Plan plan = new Plan();
			plan.setPlanName(pNorth.getTfSearch().getText().trim());
			bankbook = new BankBook();
			bankbook.setAccountPlanCode(plan);
			try {
				List<BankBook> list = service.showBankBookByPlanName(bankbook);
				if(list.size()==0) {
					JOptionPane.showMessageDialog(null, "그런 상품은 없습니다 다시 검색하세요");
				}
				else {
					pCenter.loadTableData(list);
					JOptionPane.showMessageDialog(null, "검색이 완료되었습니다");
					pNorth.tfClear();
				}
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "통장상품":
			switch(pNorth.getTfSearch().getText().trim()) {
			case "예금":
				try {
					pCenter.loadTableData(service.showBankBookByDeposit());
					JOptionPane.showMessageDialog(null, "검색이 완료되었습니다");
					pNorth.tfClear();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case "적금":
				try {
					pCenter.loadTableData(service.showBankBookBySaving());
					JOptionPane.showMessageDialog(null, "검색이 완료되었습니다");
					pNorth.tfClear();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case "마이너스":
				try {
					pCenter.loadTableData(service.showBankBookByMinus());
					JOptionPane.showMessageDialog(null, "검색이 완료되었습니다");
					pNorth.tfClear();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			default:
				JOptionPane.showMessageDialog(null, "없는 상품입니다 다시 조회하세요");
				break;
			}
			break;
		}
	}
	protected void pNorthBtnCancelActionPerformed(ActionEvent e) {
		try {
			pNorth.tfClear();
			List<BankBook> list = service.showBankBooks();
			pCenter.loadTableData(list);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public BankBookCenterNorthSearchPanel getpNorth() {
		return pNorth;
	}
	
	
	
}
