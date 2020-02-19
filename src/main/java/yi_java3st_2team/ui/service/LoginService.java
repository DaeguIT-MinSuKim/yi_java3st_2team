package yi_java3st_2team.ui.service;

import java.sql.SQLException;

import yi_java3st_2team.dao.EmployeeDao;
import yi_java3st_2team.dao.impl.EmployeeDaoImpl;
import yi_java3st_2team.dto.Employee;

public class LoginService {
	private EmployeeDao dao;
	
	public LoginService() {
		dao = EmployeeDaoImpl.getInstance();
	}

	public Employee GetLoginInfo(Employee emp) throws SQLException {
		return dao.getEmpIdPass(emp);
	}
}
