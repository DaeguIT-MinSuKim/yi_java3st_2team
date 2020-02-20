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
	
	
}
