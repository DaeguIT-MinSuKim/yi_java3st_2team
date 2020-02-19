package yi_java3st_2team.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import yi_java3st_2team.dao.EmployeeDao;
import yi_java3st_2team.ds.MySqlDataSource;
import yi_java3st_2team.dto.Employee;

public class EmployeeDaoImpl implements EmployeeDao {
	private static final EmployeeDaoImpl instance = new EmployeeDaoImpl();
	private EmployeeDaoImpl() {};
	
	public static EmployeeDaoImpl getInstance() {
		return instance;
	}

	@Override
	public Employee getEmpIdPass(Employee emp) throws SQLException {
		String sql = "select empname from employee where empid=? and emppwd=password(?)";
		Employee employee = new Employee();
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, emp.getEmpId());
			pstmt.setString(2, emp.getEmpPwd());
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					employee = getEmployeeByLogin(rs);
				}
			}
		}
		return employee;
	}

	private Employee getEmployeeByLogin(ResultSet rs) throws SQLException {
		Employee emp = new Employee();
		emp.setEmpName(rs.getString("empname"));
		return emp;
	}

	@Override
	public Employee getEmpAuth(Employee emp) throws SQLException {
		String sql = "select empauth from employee where empname = ?";
		Employee employee = new Employee();
		try(Connection con = MySqlDataSource.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, emp.getEmpName());
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					employee = getEmployeeAuth(rs);
				}
			}
		}
		return employee;
	}

	private Employee getEmployeeAuth(ResultSet rs) throws SQLException {
		Employee emp = new Employee();
		emp.setEmpAuth(rs.getString("empauth"));
		return emp;
	}
}
