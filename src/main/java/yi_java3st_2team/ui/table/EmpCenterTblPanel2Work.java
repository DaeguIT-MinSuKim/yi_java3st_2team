package yi_java3st_2team.ui.table;

import javax.swing.SwingConstants;

import yi_java3st_2team.dto.Department;
import yi_java3st_2team.dto.Employee;
import yi_java3st_2team.ui.absPanel.AbsCenterTblPanel;
import javax.swing.border.EmptyBorder;

public class EmpCenterTblPanel2Work extends AbsCenterTblPanel<Employee> {

	private Employee employee;
	private String ppName; 

	public EmpCenterTblPanel2Work() {
		
		initialize();
	}
	private void initialize() {
		getTable().setBorder(null);
		setBorder(new EmptyBorder(10, 50, 30, 50));
	}

	@Override
	protected void setTblWidthAlign() {
		setColumnAlign(SwingConstants.CENTER,0,1,2,3,5,6 );
		setColumnAlign(SwingConstants.RIGHT, 4);
        setColumnWidth(70,70,70,70,100,100,100);		
	}

	@Override
	protected String[] getColumns() {
		
		return new String[] {"사원코드","사원이름","직책","실적","보너스","상품 종류","상품 이름"} ;
	}

	
	//뷰테이블로 다시 만들어야함
	@Override
	public Object[] toArray(Employee item) {
		//System.out.println(item.getpCode());
		
		
		
		return new Object[] {
				item.getEmpCode(),
				item.getEmpName(),
				item.getEmpTitle(), 
				item.getPerf(),
				String.format("%,d", item.getBonus()), 
				item.getpCode()==null?null:getName(item.getpCode()),
				item.getpName()};
	}

	private Object getName(String getpCode) {
		
		//System.out.println(pName);
		ppName = getpCode.substring(0, 2);
		//System.out.println(ppName);
       
		if(ppName.equalsIgnoreCase("AA")) {
			ppName = "예금통장";
		}else if(ppName.equalsIgnoreCase("AB")) {
			ppName = "적금통장";
		}
		else if(ppName.equalsIgnoreCase("AC")) {
			ppName = "마이너스통장";
		}
		else if(ppName.equalsIgnoreCase("BA")) {
			ppName = "체크카드";
		}
		else if(ppName.equalsIgnoreCase("BB")) {
			ppName = "신용카드";
		}
		else if(ppName.equalsIgnoreCase("CA")) {
			ppName = "일반대출";
		}
		else if(ppName.equalsIgnoreCase("CB")) {
			ppName = "신용대출";
		}
		else if(ppName.equalsIgnoreCase("CC")) {
			ppName = "카드론";
		}
		return ppName;
	}
	@Override
	public void updateRow(Employee item, int updateIdx) {
		model.setValueAt(item.getEmpCode(), updateIdx, 0);
		model.setValueAt(item.getEmpName(), updateIdx, 1);
		model.setValueAt(item.getEmpTitle(), updateIdx, 2);
		model.setValueAt(item.getEmpAuth(), updateIdx, 3);
		model.setValueAt(item.getEmpSalary(), updateIdx, 4);
		model.setValueAt(item.getEmpTel(), updateIdx, 5);
		
	}

	@Override
	public Employee getSelectedItem() {
		
		//불필요한 코드 
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
		String sDept= (String)model.getValueAt(selIdx, 8);
		int index = sDept.indexOf(")");
		Department dept = new Department(index-1); //)앞의 숫자를 넣는다.. 
		Employee emp = new Employee(empCode, empName, empTitle, empAuth, empSalary, empTel, empId, empPwd, dept);
		
		return emp;
	}
	
	

}
