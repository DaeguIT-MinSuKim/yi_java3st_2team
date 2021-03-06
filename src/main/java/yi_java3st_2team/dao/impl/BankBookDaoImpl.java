package yi_java3st_2team.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import yi_java3st_2team.dao.BankBookDao;
import yi_java3st_2team.ds.LocalDataSource;
import yi_java3st_2team.dto.AccountInfo;
import yi_java3st_2team.dto.BankBook;
import yi_java3st_2team.dto.Card;
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
		String sql = "select b.accountNum,c.custCode,c.custName,p.planCode,p.planName,b.accountOpenDate,b.accountInterest from bankbook b left join customer c on b.custCode = c.custCode left join plan p on b.accountPlanCode = p.planCode where c.custname like ?";
		try(Connection con = LocalDataSource.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, "%" + bankbook.getCustCode().getCustName() + "%");
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
		String sql = "insert into BankBook values(?,?,?,?,?,?,(select empcode from employee where empname = ?),?)";
		try(Connection con = LocalDataSource.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, bankbook.getAccountNum());
			pstmt.setString(2, bankbook.getCustCode().getCustCode());
			pstmt.setString(3, bankbook.getAccountPlanCode().getPlanCode());
			pstmt.setTimestamp(4, new Timestamp(bankbook.getAccountOpenDate().getTime()));
			pstmt.setFloat(5, bankbook.getAccountInterest());
			pstmt.setLong(6, bankbook.getAccountBalance()==0?0:bankbook.getAccountBalance());
			pstmt.setString(7, bankbook.getEmployee().getEmpName());
			pstmt.setBoolean(8, bankbook.isConnectChk());
			res = pstmt.executeUpdate();
		}
		return res;
	}

	@Override
	public int updateBankBook(BankBook bankbook) throws SQLException {
		int res = -1;
		String sql = "update bankbook set accountopendate=?,accountinterest=? where custcode = (select custcode from customer where custname = ?) and accountnum = ? and accountplancode = (select plancode from plan where planname = ?)";
		try(Connection con = LocalDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setTimestamp(1, new Timestamp(bankbook.getAccountOpenDate().getTime()));
			pstmt.setFloat(2, bankbook.getAccountInterest());
			pstmt.setString(3,bankbook.getCustCode().getCustName());
			pstmt.setString(4, bankbook.getAccountNum());
			pstmt.setString(5, bankbook.getAccountPlanCode().getPlanName());
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
	public int updateBankBalance(Customer customer) throws SQLException {
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

	@Override
	public String showDPTotalAmount() throws SQLException {
			String sql = "select sum(accountBalance) from bankbook where SUBSTRING_INDEX(SUBSTRING_INDEX(accountNum, \'-\', 2), \'-\', -1) = \'11\'";
			String sumDpBalance = null;
			try(Connection con = LocalDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					sumDpBalance = rs.getString(1);
				}
			}
		return sumDpBalance;
	}

	@Override
	public String showSvTotalAmount() throws SQLException {
		String sql = "select sum(accountBalance) from bankbook where SUBSTRING_INDEX(SUBSTRING_INDEX(accountNum, \'-\', 2), \'-\', -1) = \'12\'";
		String sumDpBalance = null;
		try(Connection con = LocalDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery()){
			if(rs.next()) {
				sumDpBalance = rs.getString(1);
			}
		}
	return sumDpBalance;
	}

	@Override
	public String showLoTotalAmount() throws SQLException {
		String sql = "select sum(accountBalance) from bankbook where SUBSTRING_INDEX(SUBSTRING_INDEX(accountNum, \'-\', 2), \'-\', -1) = \'13\'";
		String sumDpBalance = null;
		try(Connection con = LocalDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery()){
			if(rs.next()) {
				sumDpBalance = rs.getString(1);
			}
		}
	return sumDpBalance;
	}

	@Override
	public List<String> showOpenDPMonth() throws SQLException {
		String sql = "select SUBSTRING_INDEX(SUBSTRING_INDEX(accountOpenDate, '-', 2), '-', -1) from bankbook where SUBSTRING_INDEX(SUBSTRING_INDEX(accountNum, '-', 2), '-', -1) =\'11\'";
		List<String> list = null;
		try(Connection con = LocalDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				list = new ArrayList<>();
				do {
					list.add(rs.getString(1));
				}while(rs.next());
			}
		}
		return list;
	}

	@Override
	public List<String> showOpenSvMonth() throws SQLException {
		String sql = "select SUBSTRING_INDEX(SUBSTRING_INDEX(accountOpenDate, '-', 2), '-', -1) from bankbook where SUBSTRING_INDEX(SUBSTRING_INDEX(accountNum, '-', 2), '-', -1) =\'12\'";
		List<String> list = null;
		try(Connection con = LocalDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				list = new ArrayList<>();
				do {
					list.add(rs.getString(1));
				}while(rs.next());
			}
		}
		return list;
	}

	@Override
	public List<String> showOpenLoMonth() throws SQLException {
		String sql = "select SUBSTRING_INDEX(SUBSTRING_INDEX(accountOpenDate, '-', 2), '-', -1) from bankbook where SUBSTRING_INDEX(SUBSTRING_INDEX(accountNum, '-', 2), '-', -1) =\'13\'";
		List<String> list = null;
		try(Connection con = LocalDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				list = new ArrayList<>();
				do {
					list.add(rs.getString(1));
				}while(rs.next());
			}
		}
		return list;
	}

	@Override
	public List<String> showDepositMonth() throws SQLException {
		String sql = "select  SUBSTRING_INDEX(SUBSTRING_INDEX(accountTransDate, '-', 2), '-', -1) from cust_dw_audit where dw =?";
		List<String> list = null;
		try(Connection con = LocalDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setString(1, "입금");
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				list = new ArrayList<>();
				do {
					list.add(rs.getString(1));
				}while(rs.next());
			}
		}
		return list;
	}
	
	@Override
	public List<String> showWithDrawalMonth() throws SQLException {
		String sql = "select  SUBSTRING_INDEX(SUBSTRING_INDEX(accountTransDate, '-', 2), '-', -1) from cust_dw_audit where dw =?";
		List<String> list = null;
		try(Connection con = LocalDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setString(1, "출금");
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				list = new ArrayList<>();
				do {
					list.add(rs.getString(1));
				}while(rs.next());
			}
		}
		return list;
	}

	@Override
	public List<AccountInfo> showBankBookInfoDaily() throws SQLException {
		List<AccountInfo> list = new ArrayList<>();
		String sql = "select custname,if(substring(accountnum,9,1)=1,'예금',if(substring(accountnum,9,1)=2,'적금','마이너스')) as 'div',count(transDate) as 'count' from bankbookinfo where date(transdate) = date(now()) group by accountnum";
		try(Connection con = LocalDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					list.add(getBankBookInfo(rs));
				}
			}
		}
		return list;
	}

	private AccountInfo getBankBookInfo(ResultSet rs) throws SQLException {
		String custName = rs.getString("custname");
		String div = rs.getString("div");
		int count = rs.getInt("count");
		return new AccountInfo(custName, div, count);
	}

	@Override
	public List<AccountInfo> showBankBookInfoWeekly() throws SQLException {
		List<AccountInfo> list = new ArrayList<>();
		String sql = "select custname,if(substring(accountnum,9,1)=1,'예금',if(substring(accountnum,9,1)=2,'적금','마이너스')) as 'div',count(transDate) as 'count' from bankbookinfo where week(transdate,1) = week(now(),1) group by accountnum";
		try(Connection con = LocalDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					list.add(getBankBookInfo(rs));
				}
			}
		}
		return list;
	}

	@Override
	public List<AccountInfo> showBankBookInfoMonthly() throws SQLException {
		List<AccountInfo> list = new ArrayList<>();
		String sql = "select custname,if(substring(accountnum,9,1)=1,'예금',if(substring(accountnum,9,1)=2,'적금','마이너스')) as 'div',count(transDate) as 'count' from bankbookinfo where month(transdate) = month(now()) group by accountnum";
		try(Connection con = LocalDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					list.add(getBankBookInfo(rs));
				}
			}
		}
		return list;
	}

	@Override
	public List<AccountInfo> showBankBookInfoYearly() throws SQLException {
		List<AccountInfo> list = new ArrayList<>();
		String sql = "select custname,if(substring(accountnum,9,1)=1,'예금',if(substring(accountnum,9,1)=2,'적금','마이너스')) as 'div',count(transDate) as 'count' from bankbookinfo where year(transdate) = year(now()) group by accountnum";
		try(Connection con = LocalDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					list.add(getBankBookInfo(rs));
				}
			}
		}
		return list;
	}

	@Override
	public int insertDormantAccountProcedure(BankBook bankbook) throws SQLException {
		int res = -1;
		String sql = "call make_dormant(?,?)";
		try(Connection con = LocalDataSource.getConnection();
				PreparedStatement pstmt = con.prepareCall(sql)) {
			pstmt.setString(1, bankbook.getCustCode().getCustName());
			pstmt.setString(2, bankbook.getAccountPlanCode().getPlanName());
			res = pstmt.executeUpdate();
		}
		return res;
	}

	@Override
	public int insertTerminationAccountProcedure(BankBook bankbook) throws SQLException {
		int res = -1;
		String sql = "call make_termination(?,?)";
		try(Connection con = LocalDataSource.getConnection();
				PreparedStatement pstmt = con.prepareCall(sql)) {
			pstmt.setString(1, bankbook.getCustCode().getCustName());
			pstmt.setString(2, bankbook.getAccountPlanCode().getPlanName());
			res = pstmt.executeUpdate();
		}
		return res;
	}

	@Override
	public List<AccountInfo> showBankBookDormantAccountInfo() throws SQLException {
		List<AccountInfo> list = new ArrayList<>();
		String sql = "select * from changebankbookdormantinfo";
		try(Connection con = LocalDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			while(rs.next()) {
				list.add(getAccountInfo(rs));
			}
		}
		return list;
	}

	private AccountInfo getAccountInfo(ResultSet rs) throws SQLException {
		String custname = rs.getString("custname");
		String accountnum = rs.getString("accountnum");
		Date transDate = rs.getTimestamp("changedate");
		return new AccountInfo(custname, accountnum, transDate);
	}

	@Override
	public List<AccountInfo> showBankBookTerminationAccountInfo() throws SQLException {
		List<AccountInfo> list = new ArrayList<>();
		String sql = "select * from changebankbookterminationinfo";
		try(Connection con = LocalDataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery()) {
			while(rs.next()) {
				list.add(getAccountInfo(rs));
			}
		}
		return list;
	}

	@Override
	public List<BankBook> showBankBooksByAccountNum(BankBook bankbook) throws SQLException {
		List<BankBook> list = new ArrayList<>();
		String sql = "select b.accountNum,c.custCode,c.custName,p.planCode,p.planName,b.accountOpenDate,b.accountInterest from bankbook b left join customer c on b.custCode = c.custCode left join plan p on b.accountPlanCode = p.planCode where b.accountnum like ?";
		try(Connection con = LocalDataSource.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, "%"+bankbook.getAccountNum()+"%");
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					list.add(getBankBook(rs));
				}
			}
		}
		return list;
	}

	@Override
	public List<BankBook> showBankBooksByPlanName(BankBook bankbook) throws SQLException {
		List<BankBook> list = new ArrayList<>();
		String sql = "select b.accountNum,c.custCode,c.custName,p.planCode,p.planName,b.accountOpenDate,b.accountInterest from bankbook b left join customer c on b.custCode = c.custCode left join plan p on b.accountPlanCode = p.planCode where p.planname like ?";
		try(Connection con = LocalDataSource.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, "%"+bankbook.getAccountPlanCode().getPlanName()+"%");
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					list.add(getBankBook(rs));
				}
			}
		}
		return list;
	}

	@Override
	public List<BankBook> showBankBooksByDeposit() throws SQLException {
		List<BankBook> list = new ArrayList<>();
		String sql = "select b.accountNum,c.custCode,c.custName,p.planCode,p.planName,b.accountOpenDate,b.accountInterest from bankbook b left join customer c on b.custCode = c.custCode left join plan p on b.accountPlanCode = p.planCode where accountnum like '%-11-%'";
		try(Connection con = LocalDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			while(rs.next()) {
				list.add(getBankBook(rs));
			}
		}
		return list;
	}

	@Override
	public List<BankBook> showBankBooksBySaving() throws SQLException {
		List<BankBook> list = new ArrayList<>();
		String sql = "select b.accountNum,c.custCode,c.custName,p.planCode,p.planName,b.accountOpenDate,b.accountInterest from bankbook b left join customer c on b.custCode = c.custCode left join plan p on b.accountPlanCode = p.planCode where accountnum like '%-12-%'";
		try(Connection con = LocalDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			while(rs.next()) {
				list.add(getBankBook(rs));
			}
		}
		return list;
	}

	@Override
	public List<BankBook> showBankBooksByMinus() throws SQLException {
		List<BankBook> list = new ArrayList<>();
		String sql = "select b.accountNum,c.custCode,c.custName,p.planCode,p.planName,b.accountOpenDate,b.accountInterest from bankbook b left join customer c on b.custCode = c.custCode left join plan p on b.accountPlanCode = p.planCode where accountnum like '%-13-%'";
		try(Connection con = LocalDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			while(rs.next()) {
				list.add(getBankBook(rs));
			}
		}
		return list;
	}

	@Override
	public int updateCardBalance(Customer customer) throws SQLException {
		int res = -1;
		String sql = "call change_cardbalance(?,?,?)";
		try(Connection con = LocalDataSource.getConnection();
				PreparedStatement pstmt = con.prepareCall(sql)) {
			pstmt.setString(1, customer.getCustName());
			pstmt.setLong(2, customer.getBankbook().getAccountBalance());
			pstmt.setString(3, customer.getBankbook().getAccountNum());
			res = pstmt.executeUpdate();
		}
		return res;
	}

	@Override
	public List<BankBook> showBankBookByIsConnect(Card card) throws SQLException {
		List<BankBook> list = new ArrayList<>();
		String sql = "select * from bankbook_deposit_connect_to_card_info where custcode = (select custcode from customer where custname = ?)";
		try(Connection con = LocalDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, card.getCustCode().getCustName());
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					list.add(getBankBookConnect(rs));
				}
			}
			return list;
		}
				
	}

	private BankBook getBankBookConnect(ResultSet rs) throws SQLException {
		String accountNum = rs.getString("accountnum");
		Customer custCode = new Customer(rs.getString("custcode"));
		boolean connectChk = rs.getInt("connectChk")==0?false:true;
		return new BankBook(accountNum, custCode, connectChk);
	}
	@Override
	public int updateConnectChk(Card card) throws SQLException {
		int res = -1;
		String sql = "update bankbook set connectchk = ? where custcode = (select custcode from customer where custname = ?) and accountnum = ?";
		try(Connection con = LocalDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setBoolean(1, card.getBankbook().isConnectChk());
			pstmt.setString(2, card.getCustCode().getCustName());
			pstmt.setString(3, card.getBankbook().getAccountNum());
			res = pstmt.executeUpdate();
		}
		return res;
	}

	@Override
	public int updateCardBalanceByAccountBalance(Card card) throws SQLException {
		int res = -1;
		String sql = "update card set cardbalance = (select accountbalance from bankbook where accountnum = ?) where cardnum = ? and custcode = (select custcode from customer where custname = ?)";
		try(Connection con = LocalDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1,card.getBankbook().getAccountNum());
			pstmt.setString(2, card.getCardNum());
			pstmt.setString(3, card.getCustCode().getCustName());
			res = pstmt.executeUpdate();
		}
		return res;
	}
}
