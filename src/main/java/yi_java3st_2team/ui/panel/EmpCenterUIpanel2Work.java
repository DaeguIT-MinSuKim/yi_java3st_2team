package yi_java3st_2team.ui.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import yi_java3st_2team.dto.Employee;
import yi_java3st_2team.ui.dialog.DlgEmp;
import yi_java3st_2team.ui.service.EmployeeUIService;
import yi_java3st_2team.ui.table.EmpCenterTblPanel2Work;

public class EmpCenterUIpanel2Work extends JPanel implements ActionListener {
	private EmployeeUIService service;
	private EmpCenterNorthSearchPanel pEmpSerch;
	private EmpCenterTblPanel2Work pEmpTblPanel;
	private DlgEmp dlgEmp;
	
	
	
	public EmpCenterUIpanel2Work() {
		service = new EmployeeUIService();
		initialize();
	}
	private void initialize() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		pEmpSerch = new EmpCenterNorthSearchPanel();
	
		add(pEmpSerch);
		
		pEmpTblPanel = new EmpCenterTblPanel2Work();
	    //리스트불러오기
		//pEmpTblPanel.loadTableData(service.showEmpList());
		
		pEmpTblPanel.loadTableData(service.showEmpPerformance());
		add(pEmpTblPanel);
		
		//테이블 패널에 팝업메뉴달기
		//pEmpTblPanel.setPopupMenu(createPopup());
		pEmpSerch.getBtnSearch().addActionListener(this);
		pEmpSerch.getBtnCancel().addActionListener(this);
		
	
	}
    

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == pEmpSerch.getBtnSearch()) {
			pEmpSerchBtnSearchActionPerformed(e);
		}
		if (e.getSource() == pEmpSerch.getBtnCancel()) {
			pEmpSerchBtnCancelActionPerformed(e);
		}
	}
	protected void pEmpSerchBtnCancelActionPerformed(ActionEvent e) {
		//취소누르면
		pEmpSerch.getTfSearch().setText("");
		pEmpTblPanel.loadTableData(service.showEmpPerformance());
	}
	protected void pEmpSerchBtnSearchActionPerformed(ActionEvent e) {
		//조회누르면
		String empName = pEmpSerch.getTfSearch().getText().trim();
	
		if(pEmpSerch.getTfSearch().getText().contentEquals("")) {
			JOptionPane.showMessageDialog(null, "사원 이름을 입력해주세요");
			return;
		}
		
		List<Employee> list = new ArrayList<Employee>(); 
		
		try {
			list.add(service.showEmpPerformPicked(empName));
			pEmpTblPanel.loadTableData(list);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "다시 검색해주세요");
			e1.printStackTrace();
			return;
		}
	
	}
}
