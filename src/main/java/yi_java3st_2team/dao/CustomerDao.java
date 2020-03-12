package yi_java3st_2team.dao;

import java.sql.SQLException;
import java.util.List;

import yi_java3st_2team.dto.Customer;

public interface CustomerDao {
	
	abstract List<Customer> selectCustomerAll() throws SQLException;
	abstract List<Customer> selectCustomerBalance() throws SQLException;
	abstract Customer selectCustomerByName(String custName) throws SQLException;
	abstract List<Customer> selectCustomerBankInfoByName (String custName) throws SQLException;
	abstract int selectNormalCustNum() throws SQLException; 
	abstract int selectVIPCustNum() throws SQLException;
	abstract int selectBRankCustNum() throws SQLException;
	abstract int selectSRankCustNum() throws SQLException;
	abstract int selectGRankCustNum() throws SQLException;
	abstract int selectPRankCustNum() throws SQLException;
	abstract List<String> selectCustExistChk() throws SQLException;
	abstract void insertCustomer(Customer customer) throws SQLException;
	abstract int updateCustomer (Customer customer) throws SQLException;
	abstract int deleteCustomer (Customer customer) throws SQLException;
	
	
}
