package yi_java3st_2team.dao;

import java.sql.SQLException;
import java.util.List;

import yi_java3st_2team.dto.AccountInfo;
import yi_java3st_2team.dto.Card;
import yi_java3st_2team.dto.CardInfo;

public interface CardDao {
	public abstract List<Card> showCards() throws SQLException;
	public abstract List<Card> showCardByCustName(Card card) throws SQLException;
	public abstract List<Card> showCardByPlanName(Card card) throws SQLException;
	public abstract List<Card> showCardByCheckCard() throws SQLException;
	public abstract List<Card> showCardByCreditCard() throws SQLException;
	public Card showCardByAccountNum(Card card) throws SQLException;
	public abstract int insertCardCheck(Card card) throws SQLException;
	public abstract int insertCardCredit(Card card) throws SQLException;
	public abstract int updateCard(Card card) throws SQLException;
	public abstract int deleteCard(Card card) throws SQLException;
	public abstract List<CardInfo> showCardInfoDaily() throws SQLException;
	public abstract List<CardInfo> showCardInfoWeekly() throws SQLException;
	public abstract List<CardInfo> showCardInfoMonthly() throws SQLException;
	public abstract List<CardInfo> showCardInfoYearly() throws SQLException;
	public abstract List<CardInfo> showCardInfo() throws SQLException;
	public abstract int updateAccountBalance(Card card) throws SQLException;
}
