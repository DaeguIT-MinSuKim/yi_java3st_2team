package yi_java3st_2team.ui.panel.emp;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import yi_java3st_2team.dto.Department;
import yi_java3st_2team.dto.Employee;
import yi_java3st_2team.ui.dialog.DlgEmpAuth;
import yi_java3st_2team.ui.service.EmployeeUIService;
import yi_java3st_2team.ui.table.EmpCenterTblPanel2Work;
import yi_java3st_2team.ui.table.EmpCenterTblPanelAuth;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class EmpCenterUIpanelAuth extends JPanel implements ActionListener {
	private EmployeeUIService service;
	private EmpCenterNorthSearchPanel pEmpSerch;
	private EmpCenterTblPanelAuth pEmpTblPanel;
	private DlgEmpAuth dlgEmpAuth;
	
	private Object selectedOne;
	
	private Employee emp111;
	
	public EmpCenterUIpanelAuth() {
		service = new EmployeeUIService();
		initialize();
	}
	private void initialize() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		pEmpSerch = new EmpCenterNorthSearchPanel();
	
		add(pEmpSerch);
		
		pEmpTblPanel = new EmpCenterTblPanelAuth();
	    //리스트불러오기
		pEmpTblPanel.loadTableData(service.showEmpList());
		add(pEmpTblPanel);
		
		//테이블 패널에 팝업메뉴달기
		pEmpTblPanel.setPopupMenu(createPopup());
		pEmpSerch.getBtnSearch().addActionListener(this);
		pEmpSerch.getBtnCancel().addActionListener(this);
		
	
	}
    
	private JPopupMenu createPopup() {
		JPopupMenu popup = new JPopupMenu();
		

		JMenuItem editMenu = new JMenuItem("수정");
		editMenu.addActionListener(myPopListener);
		popup.add(editMenu);
		
		return popup;
	}
	//팝업메뉴 액션리스너
	ActionListener myPopListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
				
			//수정일때
			if(e.getActionCommand()=="수정") {
				//선택한 위치의 employee객체를 구하고 그 데이터를 다이얼로그에 세팅
					Employee emp = pEmpTblPanel.getSelectedItem();
					
					try {
						emp111 = service.showPikedEmpByCode(emp.getEmpCode());
					}catch (Exception e1) {
						e1.printStackTrace();
					}
					if(dlgEmpAuth == null) {
						dlgEmpAuth = new DlgEmpAuth();
					}
					if(dlgEmpAuth != null){
						dlgEmpAuth.setVisible(true);
						dlgEmpAuth.setItem(emp111);
					}
				
					
					//다이얼로그 버튼에 myDlgActionListner달기
					dlgEmpAuth.getBtnOk().addActionListener(myDlgActionListner);
					dlgEmpAuth.getBtnCancel().addActionListener(myDlgActionListner);			    
				
			}
			
		}
	};
	
	ActionListener myDlgActionListner = new ActionListener() {
		
		private Employee updateEmp;

		@Override
		public void actionPerformed(ActionEvent e) {

				
			if(e.getActionCommand()=="수정") {
				//다이얼로그에서 수정을 누르면 디비에서 권한부분 수정 됨 
				try{
					Employee emp = pEmpTblPanel.getSelectedItem();
				

			
				updateEmp = dlgEmpAuth.getItem(emp);
				if(updateEmp == null) {
					JOptionPane.showMessageDialog(null, "입력한 값을 확인하세요(대 소문자 구별)");
					return;
				}
				service.modifyEmpAuth(updateEmp);
				JOptionPane.showMessageDialog(null, "권한이 수정되었습니다");
				dlgEmpAuth.setVisible(false);
			 }catch (Exception e1) {
				// TODO: handle exception
			}
		 }
				
			if(e.getActionCommand()=="취소") {
				//다이얼로그에서 취소 누르면 다이얼로그의 권한 값이 삭제됨
				dlgEmpAuth.clearTf();
			}
			//클리어하기
			dlgEmpAuth.clearTf();
			//리스트 다시 불러오기 
			pEmpTblPanel.loadTableData(service.showEmpList());
		}
	};

	
	
	
	
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
		pEmpTblPanel.loadTableData(service.showEmpList());
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
					if(selectedOne.equals("검색구분")) {
						JOptionPane.showMessageDialog(null, "검색할 조건을 선택해주세요");
						return;
					}
					if(selectedOne.equals("사원이름")) {
					  //list.add(service.showPickedEmp(empItem));
					  list = service.showPickedEmpList(empItem);
					  if(list.size()==0) {
						  JOptionPane.showMessageDialog(null, "해당하는 사원이 없습니다");
						  return;
					  }
				    }else if(selectedOne.equals("부서 (인사  or 고객)")) {
				      list = service.showPickedEmpByDept(empItem);
				      if(list.size()==0) {
						  JOptionPane.showMessageDialog(null, "해당하는 사원이 없습니다");
						  return;
					  }
				    }else if(selectedOne.equals("사원번호")) {
				      list = service.showPickedEmpByEmpNo(empItem);
				      if(list.size()==0) {
						  JOptionPane.showMessageDialog(null, "해당하는 사원이 없습니다");
						  return;
					  }
				    }else if(selectedOne.equals("직급")) {
				      list = service.showPickedEmpByTitle(empItem);
				      if(list.size()==0) {
						  JOptionPane.showMessageDialog(null, "해당하는 사원이 없습니다");
						  return;
					  }
				    }
					
					
					pEmpTblPanel.loadTableData(list);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "다시 검색해주세요");
					e1.printStackTrace();
					return;
		}
	
	}
	public EmpCenterNorthSearchPanel getpEmpSerch() {
		return pEmpSerch;
	}

	
}
