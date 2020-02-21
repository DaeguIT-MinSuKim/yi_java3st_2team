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

	/**
	 * Create the panel.
	 */
	public CardCenterUIPanel() {
		initialize();
	}
	private void initialize() {
		service = new CardService();
		dlgCard = new DlgCard();
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
		ActionListener popMenuListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("추가")) {
					dlgCard.initCmbModel(service);
					dlgCard.setTitle("카드 " + e.getActionCommand());
					dlgCard.setModal(true);
					dlgCard.setVisible(true);	
				}
				else if(e.getActionCommand().equals("수정")) {
					try {
						int idx = pCenter.getSelectedRowIdx();
					}
					catch(RuntimeException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
				else {
					try {
						int idx = pCenter.getSelectedRowIdx();
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
		pNorth.tfClear();
	}
}
