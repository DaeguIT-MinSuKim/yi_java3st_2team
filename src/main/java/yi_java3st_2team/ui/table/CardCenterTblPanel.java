package yi_java3st_2team.ui.table;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import yi_java3st_2team.dto.Card;
import yi_java3st_2team.dto.Customer;
import yi_java3st_2team.dto.Plan;
import yi_java3st_2team.ui.absPanel.AbsCenterTblPanel;

@SuppressWarnings("serial")
public class CardCenterTblPanel extends AbsCenterTblPanel<Card> {
	public CardCenterTblPanel() {
		
	}

	@Override
	protected void setTblWidthAlign() {
		setColumnAlign(SwingConstants.CENTER, 0,1,2,3,4,5);
		setColumnAlign(SwingConstants.RIGHT, 6,7);
		setColumnWidth(200,100,100,100,100,200,100,100);
	}

	@Override
	protected Object[] toArray(Card item) {
		return new Object[] {item.getCardNum(),item.getCustCode().getCustName(),item.getPlanCode().getPlanName(),item.getCardNum().substring(6,7).equals("1")?"체크카드":"신용카드",item.getCardSecuCode(),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.getCardIssueDate()),String.format("%,d", item.getCardBalance()),String.format("%,d", item.getCardLimit())};
	}

	@Override
	public void updateRow(Card item, int updateIdx) {
		model.setValueAt(item.getCardNum(), updateIdx, 0);
		model.setValueAt(item.getCustCode().getCustName(), updateIdx, 1);
		model.setValueAt(item.getPlanCode().getPlanName(), updateIdx, 2);
		model.setValueAt(item.getCardNum().substring(6,7).equals("1")?"체크카드":"신용카드", updateIdx, 3);
		model.setValueAt(item.getCardSecuCode(), updateIdx, 4);
		model.setValueAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.getCardIssueDate()), updateIdx, 5);
		model.setValueAt(String.format("%,d", item.getCardBalance()), updateIdx, 6);
		model.setValueAt(String.format("%,d", item.getCardLimit()), updateIdx, 7);
	}

	@Override
	public Card getSelectedItem() {
		int selIdx = getSelectedRowIdx();
		String cardNum = (String)model.getValueAt(selIdx, 0);
		Customer custCode = new Customer();
		custCode.setCustName((String)model.getValueAt(selIdx, 1));
		Plan planCode = new Plan();
		planCode.setPlanName((String)model.getValueAt(selIdx, 2));
		String cardDiv = (String)model.getValueAt(selIdx, 3);
		String cardSecuCode = (String)model.getValueAt(selIdx, 4);
		String dateStr = (String)model.getValueAt(selIdx, 5);
		Date cardIssueDate = null;
		try {
			cardIssueDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Card card = new Card(cardNum, custCode, planCode, cardSecuCode, cardIssueDate);
		if(cardDiv.equals("체크카드")) {
			String balanceStr = (String)model.getValueAt(selIdx, 6);
			long balance = Long.parseLong(balanceStr.replaceAll("[\\,]", ""));
			card.setCardBalance(balance);
		}
		else {
			String limitStr = (String)model.getValueAt(selIdx, 7);
			int limit = Integer.parseInt(limitStr.replaceAll("[\\,]", ""));
			card.setCardLimit(limit);
		}
		return card;
	}

	@Override
	protected String[] getColumns() {
		return new String[] {"카드번호","고객이름","상품이름","카드구분","카드보안코드","카드발급일","카드잔액","카드한도"};
	}

}
