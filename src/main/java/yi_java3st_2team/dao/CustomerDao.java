package yi_java3st_2team.dao;

import java.sql.SQLException;
import java.util.List;

import yi_java3st_2team.dto.Customer;

public interface CustomerDao {
	abstract List<Customer> selectCustomerAll() throws SQLException;
}
