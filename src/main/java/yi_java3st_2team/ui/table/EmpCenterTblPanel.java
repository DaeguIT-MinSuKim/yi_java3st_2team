package yi_java3st_2team.ui.table;

import javax.swing.SwingConstants;

import yi_java3st_2team.dto.Department;
import yi_java3st_2team.dto.Employee;
import yi_java3st_2team.ui.absPanel.AbsCenterTblPanel;

public class EmpCenterTblPanel extends AbsCenterTblPanel<Employee> {

	private Employee employee;
	//private Department dept; 

	public EmpCenterTblPanel() {
		
	}

	@Override
	protected void setTblWidthAlign() {
		setColumnAlign(SwingConstants.CENTER,0,1,2,3,5,6,7,8 );
		setColumnAlign(SwingConstants.RIGHT, 4);
        setColumnWidth(50,70,70,50,80,110,70,100,70);		
	}

	@Override
	protected String[] getColumns() {
		
		return new String[] {"코드","이름","직책","권한","월급","연락처","아이디","비밀번호","부서"} ;
	}

	@Override
	public Object[] toArray(Employee item) {
		employee = item;
		return new Object[] {
				item.getEmpCode(),
				item.getEmpName(),
				item.getEmpTitle(), 
				item.getEmpAuth(), 
				String.format("%,d", item.getEmpSalary()), 
				item.getEmpTel(), 
				item.getEmpId(), 
				item.getEmpPwd().replace(item.getEmpPwd(), "**********"), 
				//String.format("%s(%s)",item.getDept().getDeptName(),item.getDept().getDeptNo())
		        item.getDept()		
		};
	}

	@Override
	public void updateRow(Employee item, int updateIdx) {
		model.setValueAt(item.getEmpCode(), updateIdx, 0);
		model.setValueAt(item.getEmpName(), updateIdx, 1);
		model.setValueAt(item.getEmpTitle(), updateIdx, 2);
		model.setValueAt(item.getEmpAuth(), updateIdx, 3);
		model.setValueAt(item.getEmpSalary(), updateIdx, 4);
		model.setValueAt(item.getEmpTel(), updateIdx, 5);
		model.setValueAt(item.getEmpId(), updateIdx, 6);
		model.setValueAt(item.getEmpPwd(), updateIdx, 7);
		model.setValueAt(item.getDept().getDeptName(), updateIdx, 8);
	}

	@Override
	public Employee getSelectedItem() {
		int selIdx = getSelectedRowIdx();
		String empCode = (String) model.getValueAt(selIdx, 0);
		String empName = (String) model.getValueAt(selIdx, 1);
		String empTitle = (String) model.getValueAt(selIdx, 2);
		String empAuth = (String) model.getValueAt(selIdx, 3);
		String sSalary = (String)model.getValueAt(selIdx, 4);
		int empSalary = Integer.parseInt(sSalary.replace(",",""));
		String empTel = (String) model.getValueAt(selIdx, 5);
		String empId = (String) model.getValueAt(selIdx, 6);
		String empPwd = (String) model.getValueAt(selIdx, 7);
	    Department dept	= (Department) model.getValueAt(selIdx, 8);
		
//		String sDept= (String)model.getValueAt(selIdx, 8);
//		
//		
//		  if(sDept.equals("인사(1)")) {
//			  dept = new Department(1);
//			  dept.setDeptName("인사");
//		  }if(sDept.contentEquals("고객(2)")) {
//			  dept = new Department(2);
//			  dept.setDeptName("고객");
//		  }
	//	Department dept = new Department(index-2); //)앞의 숫자를 넣는다.. 
	//	System.out.println(dept);
		Employee emp = new Employee(empCode, empName, empTitle, empAuth, empSalary, empTel, empId, empPwd, dept);
		
		return emp;
	}
	
	

}
