package yi_java3st_2team.ui.service;

import java.sql.SQLException;
import java.util.List;

import yi_java3st_2team.dao.BankBookDao;
import yi_java3st_2team.dao.CustomerDao;
import yi_java3st_2team.dao.PlanDao;
import yi_java3st_2team.dao.impl.BankBookDaoImpl;
import yi_java3st_2team.dao.impl.CustomerDaoImpl;
import yi_java3st_2team.dao.impl.PlanDaoImpl;
import yi_java3st_2team.dto.BankBook;
import yi_java3st_2team.dto.Customer;
import yi_java3st_2team.dto.Plan;

public class BankBookService {
	private BankBookDao bankBookDao;
	private CustomerDao custDao;
	private PlanDao planDao;

	public BankBookService() {
		bankBookDao = BankBookDaoImpl.getInstance();
		custDao = CustomerDaoImpl.getInstance();
		planDao = PlanDaoImpl.getInstance();
	}
	public List<BankBook> showBankBooks() throws SQLException {
		return bankBookDao.showBankBooks();
	}
	public List<BankBook> showBankBookByCustName(BankBook bankbook) throws SQLException {
		return bankBookDao.showBankBooksByCustName(bankbook);
	}
	public int insertBankBook(BankBook bankbook) throws SQLException {
		return bankBookDao.insertBankBook(bankbook);
	}
	public int updateBankBook(BankBook bankbook) throws SQLException {
		return bankBookDao.updateBankBook(bankbook);
	}
	public int deleteBankBook(BankBook bankbook) throws SQLException {
		return bankBookDao.deleteBankBook(bankbook);
	}
	public List<Customer> showCustomers() throws SQLException {
		return custDao.selectCustomerAll();
	}
	public List<Plan> showPlanByBankBook() throws SQLException {
		return planDao.selectPlanByBankBook();
	}
}
