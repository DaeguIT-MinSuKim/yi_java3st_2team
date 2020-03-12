package yi_java3st_2team.ui.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import com.mysql.jdbc.MysqlDataTruncation;

import yi_java3st_2team.dto.Employee;
import yi_java3st_2team.ui.dialog.DlgEmp;
import yi_java3st_2team.ui.service.EmployeeUIService;
import yi_java3st_2team.ui.table.EmpCenterTblPanel;
import yi_java3st_2team.ui.table.EmpCenterTblPanel2Work;


public class EmpCenterUIpanel extends JPanel implements ActionListener {
	private EmployeeUIService service;
	private EmpCenterNorthSearchPanel pEmpSerch;
	private EmpCenterTblPanel pEmpTblPanel;
	private DlgEmp dlgEmp;
	private DlgEmp dlgEmpForUpdate;


	public EmpCenterUIpanel() {
		service = new EmployeeUIService();
		initialize();
	}
	private void initialize() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		pEmpSerch = new EmpCenterNorthSearchPanel();

		add(pEmpSerch);
		
		
		pEmpTblPanel = new EmpCenterTblPanel();
	    //리스트불러오기
		pEmpTblPanel.loadTableData(service.showEmpList());
		add(pEmpTblPanel);
		
		//테이블 패널에 팝업메뉴달기
		pEmpTblPanel.setPopupMenu(createPopup());
		pEmpSerch.getBtnSearch().addActionListener(this);
		pEmpSerch.getBtnCancel().addActionListener(this);
	
	}
    
	private JPopupMenu createPopup() {
		
		//팝업메뉴 액션리스너
		ActionListener myPopListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//추가일때
				if(e.getActionCommand()=="추가") {
					if(dlgEmp == null) {
					dlgEmp = new DlgEmp();
					}
					//부서 리스트 가져와서 콤보박스에 넣기 
					dlgEmp.setCmbDeptList(service.showDeptList());
					dlgEmp.setVisible(true);
					//다이얼로그의 추가 취소 버튼 가져와서 액션리스너 달기
			        dlgEmp.getBtnOk().addActionListener(myDlgActionListner);
			        dlgEmp.getBtnCancel().addActionListener(myDlgActionListner);
					
				//수정일때
				}if(e.getActionCommand()=="수정") {
					//선택한 위치의 employee객체를 구하고 그 데이터를 다이얼로그에 세팅
						Employee emp = pEmpTblPanel.getSelectedItem();
						if(dlgEmpForUpdate == null) {
							dlgEmpForUpdate = new DlgEmp();
						}
						if(dlgEmpForUpdate != null){
							dlgEmpForUpdate.setVisible(true);
							dlgEmpForUpdate.setItem(emp);
						}
						dlgEmpForUpdate.setCmbDeptList(service.showDeptList());
						
						//다이얼로그 버튼을 수정으로 바꾸고 myDlgActionListner달기
						dlgEmpForUpdate.getBtnUpdate().addActionListener(myDlgActionListner);
						dlgEmpForUpdate.setActionCommendClose().addActionListener(myDlgActionListner);
				    
					
				}if(e.getActionCommand()=="삭제") {
					//선택한 위치의  employee객체를 구하고 그걸 데이터에서 삭제 
					Employee emp = pEmpTblPanel.getSelectedItem();
					
					service.removeEmp(emp);
					JOptionPane.showMessageDialog(null, "삭제되었습니다");
					pEmpTblPanel.loadTableData(service.showEmpList());
					
				}
			}
		};
		JPopupMenu popup = new JPopupMenu();
		
		JMenuItem addMenu = new JMenuItem("추가");
		addMenu.addActionListener(myPopListener);
		popup.add(addMenu);
		
		JMenuItem editMenu = new JMenuItem("수정");
		editMenu.addActionListener(myPopListener);
		popup.add(editMenu);
		
		JMenuItem deleteMenu = new JMenuItem("삭제");
		deleteMenu.addActionListener(myPopListener);
		popup.add(deleteMenu);
		return popup;
	}


	//다이얼로그의 버튼들에 액션리스너 달기
			ActionListener myDlgActionListner = new ActionListener() {
				
				private Employee addEmp;

				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getActionCommand().contentEquals("추가")) {
						//System.out.println("추가 눌렀음 ");
						//다이어로그에서 추가를 누르면 디비 employee테이블 에 선택한 값들이 들어감 	
	
					    try{
					    	addEmp = dlgEmp.getItem();
					    }catch(Exception e4){
					    	//System.out.println(e4.getMessage() + "는 이것 "); //For input string: ""는 이것 
					    	System.out.println(e4.getMessage() + "는 이것 ");
					    	if(e4.getMessage().contains("")) {
					    	  JOptionPane.showMessageDialog(null, "정보를 올바르게 입력해주세요");
					    	  return;
					    	}
//					    	if(e4.getMessage().contains("PRIMARY")) {
//								JOptionPane.showMessageDialog(null, "사원번호 중복입니다");
//								return;
//							}
					    }
						//서비스로 인서트구문 만들어 넣기
						try{ 
						service.addEmp(addEmp);
			           //  JOptionPane.showMessageDialog(null, "추가되었습니다");
						
						//리스트 다시 불러오기 
						pEmpTblPanel.loadTableData(service.showEmpList());
						//창 닫기
						dlgEmp.setVisible(false);
						//JOptionPane.showMessageDialog(null, "추가되었습니다");
						
						}catch (Exception e2) {
							//e2.printStackTrace();
							JOptionPane.showMessageDialog(null, "입력한 정보를 다시 확인해주세요");
							return;
						}
						
						//0226부서번호가 없어도 들어간다 이게 무슨일이야..........ㅜ 
						
					}if(e.getActionCommand().contentEquals("수정")) {
						//다이얼로그에서 수정을 누르면 디비에서 데이터가 수정 됨 
						//System.out.println("수정 눌렀음 ");
						try {
						Employee updateEmp = dlgEmpForUpdate.getItem();
						
						System.out.println(updateEmp);
						
						if(updateEmp == null) {
					    	return;
					    }
	                 
					    service.modifyEmp(updateEmp);
					    
						pEmpTblPanel.loadTableData(service.showEmpList());
						dlgEmpForUpdate.setVisible(false);
						
						
						}catch (NullPointerException e2) {
	                     //  JOptionPane.showMessageDialog(null, "부서를 입력해주세요");
							e2.printStackTrace();
							return;
						}
						JOptionPane.showMessageDialog(null, "수정 되었습니다");
						
					}
						
					if(e.getActionCommand().contentEquals("취소")) {
						System.out.println("취소 눌렀음 ");
						//다이얼로그에서 취소 누르면 다이얼로그 텍스트 값들이 초기화됨 
						
						dlgEmp.clearTf();
					}
					
					if(e.getActionCommand().contentEquals("닫기")) {
						dlgEmp.setVisible(false);
					}
					
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
		String empName = pEmpSerch.getTfSearch().getText().trim();
	
		if(pEmpSerch.getTfSearch().getText().contentEquals("")) {
			JOptionPane.showMessageDialog(null, "사원 이름을 입력해주세요");
			return;
		}
		
		List<Employee> list = new ArrayList<Employee>(); 
		
		try {
			list.add(service.showPickedEmp(empName));
			pEmpTblPanel.loadTableData(list);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "다시 검색해주세요");
			e1.printStackTrace();
			return;
		}
	
	}
}	

