package yi_java3st_2team.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import yi_java3st_2team.dao.EmployeeDao;
import yi_java3st_2team.ds.MySqlDataSource;
import yi_java3st_2team.dto.Department;
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

	@Override
	public Employee selectEmpByName(Employee emp) {
		return null;
	}

	@Override
	public int insertEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Employee> selectEmployeeByAll() {
		String sql="select  empCode, empName, empTitle, empAuth, empSalary, empTel, empId, empPwd, d.deptName, d.deptNo\r\n" + 
				"   from employee e left join department d on e.deptNo = d.deptNo \r\n" + 
				"   order by empCode";
		try (Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
						ResultSet rs = pstmt.executeQuery()){
			List<Employee> list = new ArrayList<Employee>();
			
			while(rs.next()) {
				list.add(getEmployee(rs));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}

	private Employee getEmployee(ResultSet rs) throws SQLException {
		String empCode = rs.getString("empCode");
		String empName = rs.getString("empName");
		String empTitle = rs.getString("empTitle");
		String empAuth = rs.getString("empAuth");
		int empSalary = rs.getInt("empSalary");
		String empTel = rs.getString("empTel");
		String empId = rs.getString("empId");
		String empPwd =rs.getString("empPwd");
		Department dept= new Department(rs.getInt("d.deptNo")); //이부분 확인해보기
	    dept.setDeptName(rs.getString("d.deptName"));
		return new Employee(empCode, empName, empTitle, empAuth, empSalary, empTel, empId, empPwd, dept);
	}
}
