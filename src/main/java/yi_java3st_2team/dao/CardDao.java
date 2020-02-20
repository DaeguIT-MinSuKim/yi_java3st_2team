package yi_java3st_2team.dao;

import java.sql.SQLException;
import java.util.List;

import yi_java3st_2team.dto.Card;

public interface CardDao {
	public abstract List<Card> showCards() throws SQLException;
	public abstract Card showCardByCustCode(Card card) throws SQLException;
	public abstract int insertCard(Card card) throws SQLException;
	public abstract int updateCard(Card card) throws SQLException;
	public abstract int deleteCard(Card card) throws SQLException;
}
