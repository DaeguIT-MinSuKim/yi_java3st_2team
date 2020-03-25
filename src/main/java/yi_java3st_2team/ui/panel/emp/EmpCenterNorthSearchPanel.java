package yi_java3st_2team.ui.panel.emp;

import yi_java3st_2team.dto.Employee;
import yi_java3st_2team.ui.absPanel.AbsCenterNorthSearchPanel;

public class EmpCenterNorthSearchPanel extends AbsCenterNorthSearchPanel<Employee> {
    
	
//	public EmpCenterNorthSearchPanel() {
//		setText("사원 이름 입력");
//	}
	
	
	@Override
	protected void tfClear() {
		getTfSearch().setText("");
		
	}


	@Override
	public String[] setSearchList() {
	
				String[] searchList = {"검색구분","사원이름","부서 (인사  or 고객)","사원번호","직급"};
				return searchList; 
	
	}

}
