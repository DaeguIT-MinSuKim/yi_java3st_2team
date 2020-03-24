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
	
	
	private Object selectedOne;
	
	
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
    

	public EmpCenterTblPanel2Work getpEmpTblPanel() {
		return pEmpTblPanel;
	}
	public void setpEmpTblPanel(EmpCenterTblPanel2Work pEmpTblPanel) {
		this.pEmpTblPanel = pEmpTblPanel;
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
		//콤보박스의 값 가져오기 
		selectedOne = pEmpSerch.getCmbSearchList().getSelectedItem();
		// System.out.println(selectedOne); // 부서 사원번호등 목록으로 불러와짐
				
		//서치 패널에 입력하는 값을 가지고 오기
		String empItem = pEmpSerch.getTfSearch().getText().trim();
	
		if(pEmpSerch.getTfSearch().getText().contentEquals("")) {
			JOptionPane.showMessageDialog(null, "검색할 값을 입력해주세요");
			return;
		}
		
		List<Employee> list = new ArrayList<Employee>(); 
		
		try {
			if(selectedOne.equals("(검색할 조건을 선택해 주세요)")) {
				JOptionPane.showMessageDialog(null, "검색할 조건을 선택해주세요");
				return;
			}
			if(selectedOne.equals("사원이름")) {
			  //list.add(service.showPickedEmp(empItem));
			  list = service.showPickedEmpListForPerform(empItem);
		    }else if(selectedOne.equals("부서 (인사  or 고객)")) {
		      list = service.showPickedEmpByDeptForPerform(empItem);
		    }else if(selectedOne.equals("사원번호")) {
		      list = service.showPickedEmpByEmpNoForPerform(empItem);
		    }else if(selectedOne.equals("직급")) {
		      list = service.showPickedEmpByTitleForPerform(empItem);
		    }
			
			
			pEmpTblPanel.loadTableData(list);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "다시 검색해주세요");
			e1.printStackTrace();
			return;
		}
	
	}
}
