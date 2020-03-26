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
import yi_java3st_2team.dto.Loan;
import yi_java3st_2team.dto.Plan;
import yi_java3st_2team.ui.MainFrame;
import yi_java3st_2team.ui.dialog.DlgLoanAdd;
import yi_java3st_2team.ui.dialog.DlgLoanMod;
import yi_java3st_2team.ui.service.LoanService;
import yi_java3st_2team.ui.table.LoanCenterTblPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class LoanCenterUIPanel extends JPanel implements ActionListener {
	private LoanCenterNorthSearchPanel pNorth;
	private LoanCenterTblPanel pCenter;
	private LoanService service;
	private DlgLoanAdd dlgLoanAdd;
	private DlgLoanMod dlgLoanMod;
	private int selIdx;
	private MainFrame main;

	/**
	 * Create the panel.
	 */
	public LoanCenterUIPanel() {
		initialize();
	}
	private void initialize() {
		if(service==null) {
			service = new LoanService();
		}
		setLayout(new BorderLayout(0, 0));
		
		pNorth = new LoanCenterNorthSearchPanel();
		pNorth.getBtnCancel().addActionListener(this);
		pNorth.getBtnSearch().addActionListener(this);
		add(pNorth, BorderLayout.NORTH);
		
		pCenter = new LoanCenterTblPanel();
		pCenter.setBorder(new EmptyBorder(10, 50, 30, 50));
		add(pCenter, BorderLayout.CENTER);
		try {
			pCenter.loadTableData(service.showLoans());
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
						Loan loan = dlgLoanAdd.getItem();
						service.insertLoan(loan);
						pCenter.addItem(loan);
						pCenter.loadTableData(service.showLoans());
						JOptionPane.showMessageDialog(null, "추가되었습니다");
						dlgLoanAdd.dispose();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "계좌번호가 중복되었습니다 다시 한번 확인해주세요");
						return;
					}
				}
				else {
					try {
						Loan loan = dlgLoanMod.getItem();
						pCenter.updateRow(loan, pCenter.getSelectedRowIdx());
						service.updateLoan(loan);
						pCenter.loadTableData(service.showLoans());
						JOptionPane.showMessageDialog(null, "수정되었습니다");
						dlgLoanMod.dispose();
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
					dlgLoanAdd = new DlgLoanAdd();
					dlgLoanAdd.setEmp(main.getEmpAuth());
					dlgLoanAdd.setTitle("대출 " + e.getActionCommand());
					dlgLoanAdd.getBtnOk().setText(e.getActionCommand());
					dlgLoanAdd.getBtnOk().addActionListener(myDlgListener);
					dlgLoanAdd.setModal(true);
					dlgLoanAdd.setVisible(true);	
				}
				else if(e.getActionCommand().equals("수정")) {
					try {
						selIdx = pCenter.getSelectedRowIdx();
						Loan loan = pCenter.getSelectedItem();
						dlgLoanMod = new DlgLoanMod();
						dlgLoanMod.setTitle("대출" + e.getActionCommand());
						dlgLoanMod.getBtnOk().setText(e.getActionCommand());
						dlgLoanMod.getBtnOk().addActionListener(myDlgListener);
						dlgLoanMod.getTfAccountNum().setEnabled(false);
						dlgLoanMod.getCmbCust().setEnabled(false);
						dlgLoanMod.getCmbPlan().setEnabled(false);
						dlgLoanMod.setItem(loan);
						dlgLoanMod.setModal(true);
						dlgLoanMod.setVisible(true);
					}
					catch(RuntimeException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
				else {
					try {
						Loan loan = pCenter.getSelectedItem();
						selIdx = pCenter.getSelectedRowIdx();
						int res = JOptionPane.showConfirmDialog(null, "정말 삭제하시겠습니까?");
						if(res==0) {
							try {
								pCenter.removeItem(selIdx);
								service.deleteLoan(loan);
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
				
			}
		};
		JMenuItem insertMenu = new JMenuItem("추가");
		JMenuItem updateMenu = new JMenuItem("수정");
		JMenuItem deleteMenu = new JMenuItem("삭제");
		insertMenu.addActionListener(popMenuListener);
		updateMenu.addActionListener(popMenuListener);
		deleteMenu.addActionListener(popMenuListener);
		popMenu.add(insertMenu);
		popMenu.add(updateMenu);
		popMenu.add(deleteMenu);
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
			Loan loan = new Loan();
			loan.setLoanAccountNum(pNorth.getTfSearch().getText().trim());
			try {
				List<Loan> list = service.searchLoanAccountNum(loan);
				if(list.size()==0) {
					JOptionPane.showMessageDialog(null, "그런 계좌번호를 찾을 수 없습니다");
				}
				else {
					pCenter.loadTableData(list);
					JOptionPane.showMessageDialog(null, "검색이 완료되었습니다");
				}
			}
			catch(SQLException e1) {
				e1.printStackTrace();
			}
			break;
		case "고객이름":
			loan = new Loan();
			Customer customer = new Customer();
			customer.setCustName(pNorth.getTfSearch().getText().trim());
			loan.setCustCode(customer);
			try {
				List<Loan> list = service.searchLoanCustName(loan);
				if(list.size()==0) {
					JOptionPane.showMessageDialog(null, "그런 고객을 찾을 수 없습니다");
				}
				else {
					pCenter.loadTableData(list);
					JOptionPane.showMessageDialog(null, "검색이 완료되었습니다");
				}
			}
			catch(SQLException e1) {
				e1.printStackTrace();
			}
			break;
		case "상품명":
			loan = new Loan();
			Plan plan = new Plan();
			plan.setPlanName(pNorth.getTfSearch().getText().trim());
			loan.setPlanCode(plan);
			try {
				List<Loan> list = service.searchLoanPlanName(loan);
				if(list.size()==0) {
					JOptionPane.showMessageDialog(null, "그런 상품을 찾을 수 없습니다");
				}
				else {
					pCenter.loadTableData(list);
					JOptionPane.showMessageDialog(null, "검색이 완료되었습니다");
				}
			}
			catch(SQLException e1) {
				e1.printStackTrace();
			}
			break;
		}
	}
	protected void pNorthBtnCancelActionPerformed(ActionEvent e) {
		try {
			pNorth.tfClear();
			List<Loan> list = service.showLoans();
			pCenter.loadTableData(list);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public LoanCenterNorthSearchPanel getpNorth() {
		return pNorth;
	}
	
}
