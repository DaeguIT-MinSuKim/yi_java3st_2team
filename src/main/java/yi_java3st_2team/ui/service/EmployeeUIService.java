package yi_java3st_2team.ui.service;

import java.sql.SQLException;
import java.util.List;

import yi_java3st_2team.dao.EmployeeDao;
import yi_java3st_2team.dao.impl.EmployeeDaoImpl;
import yi_java3st_2team.dto.Department;
import yi_java3st_2team.dto.Employee;

public class EmployeeUIService {
    private  EmployeeDao empDao;
    
    
    public EmployeeUIService() {
    	empDao = EmployeeDaoImpl.getInstance();
    }
    
    
    public List<Employee> showEmpList(){
    	return empDao.selectEmployeeByAll();
    }
    
    //사원 이름으로 검색
    public List<Employee> showPickedEmpList(String empName) throws SQLException{
    	return empDao.selectEmpByNameList(empName);
    }
    public Employee showPickedEmp(String empName) throws SQLException{
    	return empDao.selectEmpByName(empName);
    }
    
    public List<Department> showDeptList(){
    	return empDao.selectDeptByAll();
    }
    
    
    public void removeEmp(Employee emp) {
    	empDao.deleteEmployee(emp);
    }
    
    //insert하는 부분 
    public void addEmp(Employee emp) {
    	empDao.insertEmployee(emp);
    }
    
    public void modifyEmp(Employee emp) {
    	empDao.updateEmployee(emp);
    }
    
    public void modifyEmpAuth(Employee emp) {
    	empDao.updateEmployeeAuth(emp);
    }
    
    //사원 실적까지 포함
    public List<Employee> showEmpPerformance(){
    	return empDao.selectEmployeeByPerform();
    }
    
    public Employee showEmpPerformPicked(String empName) throws SQLException {
    	return empDao.selectOneEmployeeByPerform(empName);
    }
    
    
    //전체 사원 수 구하기
    public int countAllEmpNum() {
    	return empDao.selectCountAllEmployee();
    	
    }
    //부서별 사원 수 구하기
    // 인사부 - 1번  고객팀 2번
    public int countMemberByDepartment(int deptNo) {
    	return empDao.selectCountMemberByDept(deptNo);
    }
    
    //타이틀마다 사원 수 구하기{  
    public int countMemberByTitle(String empTitle) {
    	return empDao.selectCountMemberByTitle(empTitle);
    }
    
    //1인 평균 월급
    public int avgOfSalary() {
    	return empDao.selectAvgOfSalary();   	
    }
    
 
    
}
