package yi_java3st_2team.ui.service;

import java.util.List;

import yi_java3st_2team.dao.EmployeeDao;
import yi_java3st_2team.dao.impl.EmployeeDaoImpl;
import yi_java3st_2team.dto.Employee;

public class EmployeeUIService {
    private  EmployeeDao empDao;
    
    
    public EmployeeUIService() {
    	empDao = EmployeeDaoImpl.getInstance();
    }
    
    
    public List<Employee> showEmpList(){
    	return empDao.selectEmployeeByAll();
    }
    
    
    
    public void removeEmp(Employee emp) {
    	empDao.deleteEmployee(emp);
    }
    public void addEmp(Employee emp) {
    	empDao.insertEmployee(emp);
    }
    
    public void modifyEmp(Employee emp) {
    	empDao.updateEmployee(emp);
    }
    
    
    
}
