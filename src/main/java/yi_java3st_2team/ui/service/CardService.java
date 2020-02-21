package yi_java3st_2team.ui.service;

import java.sql.SQLException;
import java.util.List;

import yi_java3st_2team.dao.CardDao;
import yi_java3st_2team.dao.impl.CardDaoImpl;
import yi_java3st_2team.dto.Card;

public class CardService {
	private CardDao dao;

	public CardService() {
		dao = CardDaoImpl.getInstance();
	}
	public List<Card> showCards() throws SQLException {
		return dao.showCards();
	}
	public List<Card> showCardByCustName(Card card) throws SQLException {
		return dao.showCardByCustName(card);
	}
	public int insertCard(Card card) throws SQLException {
		return dao.insertCard(card);
	}
	public int updateCard(Card card) throws SQLException {
		return dao.updateCard(card);
	}
	public int deleteCard(Card card) throws SQLException {
		return dao.deleteCard(card);
	}
}

