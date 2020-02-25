package yi_java3st_2team.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import yi_java3st_2team.dao.EmployeeDao;
import yi_java3st_2team.ds.LocalDataSource;
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
		try(Connection con = LocalDataSource.getConnection();
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
		try(Connection con = LocalDataSource.getConnection(); 
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
	public Employee selectEmpByName(String empName) throws SQLException {
		Employee emp = null;
		String sql = "select  empCode, empName, empTitle, empAuth, empSalary, empTel, empId, empPwd, d.deptName, d.deptNo\r\n" + 
				"   from employee e left join department d on e.deptNo = d.deptNo \r\n" + 
				"   where empName=?";
		
		try (Connection con = LocalDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setString(1, empName);
			
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					return getEmployee(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertEmployee(Employee emp) {
		String sql = "insert into employee values(?,?,?,?,?,?,?,password(?),?)";
		try(Connection con = LocalDataSource.getConnection();
				PreparedStatement pstmt= con.prepareStatement(sql)){
			pstmt.setString(1, emp.getEmpCode());
			pstmt.setString(2, emp.getEmpName());
			pstmt.setString(3, emp.getEmpTitle());
			pstmt.setString(4, emp.getEmpAuth());
			pstmt.setInt(5, emp.getEmpSalary());
			pstmt.setString(6, emp.getEmpTel());
			pstmt.setString(7, emp.getEmpId());
			pstmt.setString(8, emp.getEmpPwd());
			pstmt.setInt(9, emp.getDept().getDeptNo());
			
			return pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateEmployee(Employee emp) {
		String sql="update employee set empName=?,empTitle=?,empAuth=?,empSalary=?,empTel=?,empId=?,empPwd=password(?),deptNo=? where empCode=?";
		try(Connection con = LocalDataSource.getConnection();
				PreparedStatement pstmt= con.prepareStatement(sql)){
			
			pstmt.setString(1, emp.getEmpName());
			pstmt.setString(2, emp.getEmpTitle());
			pstmt.setString(3, emp.getEmpAuth());
			pstmt.setInt(4, emp.getEmpSalary());
			pstmt.setString(5, emp.getEmpTel());
			pstmt.setString(6, emp.getEmpId());
			pstmt.setString(7, emp.getEmpPwd());
			pstmt.setInt(8, emp.getDept().getDeptNo());
			pstmt.setString(9, emp.getEmpCode());

			return pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
		
	}

	@Override
	public int deleteEmployee(Employee emp) {
		String sql="delete from employee where empCode=?";
		try(Connection con = LocalDataSource.getConnection();
				PreparedStatement pstmt= con.prepareStatement(sql)){
			pstmt.setString(1, emp.getEmpCode());

			return pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Employee> selectEmployeeByAll() {
		String sql="select  empCode, empName, empTitle, empAuth, empSalary, empTel, empId, empPwd, d.deptName, d.deptNo\r\n" + 
				"   from employee e left join department d on e.deptNo = d.deptNo \r\n" + 
				"   order by empCode";
		try (Connection con = LocalDataSource.getConnection();
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

	@Override
	public List<Department> selectDeptByAll() {
		String sql="select deptNo, deptName from department order by deptNo";
		try (Connection con = LocalDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
						ResultSet rs = pstmt.executeQuery()){
			List<Department> list = new ArrayList<Department>();
			
			while(rs.next()) {
				list.add(getDepartment(rs));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}

	private Department getDepartment(ResultSet rs) throws SQLException {
		int deptNo = rs.getInt("deptNo");
		String deptName =rs.getString("deptName");
		return new Department(deptNo, deptName);
	}

	@Override
	public int updateEmployeeAuth(Employee emp) {  //여기서 emp는 네가지 정보만 가지고 있음 
		String sql="update employee set empName=?,empTitle=?,empAuth=? where empCode=?";
		try(Connection con = LocalDataSource.getConnection();
				PreparedStatement pstmt= con.prepareStatement(sql)){
			
			pstmt.setString(1, emp.getEmpName());
			pstmt.setString(2, emp.getEmpTitle());
			pstmt.setString(3, emp.getEmpAuth());
			pstmt.setString(4, emp.getEmpCode());

			return pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
