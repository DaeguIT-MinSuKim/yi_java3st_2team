package yi_java3st_2team.dao;

import java.sql.SQLException;
import java.util.List;

import yi_java3st_2team.dto.Customer;

public interface CustomerDao {
	
	abstract List<Customer> selectCustomerAll() throws SQLException;
	abstract Customer selectCustomerByName(String custName) throws SQLException;
	abstract void insertCustomer(Customer customer) throws SQLException;
	abstract int updateCustomer (Customer customer) throws SQLException;
	abstract int deleteCustomer (Customer customer) throws SQLException;
}
