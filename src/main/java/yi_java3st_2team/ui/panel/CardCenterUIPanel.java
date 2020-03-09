package yi_java3st_2team.ui.panel;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.List;

import yi_java3st_2team.dto.Card;
import yi_java3st_2team.dto.Customer;
import yi_java3st_2team.ui.service.CardService;
import yi_java3st_2team.ui.table.CardCenterTblPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class CardCenterUIPanel extends JPanel implements ActionListener {
	private CardCenterNorthSearchPanel pNorth;
	private CardCenterTblPanel pCenter;
	private CardService service;
	private DlgCard dlgCard;
	private int selIdx;

	/**
	 * Create the panel.
	 */
	public CardCenterUIPanel() {
		initialize();
	}
	private void initialize() {
		if(service==null) {
			service = new CardService();
		}
		setLayout(new BorderLayout(0, 0));
		
		pNorth = new CardCenterNorthSearchPanel();
		pNorth.getBtnCancel().addActionListener(this);
		pNorth.getBtnSearch().addActionListener(this);
		add(pNorth, BorderLayout.NORTH);
		
		pCenter = new CardCenterTblPanel();
		add(pCenter, BorderLayout.CENTER);
		try {
			pCenter.loadTableData(service.showCards());
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
						Card card = dlgCard.getItem();
						pCenter.addItem(card);
						service.insertCard(card);
						pCenter.loadTableData(service.showCards());
						JOptionPane.showMessageDialog(null, "추가되었습니다");
						dlgCard.dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					try {
						Card card = dlgCard.getItem();
						pCenter.updateRow(card, pCenter.getSelectedRowIdx());
						service.updateCard(card);
						pCenter.loadTableData(service.showCards());
						JOptionPane.showMessageDialog(null, "수정되었습니다");
						dlgCard.dispose();
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
					dlgCard = new DlgCard();
					dlgCard.initCmbModel(service);
					dlgCard.setTitle("카드 " + e.getActionCommand());
					dlgCard.getBtnOk().setText(e.getActionCommand());
					dlgCard.getBtnOk().addActionListener(myDlgListener);
					dlgCard.setModal(true);
					dlgCard.setVisible(true);	
				}
				else if(e.getActionCommand().equals("수정")) {
					try {
						selIdx = pCenter.getSelectedRowIdx();
						Card selCard = pCenter.getSelectedItem();
						dlgCard = new DlgCard();
						dlgCard.initCmbModel(service);
						dlgCard.setTitle("카드" + e.getActionCommand());
						dlgCard.getBtnOk().setText(e.getActionCommand());
						dlgCard.getBtnOk().addActionListener(myDlgListener);
						dlgCard.getTfCardNum().setEnabled(false);
						dlgCard.getCmbCust().setEnabled(false);
						dlgCard.getCmbPlan().setEnabled(false);
						dlgCard.setItem(selCard);
						dlgCard.setModal(true);
						dlgCard.setVisible(true);
					}
					catch(RuntimeException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
				else {
					try {
						Card selCard = pCenter.getSelectedItem();
						selIdx = pCenter.getSelectedRowIdx();
						int res = JOptionPane.showConfirmDialog(null, "정말 삭제하시겠습니까?");
						if(res==0) {
							try {
								pCenter.removeItem(selIdx);
								service.deleteCard(selCard);
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
		Card card = new Card();
		card.setCustCode(cust);
		try {
			List<Card> list = service.showCardByCustName(card);
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
			List<Card> list = service.showCards();
			pCenter.loadTableData(list);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
