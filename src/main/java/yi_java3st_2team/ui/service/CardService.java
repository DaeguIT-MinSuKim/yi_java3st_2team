package yi_java3st_2team.ui.service;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import yi_java3st_2team.dao.BankBookDao;
import yi_java3st_2team.dao.CardDao;
import yi_java3st_2team.dao.CustomerDao;
import yi_java3st_2team.dao.PlanDao;
import yi_java3st_2team.dao.impl.BankBookDaoImpl;
import yi_java3st_2team.dao.impl.CardDaoImpl;
import yi_java3st_2team.dao.impl.CustomerDaoImpl;
import yi_java3st_2team.dao.impl.PlanDaoImpl;
import yi_java3st_2team.dto.BankBook;
import yi_java3st_2team.dto.Card;
import yi_java3st_2team.dto.CardInfo;
import yi_java3st_2team.dto.Customer;
import yi_java3st_2team.dto.Plan;

public class CardService {
	private CardDao cardDao;
	private CustomerDao custDao;
	private PlanDao planDao;
	private BankBookDao bankbookDao;

	public CardService() {
		cardDao = CardDaoImpl.getInstance();
		custDao = CustomerDaoImpl.getInstance();
		planDao = PlanDaoImpl.getInstance();
		bankbookDao = BankBookDaoImpl.getInstance();
	}
	public List<Card> showCards() throws SQLException {
		return cardDao.showCards();
	}
	public List<Card> showCardByCustName(Card card) throws SQLException {
		return cardDao.showCardByCustName(card);
	}
	public List<Card> showCardByPlanName(Card card) throws SQLException {
		return cardDao.showCardByPlanName(card);
	}
	public List<Card> showCardByCheckCard() throws SQLException {
		return cardDao.showCardByCheckCard();
	}
	public List<Card> showCardByCreditCard() throws SQLException {
		return cardDao.showCardByCreditCard();
	}
	public int insertCardCredit(Card card) throws SQLException {
		return cardDao.insertCardCredit(card);
	}
	public int updateCard(Card card) throws SQLException {
		return cardDao.updateCard(card);
	}
	public int deleteCard(Card card) throws SQLException {
		return cardDao.deleteCard(card);
	}
	public List<Customer> showCusts() throws SQLException {
		return custDao.selectCustomerAll();
	}
	public List<Plan> showPlansByCard() throws SQLException {
		return planDao.selectPlanByCard();
	}
	public List<Plan> showPlansByCardNormal() throws SQLException {
		return planDao.selectPlanByCardNormal();
	}
	public List<CardInfo> cardInfoDaily() throws SQLException {
		return cardDao.showCardInfoDaily();
	}
	public List<CardInfo> cardInfoWeekly() throws SQLException {
		return cardDao.showCardInfoWeekly();
	}
	public List<CardInfo> cardInfoMonthly() throws SQLException {
		return cardDao.showCardInfoMonthly();
	}
	public List<CardInfo> cardInfoYearly() throws SQLException {
		return cardDao.showCardInfoYearly();
	}
	public List<CardInfo> showCardInfo() throws SQLException {
		return cardDao.showCardInfo();
	}
	public int updateAccountBalance(Card card) throws SQLException {
		return cardDao.updateAccountBalance(card);
	}
	public List<BankBook> showBankBookIsConnect(Card card) throws SQLException {
		return bankbookDao.showBankBookByIsConnect(card);
	}
	public int insertCheckCardProcedure(Card card) throws SQLException {
		try {
			int res = 0;
			res += cardDao.insertCardCheck(card);
			res += bankbookDao.updateConnectChk(card);
			res += bankbookDao.updateCardBalanceByAccountBalance(card);
			if(res==3) {
				return res;
			}
			else {
				new RuntimeException();
			}
		}
		catch(RuntimeException e) {
			JOptionPane.showMessageDialog(null, "실패하였습니다");
		}
		return -1;
	}
	public int deleteCheckCardProcedure(Card card) throws SQLException {
		try {
			int res = 0;
			res += cardDao.deleteCard(card);
			res += bankbookDao.updateConnectChk(card);
			if(res==2) {
				return res;
			}
			else {
				new RuntimeException();
			}
		}
		catch(RuntimeException e) {
			JOptionPane.showMessageDialog(null, "실패하였습니다");
		}
		return -1;
	}
	public Card showCardByAccountnum(Card card) throws SQLException {
		return cardDao.showCardByAccountNum(card);
	}
}

