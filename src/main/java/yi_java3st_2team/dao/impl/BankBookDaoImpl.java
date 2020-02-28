package yi_java3st_2team.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import yi_java3st_2team.dao.BankBookDao;
import yi_java3st_2team.ds.LocalDataSource;
import yi_java3st_2team.dto.BankBook;
import yi_java3st_2team.dto.Customer;
import yi_java3st_2team.dto.Plan;

public class BankBookDaoImpl implements BankBookDao {
	private static final BankBookDaoImpl instance = new BankBookDaoImpl();
	private BankBookDaoImpl() {};
	
	public static BankBookDaoImpl getInstance() {
		return instance;
	}

	@Override
	public List<BankBook> showBankBooks() throws SQLException {
		List<BankBook> list = new ArrayList<>();
		String sql = "select b.accountNum,c.custCode,c.custName,p.planCode,p.planName,b.accountOpenDate,b.accountInterest from bankbook b left join customer c on b.custCode = c.custCode left join plan p on b.accountPlanCode = p.planCode";
		try(Connection con = LocalDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			while(rs.next()) {
				list.add(getBankBook(rs));
			}
		}
		return list;
	}

	private BankBook getBankBook(ResultSet rs) throws SQLException {
		String accountNum = rs.getString("b.accountnum");
		Customer custCode = new Customer(rs.getString("c.custcode"));
		custCode.setCustName(rs.getString("c.custname"));
		Plan accountPlanCode = new Plan(rs.getString("p.plancode"));
		accountPlanCode.setPlanName(rs.getString("p.planname"));
		Date accountOpenDate = rs.getTimestamp("b.accountOpenDate");
		float accountInterest = rs.getFloat("b.accountInterest");
		return new BankBook(accountNum, custCode, accountPlanCode, accountOpenDate, accountInterest);
	}

	@Override
	public List<BankBook> showBankBooksByCustName(BankBook bankbook) throws SQLException {
		List<BankBook> list = new ArrayList<>();
		String sql = "select b.accountNum,c.custCode,c.custName,p.planCode,p.planName,b.accountOpenDate,b.accountInterest from bankbook b left join customer c on b.custCode = c.custCode left join plan p on b.accountPlanCode = p.planCode where c.custname = ?";
		try(Connection con = LocalDataSource.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, bankbook.getCustCode().getCustName());
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					list.add(getBankBook(rs));
				}
			}
		}
		return list;
	}

	@Override
	public int insertBankBook(BankBook bankbook) throws SQLException {
		int res = -1;
		String sql = "insert into BankBook values(?,?,?,?,?,?)";
		try(Connection con = LocalDataSource.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, bankbook.getAccountNum());
			pstmt.setString(2, bankbook.getCustCode().getCustCode());
			pstmt.setString(3, bankbook.getAccountPlanCode().getPlanCode());
			pstmt.setTimestamp(4, new Timestamp(bankbook.getAccountOpenDate().getTime()));
			pstmt.setFloat(5, bankbook.getAccountInterest());
			pstmt.setLong(6, bankbook.getAccountBalance()==0?0:bankbook.getAccountBalance());
			res = pstmt.executeUpdate();
		}
		return res;
	}

	@Override
	public int updateBankBook(BankBook bankbook) throws SQLException {
		int res = -1;
		String sql = "update bankbook set accountopendate=?,accountinterest=? where custcode = (select custcode from customer where custname = ?) and accountnum = ?";
		try(Connection con = LocalDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setTimestamp(1, new Timestamp(bankbook.getAccountOpenDate().getTime()));
			pstmt.setFloat(2, bankbook.getAccountInterest());
			pstmt.setString(3,bankbook.getCustCode().getCustName());
			pstmt.setString(4, bankbook.getAccountNum());
			res = pstmt.executeUpdate();
		}
		return res;
	}

	@Override
	public int deleteBankBook(BankBook bankbook) throws SQLException {
		int res = -1;
		String sql = "delete from bankbook where custcode = (select custcode from customer where custname = ?) and accountnum = ?";
		try(Connection con = LocalDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, bankbook.getCustCode().getCustName());
			pstmt.setString(2, bankbook.getAccountNum());
			res = pstmt.executeUpdate();
		}
		return res;
	}

	@Override
	public int updateBankBook(Customer customer) throws SQLException {
		String sql = "update BankBook set accountBalance = ? where custCode=(select custCode from customer where custName=?) and accountNum =?";
		int res = -1;
		try(Connection con = LocalDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setLong(1,customer.getBankbook().getAccountBalance());
			pstmt.setString(2, customer.getCustName());
			pstmt.setString(3, customer.getBankbook().getAccountNum());
			
			res = pstmt.executeUpdate();
		}
		return res;
	}

}
