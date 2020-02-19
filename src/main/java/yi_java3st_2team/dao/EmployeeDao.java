package yi_java3st_2team.dao;

import java.sql.SQLException;

import yi_java3st_2team.dto.Employee;

public interface EmployeeDao {
	abstract Employee getEmpIdPass(Employee emp) throws SQLException;
	abstract Employee getEmpAuth(Employee emp) throws SQLException;
}
