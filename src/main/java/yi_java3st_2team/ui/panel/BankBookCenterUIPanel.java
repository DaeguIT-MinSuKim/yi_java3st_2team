package yi_java3st_2team.ui.panel;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.List;

import yi_java3st_2team.dto.BankBook;
import yi_java3st_2team.dto.Card;
import yi_java3st_2team.dto.Customer;
import yi_java3st_2team.ui.service.BankBookService;
import yi_java3st_2team.ui.service.CardService;
import yi_java3st_2team.ui.table.BankBookCenterTblPanel;
import yi_java3st_2team.ui.table.CardCenterTblPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class BankBookCenterUIPanel extends JPanel implements ActionListener {
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
					dlgBankBook.setTitle("통장 " + e.getActionCommand());
					dlgBankBook.getBtnOk().setText(e.getActionCommand());
					dlgBankBook.getBtnOk().addActionListener(myDlgListener);
					dlgBankBook.setModal(true);
					dlgBankBook.setVisible(true);	
				}
				else if(e.getActionCommand().equals("수정")) {
					try {
						selIdx = pCenter.getSelectedRowIdx();
						BankBook bankbook = pCenter.getSelectedItem();
						dlgBankBook = new DlgBankBook();
						dlgBankBook.initCmbModel(service);
						dlgBankBook.setTitle("통장" + e.getActionCommand());
						dlgBankBook.getBtnOk().setText(e.getActionCommand());
						dlgBankBook.getBtnOk().addActionListener(myDlgListener);
						dlgBankBook.getTfCardNum().setEnabled(false);
						dlgBankBook.getCmbCust().setEnabled(false);
						dlgBankBook.getCmbPlan().setEditable(false);
						dlgBankBook.setItem(bankbook);
						dlgBankBook.setModal(true);
						dlgBankBook.setVisible(true);
					}
					catch(RuntimeException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
				else {
					try {
						BankBook bankbook = pCenter.getSelectedItem();
						selIdx = pCenter.getSelectedRowIdx();
						int res = JOptionPane.showConfirmDialog(null, "정말 삭제하시겠습니까?");
						if(res==0) {
							try {
								pCenter.removeItem(selIdx);
								service.deleteBankBook(bankbook);
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
		BankBook bankbook = new BankBook();
		bankbook.setCustCode(cust);
		try {
			List<BankBook> list = service.showBankBookByCustName(bankbook);
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
			List<BankBook> list = service.showBankBooks();
			pCenter.loadTableData(list);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
