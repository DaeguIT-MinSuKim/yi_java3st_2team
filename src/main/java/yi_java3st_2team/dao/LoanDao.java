package yi_java3st_2team.dao;

import java.sql.SQLException;
import java.util.List;

import yi_java3st_2team.dto.Loan;

public interface LoanDao {
	public List<Loan> showLoans() throws SQLException;
	public List<Loan> showLoanByCustName(Loan loan) throws SQLException;
	public int insertLoan(Loan loan) throws SQLException;
	public int updateLoan(Loan loan) throws SQLException;
	public int deleteLoan(Loan loan) throws SQLException;
}
