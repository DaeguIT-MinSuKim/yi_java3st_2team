package yi_java3st_2team.ui.service;

import java.sql.SQLException;
import java.util.List;

import yi_java3st_2team.dao.CardDao;
import yi_java3st_2team.dao.CustomerDao;
import yi_java3st_2team.dao.PlanDao;
import yi_java3st_2team.dao.impl.CardDaoImpl;
import yi_java3st_2team.dao.impl.CustomerDaoImpl;
import yi_java3st_2team.dao.impl.PlanDaoImpl;
import yi_java3st_2team.dto.Card;
import yi_java3st_2team.dto.Customer;
import yi_java3st_2team.dto.Plan;

public class CardService {
	private CardDao cardDao;
	private CustomerDao custDao;
	private PlanDao planDao;

	public CardService() {
		cardDao = CardDaoImpl.getInstance();
		custDao = CustomerDaoImpl.getInstance();
		planDao = PlanDaoImpl.getInstance();
	}
	public List<Card> showCards() throws SQLException {
		return cardDao.showCards();
	}
	public List<Card> showCardByCustName(Card card) throws SQLException {
		return cardDao.showCardByCustName(card);
	}
	public int insertCard(Card card) throws SQLException {
		return cardDao.insertCard(card);
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
}

