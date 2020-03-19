package yi_java3st_2team.dao;

import java.sql.SQLException;
import java.util.List;

import yi_java3st_2team.dto.Card;
import yi_java3st_2team.dto.CardInfo;
import yi_java3st_2team.dto.Info;

public interface CardDao {
	public abstract List<Card> showCards() throws SQLException;
	public abstract List<Card> showCardByCustName(Card card) throws SQLException;
	public abstract List<Card> showCardByPlanName(Card card) throws SQLException;
	public abstract List<Card> showCardByCheckCard() throws SQLException;
	public abstract List<Card> showCardByCreditCard() throws SQLException;
	public abstract int insertCard(Card card) throws SQLException;
	public abstract int updateCard(Card card) throws SQLException;
	public abstract int deleteCard(Card card) throws SQLException;
	public abstract Info showCardInfoDaily(String custname) throws SQLException;
	public abstract Info showCardInfoWeekly(String custname) throws SQLException;
	public abstract Info showCardInfoMonthly(String custname) throws SQLException;
	public abstract Info showCardInfoYearly(String custname) throws SQLException;
	public abstract List<CardInfo> showCardInfo() throws SQLException;
}
