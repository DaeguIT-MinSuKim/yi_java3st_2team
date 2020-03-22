package yi_java3st_2team.ui.table;

import javax.swing.SwingConstants;

import yi_java3st_2team.dto.Department;
import yi_java3st_2team.dto.Employee;
import yi_java3st_2team.ui.absPanel.AbsCenterTblPanel;
import javax.swing.border.EmptyBorder;

public class EmpCenterTblPanelAuth extends AbsCenterTblPanel<Employee> {

	private Employee employee; 

	public EmpCenterTblPanelAuth() {
		
		initialize();
	}
	private void initialize() {
		getTable().setBorder(null);
		setBorder(new EmptyBorder(10, 50, 30, 50));
	}

	@Override
	protected void setTblWidthAlign() {
		setColumnAlign(SwingConstants.CENTER,0,1,2,3,4 );

        setColumnWidth(100,100,100,100,100);		
	}

	@Override
	protected String[] getColumns() {
		
		return new String[] {"사원코드","사원이름","직책","부서","권한"} ;
	}

	
	//뷰테이블로 다시 만들어야함
	@Override
	public Object[] toArray(Employee item) {
		employee = item;
		return new Object[] {
				item.getEmpCode(),
				item.getEmpName(),
				item.getEmpTitle(), 
				item.getDept().getDeptName(),
				item.getEmpAuth()}; 
//				String.format("%,d", item.getEmpSalary()), 
//				item.getEmpTel(), 
//				item.getEmpId(), 
//				item.getEmpPwd().replace(item.getEmpPwd(), "**********"), 
//				String.format("%s(%s)",item.getDept().getDeptName(),item.getDept().getDeptNo())};
	}

	@Override
	public void updateRow(Employee item, int updateIdx) {
		model.setValueAt(item.getEmpCode(), updateIdx, 0);
		model.setValueAt(item.getEmpName(), updateIdx, 1);
		model.setValueAt(item.getEmpTitle(), updateIdx, 2);
		model.setValueAt(item.getDept().getDeptName(), updateIdx,3);
		model.setValueAt(item.getEmpAuth(), updateIdx, 4);
//		model.setValueAt(item.getEmpSalary(), updateIdx, 4);
//		model.setValueAt(item.getEmpTel(), updateIdx, 5);
//		model.setValueAt(item.getEmpId(), updateIdx, 6);
//		model.setValueAt(item.getEmpPwd(), updateIdx, 7);
//		model.setValueAt(item.getDept().getDeptName(), updateIdx, 8);
	}

	@Override
	public Employee getSelectedItem() {
		int selIdx = getSelectedRowIdx();
		String empCode = (String) model.getValueAt(selIdx, 0);
		String empName = (String) model.getValueAt(selIdx, 1);
		String empTitle = (String) model.getValueAt(selIdx, 2);
		
		Department dept = new Department();
		dept.setDeptName((String) model.getValueAt(selIdx, 3));
	
		String empAuth = (String) model.getValueAt(selIdx, 4);
		
//		String sSalary = (String)model.getValueAt(selIdx, 4);
//		int empSalary = Integer.parseInt(sSalary.replace(",",""));
//		String empTel = (String) model.getValueAt(selIdx, 5);
//		String empId = (String) model.getValueAt(selIdx, 6);
//		String empPwd = (String) model.getValueAt(selIdx, 7);
//		String sDept= (String)model.getValueAt(selIdx, 8);
//		int index = sDept.indexOf(")");
//		Department dept = new Department(index-1); //)앞의 숫자를 넣는다.. 
		Employee emp = new Employee(empCode, empName, empTitle, empAuth, dept);
		
		return emp;
	}
	
	

}
