package yi_java3st_2team.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import yi_java3st_2team.dao.EmployeeDao;
import yi_java3st_2team.ds.LocalDataSource;
import yi_java3st_2team.ds.MySqlDataSource;
import yi_java3st_2team.dto.Department;
import yi_java3st_2team.dto.Employee;
import yi_java3st_2team.ui.panel.EmpCenterUIpanel;

public class EmployeeDaoImpl implements EmployeeDao {
	
	private EmpCenterUIpanel empPanel;
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
		String sql = "select empauth, empTitle from employee where empname = ?";
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
		emp.setEmpTitle(rs.getString("empTitle"));
		return emp;
	}

	@Override
	public List<Employee> selectEmpByNameList(String empName) throws SQLException {
		//Employee emp = null;
		List<Employee> list = new ArrayList<Employee>();
		String sql = "select  empCode, empName, empTitle, empAuth, empSalary, empTel, empId, empPwd, d.deptName, d.deptNo\r\n" + 
				"   from employee e left join department d on e.deptNo = d.deptNo \r\n" + 
				"   where empName like ?";
		
		try (Connection con = LocalDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setString(1, "%"+empName+"%");
			
			try(ResultSet rs = pstmt.executeQuery();){
				while(rs.next()) {
					list.add(getEmployee(rs));
					
					//return getEmployee(rs);
				}
				return list;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertEmployee(Employee emp) {
		String sql = "insert into employee values(?,?,?,?,?,?,?,password(?),?,?)";
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
			if (emp.getPic()!=null) {
				pstmt.setBytes(10, emp.getPic());
			}
			
			return pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();

			if(e.getMessage().contains("PRIMARY")) {
				JOptionPane.showMessageDialog(null, emp.getEmpCode()+ "사원번호 중복입니다");
				empPanel.getDlgEmp().setVisible(true);
				
				return 0;
			}
		}
		
		return 0;
	}

	@Override
	public int updateEmployee(Employee emp) {
		String sql="update employee set empName=?,empTitle=?,empAuth=?,empSalary=?,empTel=?,empId=?,empPwd=password(?),deptNo=?,pic=? where empCode=?";
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
			pstmt.setBytes(9, emp.getPic());
			pstmt.setString(10, emp.getEmpCode());

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
	private Employee getEmployeePic(ResultSet rs) throws SQLException {
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
		Employee emp = new Employee(empCode, empName, empTitle, empAuth, empSalary, empTel, empId, empPwd, dept);
		byte[] pic = rs.getBytes("pic")==null?null:rs.getBytes("pic");
		if(pic==null) {
			return emp;
		}
		else {
			emp.setPic(pic);
			return emp;
		}
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

	@Override
	public List<Employee> selectEmployeeByPerform() {
		String sql="select e.empCode, e.empName, e.empTitle, count(if(p.custCode=null,0,p.custCode)) as perf , if(count(if(p.custCode=null,0,p.custCode))>=10,e.`empSalary`*0.1,0) as bonus, if(p.`planCode`='A001',vip,null) as vip\r\n" + 
				"from employee e left join performance p on e.`empCode` = p.`empCode`  left join customer c on p.`custCode`=c.`custCode` left join viptable v on p.`custCode`= v.vip\r\n" + 
				"group by e.`empCode`";
		try(Connection con = LocalDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			
			List<Employee> list = new ArrayList<Employee>();
			while(rs.next()) {
				list.add(getEmpPerform(rs));
				
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private Employee getEmpPerform(ResultSet rs) throws SQLException {
		String empCode = rs.getString("e.empCode");
		String empName = rs.getString("e.empName");
		String empTitle = rs.getString("e.empTitle");
		int perf = rs.getInt("perf");
		int bonus = rs.getInt("bonus");
		String vip = rs.getString("vip");
		return new Employee(empCode, empName, empTitle, perf, bonus, vip);
	}

	@Override
	public Employee selectEmpByName(String empName) throws SQLException {
		       Employee emp = null;
				String sql = "select  empCode, empName, empTitle, empAuth, empSalary, empTel, empId, empPwd, d.deptName, d.deptNo\r\n" + 
						"   from employee e left join department d on e.deptNo = d.deptNo \r\n" + 
						"   where empName=?";
				
				try (Connection con = LocalDataSource.getConnection();
						PreparedStatement pstmt = con.prepareStatement(sql)){
					
					pstmt.setString(1,empName);
					
					try(ResultSet rs = pstmt.executeQuery();){
						if(rs.next()) {
							//list.add(getEmployee(rs));
							
							return getEmployee(rs);
						}
						
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return null;
	}

	@Override
	public Employee selectOneEmployeeByPerform(String empName) throws SQLException {
		String sql="select e.empCode, e.empName, e.empTitle, count(if(p.custCode=null,0,p.custCode)) as perf , if(count(if(p.custCode=null,0,p.custCode))>=10,e.`empSalary`*0.1,0) as bonus, if(p.`planCode`='A001',vip,null) as vip\r\n" + 
				"from employee e left join performance p on e.`empCode` = p.`empCode`  left join customer c on p.`custCode`=c.`custCode` left join viptable v on p.`custCode`= v.vip\r\n" + 
				"where e.empName=? group by e.`empCode`";
		try(Connection con = LocalDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, empName);
			//List<Employee> list = new ArrayList<Employee>();
			try(ResultSet rs = pstmt.executeQuery();){
			
			if(rs.next()) {
				//list.add(getEmpPerform(rs));
			   return getEmpPerform(rs);
				
			  }
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// -----------------------------------------------------------------------
	//통계 
	@Override
	public int selectCountAllEmployee() {
	   String sql="select count(empCode) from employee";

	   try(Connection con = LocalDataSource.getConnection();
		   PreparedStatement pstmt = con.prepareStatement(sql);){
		  
		   try(ResultSet rs = pstmt.executeQuery();){
			   
			   while(rs.next()) {
			      return rs.getInt("count(empCode)");

			   }
			  
		   }	   	   
	   } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		// TODO Auto-generated method stub
		return 0;
	}

	//부서 번호를 매개변수로 넣고 부서별 인원 수 구하기
	@Override
	public int selectCountMemberByDept(int dept) {
		String sql= "select count(*) from employee e  where `deptNo` =?";
		
		try(Connection con = LocalDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
			
			pstmt.setInt(1, dept);
			
			try(ResultSet rs = pstmt.executeQuery();){
				while(rs.next()) {
					return rs.getInt("count(*)");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int selectCountMemberByTitle(String empTitle) {
		String sql="select  count(empCode) from employee\r\n" + 
				"where `empTitle` =?";
		try(Connection con = LocalDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
			
			pstmt.setString(1, empTitle);
			
			try(ResultSet rs = pstmt.executeQuery();){
				while(rs.next()) {
					return rs.getInt("count(empCode)");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int selectAvgOfSalary() {
		String sql="select (sum(empSalary))/(count(*)) as 'avgSal' from employee";
		
		try(Connection con = LocalDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				){
			
			while(rs.next()) {
				return rs.getInt("avgSal");
			}
				
		     
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int selectTotalSalary() {
		String sql="select sum(empSalary) as 'totalSal' \r\n" + 
				"   from employee e";
		
		try(Connection con = LocalDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			
			while(rs.next()) {
				return rs.getInt("totalSal");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return 0;
	}

	
	// 부서로 검색하기 
	@Override
	public List<Employee> selectEmpByDept(String empItem) throws SQLException {
		 Employee emp = null;
		 List<Employee> list = new ArrayList<Employee>();
			String sql = "select  empCode, empName, empTitle, empAuth, empSalary, empTel, empId, empPwd, d.deptName, d.deptNo\r\n" + 
					"   from employee e left join department d on e.deptNo = d.deptNo \r\n" + 
					"   where d.deptName=?";
			
			try (Connection con = LocalDataSource.getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql)){
				
				pstmt.setString(1,empItem);
				
				try(ResultSet rs = pstmt.executeQuery();){
					while(rs.next()) {
						list.add(getEmployee(rs));
						
						
						//return getEmployee(rs);
					}
					return list;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
	}

	@Override
	public List<Employee> selectEmpByNo(String empItem) throws SQLException {
		Employee emp = null;
		 List<Employee> list = new ArrayList<Employee>();
			String sql = "select  empCode, empName, empTitle, empAuth, empSalary, empTel, empId, empPwd, d.deptName, d.deptNo\r\n" + 
					"   from employee e left join department d on e.deptNo = d.deptNo \r\n" + 
					"   where empCode=?";
			
			try (Connection con = LocalDataSource.getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql)){
				
				pstmt.setString(1,empItem);
				
				try(ResultSet rs = pstmt.executeQuery();){
					while(rs.next()) {
						list.add(getEmployee(rs));
						
						
						//return getEmployee(rs);
					}
					return list;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
	}

	@Override
	public List<Employee> selectEmpByTitle(String empItem) throws SQLException {
		Employee emp = null;
		 List<Employee> list = new ArrayList<Employee>();
			String sql = "select  empCode, empName, empTitle, empAuth, empSalary, empTel, empId, empPwd, d.deptName, d.deptNo\r\n" + 
					"   from employee e left join department d on e.deptNo = d.deptNo \r\n" + 
					"   where empTitle=?";
			
			try (Connection con = LocalDataSource.getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql)){
				
				pstmt.setString(1,empItem);
				
				try(ResultSet rs = pstmt.executeQuery();){
					while(rs.next()) {
						list.add(getEmployee(rs));
						
						
						//return getEmployee(rs);
					}
					return list;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
	}

	// 실적테이블 검색조건에 따라 구현되는 서비스 
	@Override
	public List<Employee> selectEmpByNameListForPerform(String empItem) throws SQLException {
		List<Employee> list = new ArrayList<Employee>();
		String sql="select e.empCode, e.empName, e.empTitle, count(if(p.custCode=null,0,p.custCode)) as perf , if(count(if(p.custCode=null,0,p.custCode))>=10,e.`empSalary`*0.1,0) as bonus, if(p.`planCode`='A001',vip,null) as vip\r\n" + 
				"from employee e left join performance p on e.`empCode` = p.`empCode`  left join customer c on p.`custCode`=c.`custCode` left join viptable v on p.`custCode`= v.vip\r\n" + 
				"where e.empName like ? group by e.`empCode`";
		try(Connection con = LocalDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, "%"+empItem+"%");
			//List<Employee> list = new ArrayList<Employee>();
			try(ResultSet rs = pstmt.executeQuery();){
			
			while(rs.next()) {
				list.add(getEmpPerform(rs));
			   //return getEmpPerform(rs);
				
			  }
			 return list;
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Employee> selectEmpByDeptForPerform(String empItem) throws SQLException {
		List<Employee> list = new ArrayList<Employee>();
		String sql="select e.empCode, e.empName, e.empTitle, count(if(p.custCode=null,0,p.custCode)) as perf , if(count(if(p.custCode=null,0,p.custCode))>=10,e.`empSalary`*0.1,0) as bonus, if(p.`planCode`='A001',vip,null) as vip, d.deptName \r\n" + 
				"		from employee e left join performance p on e.`empCode` = p.`empCode`  left join customer c on p.`custCode`=c.`custCode` left join viptable v on p.`custCode`= v.vip left join department d on e.`deptNo` = d.`deptNo` \r\n" + 
				"				where d.deptName =? group by e.`empCode`";
		try(Connection con = LocalDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, empItem);
			//List<Employee> list = new ArrayList<Employee>();
			try(ResultSet rs = pstmt.executeQuery();){
			
			while(rs.next()) {
				list.add(getEmpPerform(rs));
			   //return getEmpPerform(rs);
				
			  }
			 return list;
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Employee> selectEmpByNoForPerform(String empItem) throws SQLException {
		List<Employee> list = new ArrayList<Employee>();
		String sql="select e.empCode, e.empName, e.empTitle, count(if(p.custCode=null,0,p.custCode)) as perf , if(count(if(p.custCode=null,0,p.custCode))>=10,e.`empSalary`*0.1,0) as bonus, if(p.`planCode`='A001',vip,null) as vip\r\n" + 
				"from employee e left join performance p on e.`empCode` = p.`empCode`  left join customer c on p.`custCode`=c.`custCode` left join viptable v on p.`custCode`= v.vip\r\n" + 
				"where e.empCode = ? group by e.`empCode`";
		try(Connection con = LocalDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, empItem);
			//List<Employee> list = new ArrayList<Employee>();
			try(ResultSet rs = pstmt.executeQuery();){
			
			while(rs.next()) {
				list.add(getEmpPerform(rs));
			   //return getEmpPerform(rs);
				
			  }
			 return list;
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Employee> selectEmpByTitleForPerform(String empItem) throws SQLException {
		List<Employee> list = new ArrayList<Employee>();
		String sql="select e.empCode, e.empName, e.empTitle, count(if(p.custCode=null,0,p.custCode)) as perf , if(count(if(p.custCode=null,0,p.custCode))>=10,e.`empSalary`*0.1,0) as bonus, if(p.`planCode`='A001',vip,null) as vip\r\n" + 
				"from employee e left join performance p on e.`empCode` = p.`empCode`  left join customer c on p.`custCode`=c.`custCode` left join viptable v on p.`custCode`= v.vip\r\n" + 
				"where e.empTitle = ? group by e.`empCode`";
		try(Connection con = LocalDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, empItem);
			//List<Employee> list = new ArrayList<Employee>();
			try(ResultSet rs = pstmt.executeQuery();){
			
			while(rs.next()) {
				list.add(getEmpPerform(rs));
			   //return getEmpPerform(rs);
				
			  }
			 return list;
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Employee selectEmpByCode(String empCode) throws SQLException {
		Employee emp = null;
		String sql = "select empCode, empName, empTitle, empAuth, empSalary, empTel, empId, empPwd, d.deptName, d.deptNo, pic from employee e left join department d on e.deptNo = d.deptNo where empCode=?";
		
		try (Connection con = LocalDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setString(1,empCode);
			
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					//list.add(getEmployee(rs));
					
					emp = getEmployeePic(rs);
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emp;
	}
	


	
	
	
	
}
