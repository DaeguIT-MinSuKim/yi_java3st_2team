package yi_java3st_2team.ui.panel;

import yi_java3st_2team.dto.Employee;
import yi_java3st_2team.ui.absPanel.AbsCenterNorthSearchPanel;

public class EmpCenterNorthSearchPanel extends AbsCenterNorthSearchPanel<Employee> {
    
	
	public EmpCenterNorthSearchPanel() {
		setText("사원 이름 입력");
	}
	
	
	@Override
	protected void tfClear() {
		getTfSearch().setText("");
		
	}

}
