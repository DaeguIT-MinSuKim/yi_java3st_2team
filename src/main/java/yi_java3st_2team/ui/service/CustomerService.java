package yi_java3st_2team.ui.service;

import java.sql.SQLException;
import java.util.List;

import yi_java3st_2team.dao.CustomerDao;
import yi_java3st_2team.dao.impl.CustomerDaoImpl;
import yi_java3st_2team.dto.Customer;

public class CustomerService {
	private CustomerDao dao;

	public CustomerService() {
		dao = CustomerDaoImpl.getInstance();
	}
	
	public List<Customer> showCustomers() throws SQLException{
		return dao.selectCustomerAll();
	}
	
	public List<Customer> showCustomerByName(String custName) throws SQLException{
		return dao.selectCustomerByName(custName);
	}
	
	public Customer showCustomerByCode(String custCode) throws SQLException{
		return dao.selectCustomerByCode(custCode);
	}
	
	public Customer showCustomerByTel(String custTel) throws SQLException{
		return dao.selectCustomerByTel(custTel);
	}
	
	public List<Customer> showCustomersByBalance() throws SQLException{
		return dao.selectCustomerBalance();
	}
	
	public List<Customer> showCustomerBankInfoByName(String custName) throws SQLException{
		return dao.selectCustomerBankInfoByName(custName);
	}
	
	public List<Customer> showCustomerBankInfoByAcc(String accountNum) throws SQLException{
		return dao.selectCustomerBankInfoByAcc(accountNum);
	}
	
	public int showNormalCustNum () throws SQLException{
		return dao.selectNormalCustNum();
	}
	
	public int showVIPCustNum() throws SQLException{
		return dao.selectVIPCustNum();
	}
	
	public int showBRankCustNum() throws SQLException{
		return dao.selectBRankCustNum();
	}
	
	public int showSRankCustNum() throws SQLException{
		return dao.selectSRankCustNum();
	}
	
	public int showGRankCustNum() throws SQLException{
		return dao.selectGRankCustNum();
	}
	public int showPRankCustNum() throws SQLException{
		return dao.selectPRankCustNum();
	}
	
	public List<String> custExistChk() throws SQLException{
		return dao.selectCustExistChk();
	}
	
	public void AddCustomer(Customer customer) throws SQLException{
		 dao.insertCustomer(customer);
	}
	
	public int editCustomer(Customer customer) throws SQLException{
		return dao.updateCustomer(customer);
	}
	
	public int removeCustomer(Customer customer) throws SQLException{
		return dao.deleteCustomer(customer);
	}
	
}
