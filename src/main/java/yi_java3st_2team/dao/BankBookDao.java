package yi_java3st_2team.dao;

import java.sql.SQLException;
import java.util.List;

import yi_java3st_2team.dto.BankBook;
import yi_java3st_2team.dto.AccountInfo;
import yi_java3st_2team.dto.Customer;

public interface BankBookDao {
	public abstract List<BankBook> showBankBooks() throws SQLException;
	public abstract List<BankBook> showBankBooksByAccountNum(BankBook bankbook) throws SQLException;
	public abstract List<BankBook> showBankBooksByCustName(BankBook bankbook) throws SQLException;
	public abstract List<BankBook> showBankBooksByPlanName(BankBook bankbook) throws SQLException;
	public abstract List<BankBook> showBankBooksByDeposit() throws SQLException;
	public abstract List<BankBook> showBankBooksBySaving() throws SQLException;
	public abstract List<BankBook> showBankBooksByMinus() throws SQLException;
	public abstract String showDPTotalAmount() throws SQLException;//예금 총금액
	public abstract String showSvTotalAmount() throws SQLException;//적금 총금액
	public abstract String showLoTotalAmount() throws SQLException;//대출 총금액
	public abstract List<String> showOpenDPMonth() throws SQLException; //월별 예금 건수
	public abstract List<String> showOpenSvMonth() throws SQLException; //월별 적금 건수
	public abstract List<String> showOpenLoMonth() throws SQLException; //월별 대출 건수
	public abstract List<String> showDepositMonth() throws SQLException; //월별 입금 건수
	public abstract List<String> showWithDrawalMonth() throws SQLException; //월별 출금 건수
	public abstract int insertBankBook(BankBook bankbook) throws SQLException;
	public abstract int updateBankBook(BankBook bankbook) throws SQLException;
	public abstract int insertDormantAccountProcedure(BankBook bankbook) throws SQLException;
	public abstract int insertTerminationAccountProcedure(BankBook bankbook) throws SQLException;
	public abstract int deleteBankBook(BankBook bankbook) throws SQLException;
	public abstract int updateBankBalance(Customer customer) throws SQLException;
	public abstract List<AccountInfo> showBankBookInfoDaily(String custname) throws SQLException;
	public abstract List<AccountInfo> showBankBookInfoWeekly(String custname) throws SQLException;
	public abstract List<AccountInfo> showBankBookInfoMonthly(String custname) throws SQLException;
	public abstract List<AccountInfo> showBankBookInfoYearly(String custname) throws SQLException;
	public abstract List<AccountInfo> showBankBookDormantAccountInfo() throws SQLException;
	public abstract List<AccountInfo> showBankBookTerminationAccountInfo() throws SQLException;
	public int insertBankBookPerformance(BankBook bankbook) throws SQLException;
}
