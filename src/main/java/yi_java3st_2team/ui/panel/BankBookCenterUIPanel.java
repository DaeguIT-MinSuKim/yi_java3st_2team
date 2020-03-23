package yi_java3st_2team.ui.panel;

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
import yi_java3st_2team.ui.dialog.DlgBankBook;
import yi_java3st_2team.ui.service.BankBookService;
import yi_java3st_2team.ui.table.BankBookCenterTblPanel;

@SuppressWarnings("serial")
public class BankBookCenterUIPanel extends JPanel implements ActionListener {
	private MainFrame main;
	private BankBookCenterNorthSearchPanel pNorth;
	private BankBookCenterTblPanel pCenter;
	private BankBookService service;
	private DlgBankBook dlgBankBook;
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
						BankBook bankbook = dlgBankBook.getItem();
						pCenter.addItem(bankbook);
						service.insertBankBook(bankbook);
						pCenter.loadTableData(service.showBankBooks());
						JOptionPane.showMessageDialog(null, "추가되었습니다");
						dlgBankBook.dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					try {
						BankBook bankbook = dlgBankBook.getItem();
						pCenter.updateRow(bankbook, pCenter.getSelectedRowIdx());
						service.updateBankBook(bankbook);
						pCenter.loadTableData(service.showBankBooks());
						JOptionPane.showMessageDialog(null, "수정되었습니다");
						dlgBankBook.dispose();
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
					dlgBankBook = new DlgBankBook();
					dlgBankBook.initCmbModel(service);
					dlgBankBook.setEmp(main.getEmpAuth());
					dlgBankBook.setTitle("통장 " + e.getActionCommand());
					dlgBankBook.getBtnOk().setText(e.getActionCommand());
					dlgBankBook.getBtnOk().addActionListener(myDlgListener);
					dlgBankBook.setModal(true);
					dlgBankBook.setVisible(true);	
				}
				else if(e.getActionCommand().equals("수정")) {
					try {
						BankBook bankbook = pCenter.getSelectedItem();
						dlgBankBook = new DlgBankBook();
						dlgBankBook.initCmbModel(service);
						dlgBankBook.setEmp(main.getEmpAuth());
						dlgBankBook.setTitle("통장" + e.getActionCommand());
						dlgBankBook.getBtnOk().setText(e.getActionCommand());
						dlgBankBook.getBtnOk().addActionListener(myDlgListener);
						dlgBankBook.getTfCardNum().setEditable(false);
						dlgBankBook.getCmbCust().setEnabled(false);
						dlgBankBook.getCmbPlan().setEnabled(false);
						dlgBankBook.setItem(bankbook);
						dlgBankBook.setModal(true);
						dlgBankBook.setVisible(true);
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
				pCenter.loadTableData(service.showBankBookByAccoutNum(bankbook));
				JOptionPane.showMessageDialog(null, "검색이 완료되었습니다");
				pNorth.tfClear();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "고객이름":
			Customer cust = new Customer();
			cust.setCustName(pNorth.getTfSearch().getText().trim());
			bankbook = new BankBook(cust);
			try {
				pCenter.loadTableData(service.showBankBookByCustName(bankbook));
				JOptionPane.showMessageDialog(null, "검색이 완료되었습니다");
				pNorth.tfClear();
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
				pCenter.loadTableData(service.showBankBookByPlanName(bankbook));
				JOptionPane.showMessageDialog(null, "검색이 완료되었습니다");
				pNorth.tfClear();
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
	
}
