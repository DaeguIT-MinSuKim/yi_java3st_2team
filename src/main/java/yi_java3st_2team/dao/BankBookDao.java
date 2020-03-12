package yi_java3st_2team.dao;

import java.sql.SQLException;
import java.util.List;

import yi_java3st_2team.dto.BankBook;
import yi_java3st_2team.dto.Customer;
import yi_java3st_2team.dto.Info;

public interface BankBookDao {
	public abstract List<BankBook> showBankBooks() throws SQLException;
	public abstract List<BankBook> showBankBooksByCustName(BankBook bankbook) throws SQLException;
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
	public abstract int updateBankBookAccountNum(BankBook bankbook) throws SQLException;
	public abstract int deleteBankBook(BankBook bankbook) throws SQLException;
	public abstract int updateBankBalance(Customer customer) throws SQLException;
	public abstract Info showBankBookInfoDaily(String custname) throws SQLException;
	public abstract Info showBankBookInfoWeekly(String custname) throws SQLException;
	public abstract Info showBankBookInfoMonthly(String custname) throws SQLException;
	public abstract Info showBankBookInfoYearly(String custname) throws SQLException;
}
