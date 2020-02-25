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
    
    
    
}
