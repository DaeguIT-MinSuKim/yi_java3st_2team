package yi_java3st_2team.ui.service;

import java.sql.SQLException;

import yi_java3st_2team.dao.EmployeeDao;
import yi_java3st_2team.dao.impl.EmployeeDaoImpl;
import yi_java3st_2team.dto.Employee;

public class EmployeeService {
	private EmployeeDao dao;
	
	public EmployeeService() {
		dao = EmployeeDaoImpl.getInstance();
	}

	public Employee GetEmpAuth(Employee emp) throws SQLException {
		return dao.getEmpAuth(emp);
	}
}
