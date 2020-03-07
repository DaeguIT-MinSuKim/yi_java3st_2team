package yi_java3st_2team.dao;

import java.sql.SQLException;
import java.util.List;

import yi_java3st_2team.dto.BankBook;
import yi_java3st_2team.dto.Customer;

public interface BankBookDao {
	public abstract List<BankBook> showBankBooks() throws SQLException;
	public abstract List<BankBook> showBankBooksByCustName(BankBook bankbook) throws SQLException;
	public abstract String showDPTotalAmount() throws SQLException;//예금 총금액
	public abstract String showSvTotalAmount() throws SQLException;//적금 총금액
	public abstract String showLoTotalAmount() throws SQLException;//대출 총금액
	public abstract int insertBankBook(BankBook bankbook) throws SQLException;
	public abstract int updateBankBook(BankBook bankbook) throws SQLException;
	public abstract int deleteBankBook(BankBook bankbook) throws SQLException;
	public abstract int updateBankBalance(Customer customer) throws SQLException;
}
