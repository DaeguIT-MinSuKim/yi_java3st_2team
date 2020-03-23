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

import yi_java3st_2team.dto.Customer;
import yi_java3st_2team.dto.Loan;
import yi_java3st_2team.ui.MainFrame;
import yi_java3st_2team.ui.dialog.DlgLoan;
import yi_java3st_2team.ui.service.LoanService;
import yi_java3st_2team.ui.table.LoanCenterTblPanel;

@SuppressWarnings("serial")
public class LoanCenterUIPanel extends JPanel implements ActionListener {
	private LoanCenterNorthSearchPanel pNorth;
	private LoanCenterTblPanel pCenter;
	private LoanService service;
	private DlgLoan dlgLoan;
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
						Loan loan = dlgLoan.getItem();
						pCenter.addItem(loan);
						service.insertLoan(loan);
						pCenter.loadTableData(service.showLoans());
						JOptionPane.showMessageDialog(null, "추가되었습니다");
						dlgLoan.dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					try {
						Loan loan = dlgLoan.getItem();
						pCenter.updateRow(loan, pCenter.getSelectedRowIdx());
						service.updateLoan(loan);
						pCenter.loadTableData(service.showLoans());
						JOptionPane.showMessageDialog(null, "수정되었습니다");
						dlgLoan.dispose();
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
					dlgLoan = new DlgLoan();
					dlgLoan.initCmbModel(service);
					dlgLoan.setEmp(main.getEmpAuth());
					dlgLoan.setTitle("대출 " + e.getActionCommand());
					dlgLoan.getBtnOk().setText(e.getActionCommand());
					dlgLoan.getBtnOk().addActionListener(myDlgListener);
					dlgLoan.setModal(true);
					dlgLoan.setVisible(true);	
				}
				else if(e.getActionCommand().equals("수정")) {
					try {
						selIdx = pCenter.getSelectedRowIdx();
						Loan loan = pCenter.getSelectedItem();
						dlgLoan = new DlgLoan();
						dlgLoan.initCmbModel(service);
						dlgLoan.setTitle("대출" + e.getActionCommand());
						dlgLoan.getBtnOk().setText(e.getActionCommand());
						dlgLoan.getBtnOk().addActionListener(myDlgListener);
						dlgLoan.getTfAccountNum().setEnabled(false);
						dlgLoan.getCmbCust().setEnabled(false);
						dlgLoan.getCmbPlan().setEditable(false);
						dlgLoan.setItem(loan);
						dlgLoan.setModal(true);
						dlgLoan.setVisible(true);
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
		Customer cust = new Customer();
		cust.setCustName(pNorth.getTfSearch().getText());
		Loan loan = new Loan();
		loan.setCustCode(cust);
		try {
			List<Loan> list = service.showLoanByCustName(loan);
			if(list.size()==0) {
				JOptionPane.showMessageDialog(null, "그런 고객을 찾을 수 없습니다");
				return;
			}
			pCenter.loadTableData(list);
			JOptionPane.showMessageDialog(null, "검색이 완료되었습니다");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
}
