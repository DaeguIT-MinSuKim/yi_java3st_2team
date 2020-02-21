package yi_java3st_2team.dao;

import java.sql.SQLException;
import java.util.List;

import yi_java3st_2team.dto.Employee;

public interface EmployeeDao {
	
	
	
	abstract Employee getEmpIdPass(Employee emp) throws SQLException;
	abstract Employee getEmpAuth(Employee emp) throws SQLException;
	
    //이름으로 검색
	Employee selectEmpByName(Employee emp);
    //전체 사원 리스트
	List<Employee> selectEmployeeByAll();
	int insertEmployee(Employee emp);
	int updateEmployee(Employee emp);
	int deleteEmployee(Employee emp);
}
