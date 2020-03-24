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
import yi_java3st_2team.dto.Plan;
import yi_java3st_2team.ui.MainFrame;
import yi_java3st_2team.ui.dialog.DlgCard;
import yi_java3st_2team.ui.dialog.DlgConnectBankBookInfo;
import yi_java3st_2team.ui.service.BankBookService;
import yi_java3st_2team.ui.service.CardService;
import yi_java3st_2team.ui.service.CustomerService;
import yi_java3st_2team.ui.table.CardCenterTblPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class CardCenterUIPanel extends JPanel implements ActionListener {
	private CardCenterNorthSearchPanel pNorth;
	private CardCenterTblPanel pCenter;
	private CustomerService customerService;
	private CardService cardService;
	private DlgCard dlgCard;
	private DlgConnectBankBookInfo dlgInfo;
	private int selIdx;
	private MainFrame main;

	/**
	 * Create the panel.
	 */
	public CardCenterUIPanel() {
		initialize();
	}
	private void initialize() {
		if(customerService==null) {
			customerService = new CustomerService();
		}
		if(cardService==null) {
			cardService = new CardService();
		}
		setLayout(new BorderLayout(0, 0));
		
		pNorth = new CardCenterNorthSearchPanel();
		pNorth.getBtnCancel().addActionListener(this);
		pNorth.getBtnSearch().addActionListener(this);
		add(pNorth, BorderLayout.NORTH);
		
		pCenter = new CardCenterTblPanel();
		pCenter.setBorder(new EmptyBorder(10, 50, 30, 50));
		add(pCenter, BorderLayout.CENTER);
		try {
			pCenter.loadTableData(cardService.showCards());
			pCenter.setPopupMenu(getTblPopMenu());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public CardCenterTblPanel getpCenter() {
		return pCenter;
	}
	public void setpCenter(CardCenterTblPanel pCenter) {
		this.pCenter = pCenter;
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
				Card card = dlgCard.getItem();
				if(e.getActionCommand().equals("추가")) {
					try {
						JOptionPane.showMessageDialog(null, card.getCardNum().substring(6, 7));
						if(card.getCardNum().substring(6,7).equals("1")) {
							if(cardService.showBankBookIsConnect(card).size()==0) {
								JOptionPane.showMessageDialog(null, "선택할 수 있는 예금 계좌가 없습니다. 통장을 먼저 만드세요");
								return;
							}
							else {
								dlgInfo = new DlgConnectBankBookInfo();
								List<BankBook> list = cardService.showBankBookIsConnect(card);
								dlgInfo.getPanel().loadTableData(list);
								dlgInfo.setDlgCard(dlgCard);
								dlgInfo.setModal(true);
								dlgInfo.setVisible(true);
							}
						}
						else {
							cardService.insertCardCredit(card);
							dlgCard.dispose();
							JOptionPane.showMessageDialog(null, "추가되었습니다");
							pCenter.loadTableData(cardService.showCards());
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					try {
						cardService.updateCard(card);
						cardService.updateAccountBalance(card);
						main.getCust_DW_UIpanel().getPanel_1().loadTableData(customerService.showCustomersByBalance());
						pCenter.loadTableData(cardService.showCards());
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
					dlgCard.initCmbModel(cardService);
					setUIPanel();
					dlgCard.setEmp(main.getEmpAuth());
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
						dlgCard.initCmbModel(cardService);
						dlgCard.setTitle("카드" + e.getActionCommand());
						dlgCard.getBtnOk().setText(e.getActionCommand());
						dlgCard.getBtnOk().addActionListener(myDlgListener);
						dlgCard.getCmbCust().setEnabled(false);
						dlgCard.getTfCardNum().setEditable(false);
						dlgCard.getCmbPlan().setEnabled(false);
						if(selCard.getCardBalance()==0) {
							dlgCard.getTfCardBalance().setEditable(false);
						}
						else {
							dlgCard.getTfCardLimit().setEditable(false);
						}
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
								cardService.deleteCard(selCard);
								cardService.updateConnectChk(selCard);
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
		String selectMenu = (String)pNorth.getCmbSearchList().getSelectedItem();
		switch(selectMenu) {
		case "검색구분":
			JOptionPane.showMessageDialog(null, "검색 구분을 선택해주세요");
			break;
		case "고객이름":
			if(pNorth.getTfSearch().getText().length()!=0) {
				Customer cust = new Customer();
				cust.setCustName(pNorth.getTfSearch().getText().trim());
				Card card = new Card();
				card.setCustCode(cust);
				try {
					List<Card> list = cardService.showCardByCustName(card);
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
			else {
				JOptionPane.showMessageDialog(null, "고객명을 입력하세요");
			}
			break;
		case "상품명":
			if(pNorth.getTfSearch().getText().length()!=0) {
				Plan plan = new Plan();
				plan.setPlanName(pNorth.getTfSearch().getText().trim());
				Card card = new Card();
				card.setPlanCode(plan);	
				try {
					List<Card> list = cardService.showCardByPlanName(card);
					if(list.size()==0) {
						JOptionPane.showMessageDialog(null, "그런 상품을 찾을 수 없습니다");
						return;
					}
					pCenter.loadTableData(list);
					JOptionPane.showMessageDialog(null, "검색이 완료되었습니다");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "상품명을 입력하세요");
			}
			break;
		case "카드구분":
			if(pNorth.getTfSearch().getText().trim().equals("체크카드")) { 
				try {
					List<Card> list = cardService.showCardByCheckCard();
					pCenter.loadTableData(list);
					JOptionPane.showMessageDialog(null, "검색이 완료되었습니다");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(pNorth.getTfSearch().getText().trim().equals("신용카드")) {
				try {
					List<Card> list = cardService.showCardByCreditCard();
					pCenter.loadTableData(list);
					JOptionPane.showMessageDialog(null, "검색이 완료되었습니다");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "체크카드나 신용카드를 입력하세요");
			}
			break;
		}
	}
	protected void pNorthBtnCancelActionPerformed(ActionEvent e) {
		try {
			pNorth.tfClear();
			List<Card> list = cardService.showCards();
			pCenter.loadTableData(list);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public CardCenterNorthSearchPanel getpNorth() {
		return pNorth;
	}
	private void setUIPanel() {
		dlgCard.setUiPanel(this);
	}
	
	
}
