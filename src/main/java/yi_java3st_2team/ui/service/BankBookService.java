package yi_java3st_2team.ui.service;

import java.sql.SQLException;
import java.util.List;

import yi_java3st_2team.dao.BankBookDao;
import yi_java3st_2team.dao.CustomerDao;
import yi_java3st_2team.dao.PlanDao;
import yi_java3st_2team.dao.impl.BankBookDaoImpl;
import yi_java3st_2team.dao.impl.CustomerDaoImpl;
import yi_java3st_2team.dao.impl.PlanDaoImpl;
import yi_java3st_2team.dto.AccountInfo;
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
	public int insertDormantAccountProcedure(BankBook bankbook) throws SQLException {
		return bankBookDao.insertDormantAccountProcedure(bankbook);
	}
	public int insertTerminationAccountProcedure(BankBook bankbook) throws SQLException {
		return bankBookDao.insertTerminationAccountProcedure(bankbook);
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
	public List<Plan> showPlanByBankBookNormal() throws SQLException {
		return planDao.selectPlanByBankBookNormal();
	}
	
	public int updateBankBalance(Customer customer) throws SQLException {
		return bankBookDao.updateBankBalance(customer);
	}
	
	public String showDpBalance () throws SQLException{
		return bankBookDao.showDPTotalAmount();
	}
	
	public String showSvBalance () throws SQLException{
		return bankBookDao.showSvTotalAmount();
	}
	
	public String showLoBalance () throws SQLException{
		return bankBookDao.showLoTotalAmount();
	}
	
	public List<String> showOpenDPMonth() throws SQLException{
		return bankBookDao.showOpenDPMonth();
	}
	public List<String> showOpenSvMonth() throws SQLException{
		return bankBookDao.showOpenSvMonth();
	}
	public List<String> showOpenLoMonth() throws SQLException{
		return bankBookDao.showOpenLoMonth();
	}
	
	public List<String> showDepositMonth() throws SQLException{
		return bankBookDao.showDepositMonth();
	}
	
	public List<String> showWithdrawalMonth() throws SQLException{
		return bankBookDao.showWithDrawalMonth();
	}
	public List<AccountInfo> bankBookInfoDaily(String custname) throws SQLException {
		return bankBookDao.showBankBookInfoDaily(custname);
	}
	public List<AccountInfo> bankBookInfoWeekly(String custname) throws SQLException {
		return bankBookDao.showBankBookInfoWeekly(custname);
	}
	public List<AccountInfo> bankBookInfoMonthly(String custname) throws SQLException {
		return bankBookDao.showBankBookInfoMonthly(custname);
	}
	public List<AccountInfo> bankBookInfoYearly(String custname) throws SQLException {
		return bankBookDao.showBankBookInfoYearly(custname);
	}
	public List<AccountInfo> showDormantAccountInfo() throws SQLException {
		return bankBookDao.showBankBookDormantAccountInfo();
	}
	public List<AccountInfo> showTerminationAccountInfo() throws SQLException {
		return bankBookDao.showBankBookTerminationAccountInfo();
	}
	public List<BankBook> showBankBookByAccoutNum(BankBook bankbook) throws SQLException {
		return bankBookDao.showBankBooksByAccountNum(bankbook);
	}
	public List<BankBook> showBankBookByPlanName(BankBook bankbook) throws SQLException {
		return bankBookDao.showBankBooksByPlanName(bankbook);
	}
	public List<BankBook> showBankBookByDeposit() throws SQLException {
		return bankBookDao.showBankBooksByDeposit();
	}
	public List<BankBook> showBankBookBySaving() throws SQLException {
		return bankBookDao.showBankBooksBySaving();
	}
	public List<BankBook> showBankBookByMinus() throws SQLException {
		return bankBookDao.showBankBooksByMinus();
	}
	public int updateCardBalance(Customer customer) throws SQLException {
		return bankBookDao.updateCardBalance(customer);
	}
}
