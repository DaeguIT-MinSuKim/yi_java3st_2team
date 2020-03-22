package yi_java3st_2team.ui.service;

import java.sql.SQLException;
import java.util.List;

import yi_java3st_2team.dao.CustomerDao;
import yi_java3st_2team.dao.LoanDao;
import yi_java3st_2team.dao.PlanDao;
import yi_java3st_2team.dao.impl.CustomerDaoImpl;
import yi_java3st_2team.dao.impl.LoanDaoImpl;
import yi_java3st_2team.dao.impl.PlanDaoImpl;
import yi_java3st_2team.dto.Customer;
import yi_java3st_2team.dto.Loan;
import yi_java3st_2team.dto.LoanInfo;
import yi_java3st_2team.dto.Plan;

public class LoanService {
	private LoanDao loanDao;
	private CustomerDao custDao;
	private PlanDao planDao;
	public LoanService() {
		loanDao = LoanDaoImpl.getInstance();
		custDao = CustomerDaoImpl.getInstance();
		planDao = PlanDaoImpl.getInstance();
	}
	public List<Loan> showLoans() throws SQLException {
		return loanDao.showLoans();
	}
	public List<Loan> showLoanByCustName(Loan loan) throws SQLException {
		return loanDao.showLoanByCustName(loan);
	}
	public int insertLoan(Loan loan) throws SQLException {
		return loanDao.insertLoan(loan);
	}
	public int updateLoan(Loan loan) throws SQLException {
		return loanDao.updateLoan(loan);
	}
	public int deleteLoan(Loan loan) throws SQLException {
		return loanDao.deleteLoan(loan);
	}
	public List<Customer> showCust() throws SQLException {
		return custDao.selectCustomerAll();
	}
	public List<Plan> showPlanByLoan() throws SQLException {
		return planDao.selectPlanByLoan();
	}
	public List<LoanInfo> showLoanInfo() throws SQLException {
		return loanDao.showLoanInfo();
	}
}
