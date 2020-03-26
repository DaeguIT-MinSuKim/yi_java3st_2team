package yi_java3st_2team.ui.panel.emp;

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

import javafx.scene.control.Alert;
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

	private Object selectedOne;
	
	private Employee emp111;
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
			
			

			private String sDeptName;

			@Override
			public void actionPerformed(ActionEvent e) {
				//추가일때
				selectedOne = pEmpSerch.getCmbSearchList().getSelectedItem();
				sDeptName = pEmpSerch.getTfSearch().getText().trim();
				
				
				if(e.getActionCommand()=="추가") {
					if(dlgEmpForUpdate !=null) {
						dlgEmpForUpdate.dispose();
					}
					if(selectedOne.equals("부서 (인사  or 고객)") == false 
							|| (selectedOne.equals("부서 (인사  or 고객)")&& sDeptName.equals("")) 
//							|| (selectedOne.equals("부서")&& (sDeptName.equals("인사")==false))
//							|| (selectedOne.equals("부서")&& (sDeptName.equals("고객")==false))
							) {
						if(dlgEmp != null) {
							dlgEmp.dispose();
						}

						dlgEmp = new DlgEmp();
						//부서 리스트 가져와서 콤보박스에 넣기 
						dlgEmp.setCmbDeptList(service.showDeptList());
						dlgEmp.setVisible(true);
						dlgEmp.clearTf();
					}
					
					  // 인사로 검색했을 경우
					else if(selectedOne.equals("부서 (인사  or 고객)")) {
						  if(sDeptName.equals("인사")) {
							 // System.out.println("인사에서 검색했음 ");
							  if(dlgEmp != null) {
									dlgEmp.dispose();
								}
			        
							dlgEmp = new DlgEmp();
							
							dlgEmp.setEmpCode("A",pEmpTblPanel);
							dlgEmp.getTfEmpCode().setEditable(false);
							//부서 리스트 가져와서 콤보박스에 넣기 
							dlgEmp.setCmbDeptList(service.showDeptList());
							//부서 인사로 선택하도록 하기
							dlgEmp.setComboDept(0);
							dlgEmp.setVisible(true);
	
					 
					   }else if(sDeptName.equals("고객")) {
						   if(dlgEmp != null) {
								dlgEmp.dispose();
							}
			
							dlgEmp = new DlgEmp();
							dlgEmp.setEmpCode("B",pEmpTblPanel);
							dlgEmp.getTfEmpCode().setEditable(false);
							//부서 리스트 가져와서 콤보박스에 넣기 
							dlgEmp.setCmbDeptList(service.showDeptList()); 
							dlgEmp.setComboDept(1);
							dlgEmp.setVisible(true);


					   }
					
					}
					//다이얼로그의 추가 취소 버튼 가져와서 액션리스너 달기
			        dlgEmp.getBtnOk().addActionListener(myDlgActionListner);
			        dlgEmp.getBtnCancel().addActionListener(myDlgActionListner);
					
				//수정일때
				}if(e.getActionCommand()=="수정") {
					//선택한 위치의 employee객체를 구하고 그 데이터를 다이얼로그에 세팅
					
					    if(dlgEmp !=null) {
					    	dlgEmp.dispose();
					    }
						
					
						try {
							Employee empForUpdate = pEmpTblPanel.getSelectedItem();
							emp111 = service.showPikedEmpByCode(empForUpdate.getEmpCode());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							
						}catch (RuntimeException e2) {
							JOptionPane.showMessageDialog(null, "선택해주세요");
							return;
						}
						
						
						
						
						if(dlgEmpForUpdate != null){
							dlgEmpForUpdate.dispose();
						}
						dlgEmpForUpdate = new DlgEmp();
						dlgEmpForUpdate.setEmp(emp111);
						dlgEmpForUpdate.setCmbDeptList(service.showDeptList());
						dlgEmpForUpdate.setVisible(true);
						dlgEmpForUpdate.setItem(emp111);
						
		
						//다이얼로그 버튼을 수정으로 바꾸고 myDlgActionListner달기
						dlgEmpForUpdate.getBtnUpdate().addActionListener(myDlgActionListner);
						dlgEmpForUpdate.setActionCommendClose().addActionListener(myDlgActionListner);
				    
					
				}if(e.getActionCommand()=="삭제") {
					//경고창 출력
					
					
					//선택한 위치의  employee객체를 구하고 그걸 데이터에서 삭제 
					Employee emp = pEmpTblPanel.getSelectedItem();
					         
					System.out.println(emp);
					int res = JOptionPane.showConfirmDialog(null, emp.getEmpName()+"님의 정보를 정말 삭제하시겠습니까?");
					if(res == 0) {
						
						try {
							service.removeEmp(emp);
						}catch (Exception e5) {
							e5.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "삭제되었습니다");
					}

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

    
	public DlgEmp getDlgEmp() {
		return dlgEmp;
	}
	public EmpCenterTblPanel getpEmpTblPanel() {
		return pEmpTblPanel;
	}
	public void setpEmpTblPanel(EmpCenterTblPanel pEmpTblPanel) {
		this.pEmpTblPanel = pEmpTblPanel;
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
					    	service.addEmp(addEmp);
					           //  JOptionPane.showMessageDialog(null, "추가되었습니다");
								
					    	   pEmpSerch.getCmbSearchList().setSelectedIndex(0);
							   pEmpSerch.getTfSearch().setText("");
								//리스트 다시 불러오기 
								pEmpTblPanel.loadTableData(service.showEmpList());
								//창 닫기
								
								JOptionPane.showMessageDialog(null,addEmp.getEmpName()+"님이 사원리스트에 추가되었습니다");
								dlgEmp.setVisible(false);
								
								
								
					    }catch(Exception e4){
					    	//System.out.println(e4.getMessage() + "는 이것 "); //For input string: ""는 이것 
					    	//System.out.println(e4.getMessage() + "는 이것 ");
					    	if(e4.getMessage().contains("")) {
					    	  JOptionPane.showMessageDialog(null, "정보를 올바르게 입력해주세요");
					    	  dlgEmp.setVisible(true);
					    	  return;
					    	}
//					    	else if(e4.getMessage().contains("PRIMARY")) {
//								JOptionPane.showMessageDialog(null, "사원번호 중복입니다");
//								return;
//							}
					    }
						//서비스로 인서트구문 만들어 넣기
//						try{ 
//						
//						
//						}catch (Exception e2) {
//							//e2.printStackTrace();
//							JOptionPane.showMessageDialog(null, "입력한 정보를 다시 확인해주세요");
//							return;
//						}
							
						
						
						
					}if(e.getActionCommand().contentEquals("수정")) {
						//다이얼로그에서 수정을 누르면 디비에서 데이터가 수정 됨 
						//System.out.println("수정 눌렀음 ");
						try {
						Employee updateEmp = dlgEmpForUpdate.getItem();
						
						
						//System.out.println(updateEmp);
						
						if(updateEmp == null) {
					    	return;
					    }
						
						//다이얼로그에서 받아온 비밀번호 값이 기본값인 경우와 비밀번호도 수정한 경우 
	                    if(dlgEmpForUpdate.getTfEmpPwd().getText().equals("**********")) {
	                    	service.modifyEmpExceptForPwd(updateEmp);
	                    }
	                    else{
	                    	service.modifyEmp(updateEmp);
	                    }
					    
						pEmpTblPanel.loadTableData(service.showEmpList());
						dlgEmpForUpdate.setVisible(false);
						JOptionPane.showMessageDialog(null, "수정 되었습니다");
						
						}catch (NullPointerException e2) {
	                     //  JOptionPane.showMessageDialog(null, "부서를 입력해주세요");
							e2.printStackTrace();
							return;
						}
						
						
					}
						
					if(e.getActionCommand().contentEquals("취소")) {
						//System.out.println("취소 눌렀음 ");
						//다이얼로그에서 취소 누르면 다이얼로그 텍스트 값들이 초기화됨 
						
						try{
							dlgEmp.clearTf();
						}catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "취소되었습니다");
						}
					}
					
					if(e.getActionCommand().contentEquals("닫기")) {
						//dlgEmp.setVisible(false);
						dlgEmpForUpdate.dispose();
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
		    }else if(selectedOne.equals("부서 (인사  or 고객)")) {
		      list = service.showPickedEmpByDept(empItem);
		    }else if(selectedOne.equals("사원번호")) {
		      list = service.showPickedEmpByEmpNo(empItem);
		    }else if(selectedOne.equals("직급")) {
		      list = service.showPickedEmpByTitle(empItem);
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

