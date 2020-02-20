package yi_java3st_2team.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import yi_java3st_2team.dao.CardDao;
import yi_java3st_2team.ds.MySqlDataSource;
import yi_java3st_2team.dto.Card;
import yi_java3st_2team.dto.Customer;
import yi_java3st_2team.dto.Plan;

public class CardDaoImpl implements CardDao {
	private static final CardDaoImpl instance = new CardDaoImpl();
	private CardDaoImpl() {};
	public static CardDaoImpl getInstance() {
		return instance;
	}

	@Override
	public List<Card> showCards() throws SQLException {
		List<Card> list = new ArrayList<>();
		String sql = "select c.cardnum,cs.custcode,cs.custname,p.plancode,p.planname,c.cardsecucode,c.cardissuedate,c.cardlimit,c.cardbalance from card c left join customer cs on c.custcode = cs.custcode left join plan p on p.planCode = c.plancode";
		try(Connection con = MySqlDataSource.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			while(rs.next()) {
				list.add(getCardInfo(rs));
			}
		}
		return list;
	}

	private Card getCardInfo(ResultSet rs) throws SQLException {
		String cardNum = rs.getString("c.cardnum");
		Customer custCode = new Customer(rs.getString("cs.custcode"));
		custCode.setCustName(rs.getString("cs.custname"));
		Plan planCode = new Plan(rs.getString("p.plancode"));
		planCode.setPlanName("p.planname");
		String cardSecuCode = rs.getString("c.cardsecucode");
		Date cardIssueDate = rs.getTimestamp("c.cardissuedate");
		Card card = new Card(cardNum, custCode, planCode, cardSecuCode, cardIssueDate);
		if(cardNum.substring(8).equals("1")) {
			card.setCardBalance(rs.getLong("c.cardblance"));
		}
		else {
			card.setCardLimit(rs.getInt("c.cardlimit"));
		}
		return card;
	}
	@Override
	public Card showCardByCustCode(Card card) throws SQLException {
		String sql = "select c.cardnum,cs.custcode,cs.custname,p.plancode,p.planname,c.cardsecucode,c.cardissuedate,c.cardlimit,c.cardbalance from card c left join customer cs on c.custcode = cs.custcode left join plan p on p.planCode = c.plancode where c.custcode = ?";
		try(Connection con = MySqlDataSource.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, card.getCustCode().getCustCode());
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					return getCardInfo(rs);
				}
			}
		}
		return null;
	}

	@Override
	public int insertCard(Card card) throws SQLException {
		int res = -1;
		StringBuilder createSql= new StringBuilder("insert into card(cardnum,custcode,plancode,cardsecucode,cardissuedate,");
		if(card.getCardNum().substring(8).equals("1")) {
			createSql.append("cardbalance values(?,?,?,?,?,?,?)");
		}
		else {
			createSql.append("cardlimit values(?,?,?,?,?,?,?)");
		}
		String sql = createSql.toString();
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, card.getCardNum());
			pstmt.setString(2, card.getCustCode().getCustCode());
			pstmt.setString(3, card.getPlanCode().getPlanCode());
			pstmt.setString(4, card.getCardSecuCode());
			pstmt.setTimestamp(5, new Timestamp(card.getCardIssueDate().getTime()));
			if(card.getCardNum().substring(8).equals("1")) {
				pstmt.setLong(6, card.getCardBalance());
			}
			else {
				pstmt.setInt(6, card.getCardLimit());
			}
			res = pstmt.executeUpdate();
		}
		return res;
	}

	@Override
	public int updateCard(Card card) throws SQLException {
		int res = -1;
		StringBuilder updateSql = new StringBuilder("update card set cardnum = ?, cardsecucode = ?, cardissuedate = ?");
		if(card.getCardNum().equals("1")) {
			updateSql.append(",cardbalance = ? where custcode = ?");
		}
		else {
			updateSql.append(",cardlimit = ? where custcode = ?");
		}
		String sql = updateSql.toString();
		try(Connection con = MySqlDataSource.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, card.getCardNum());
			pstmt.setString(2, card.getCardSecuCode());
			pstmt.setTimestamp(3, new Timestamp(card.getCardIssueDate().getTime()));
			if(card.getCardNum().equals("1")) {
				pstmt.setLong(4, card.getCardBalance());
			}
			else {
				pstmt.setInt(4, card.getCardLimit());
			}
			pstmt.setString(5, card.getCustCode().getCustCode());
			res = pstmt.executeUpdate();
		}
		return res;
	}

	@Override
	public int deleteCard(Card card) throws SQLException {
		int res = 0;
		String sql = "delete from card where = custcode = ?";
		try(Connection con = MySqlDataSource.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, card.getCustCode().getCustCode());
			res = pstmt.executeUpdate();
		}
		return res;
	}

}
