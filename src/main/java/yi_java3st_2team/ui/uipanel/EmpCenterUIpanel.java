package yi_java3st_2team.ui.uipanel;

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
import yi_java3st_2team.ui.panel.EmpCenterNorthSearchPanel;
import yi_java3st_2team.ui.service.EmployeeUIService;
import yi_java3st_2team.ui.table.EmpCenterTblPanel;
import yi_java3st_2team.ui.table.EmpCenterTblPanel2Work;


public class EmpCenterUIpanel extends JPanel implements ActionListener {
	private EmployeeUIService service;
	private EmpCenterNorthSearchPanel pEmpSerch;
	private EmpCenterTblPanel pEmpTblPanel;
	private DlgEmp dlgEmp;


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
	
	//다이얼로그의 버튼들에 액션리스너 달기
			ActionListener myDlgActionListner = new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getActionCommand().contentEquals("추가")) {
						//System.out.println("추가 눌렀음 ");
						//다이어로그에서 추가를 누르면 디비 employee테이블 에 선택한 값들이 들어감 	
						
						try{Employee addEmp = dlgEmp.getItem();  //임플로이 생성자로 생성
						//서비스로 인서트구문 만들어 넣기
						service.addEmp(addEmp);
			            JOptionPane.showMessageDialog(null, "추가되었습니다");
						//창 닫기
						dlgEmp.setVisible(false);
						//리스트 다시 불러오기 
						pEmpTblPanel.loadTableData(service.showEmpList());
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
						Employee updateEmp = dlgEmp.getItem();
	                 //com.mysql.jdbc.MysqlDataTruncation: Data truncation: Data too long for column 'empName' at row 1
						//해결해야함 0225 
					    service.modifyEmp(updateEmp);
					    if(updateEmp == null) {
					    	return;
					    }
						JOptionPane.showMessageDialog(null, "수정 되었습니다");
						pEmpTblPanel.loadTableData(service.showEmpList());
						dlgEmp.setVisible(false);
						}
						
						catch (NullPointerException e2) {
							
							JOptionPane.showMessageDialog(null, "부서를 입력해주세요");
							return;
						}
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
				pEmpSerch.getTfSearch().setText("");
				//취소 누르면 검색창 초기화 된 후 
				//테이블이 다시 원래 상태로 돌아간다
				pEmpTblPanel.loadTableData(service.showEmpList());
			}
			protected void pEmpSerchBtnSearchActionPerformed(ActionEvent e) {
				String eName = pEmpSerch.getTfSearch().getText();
				List<Employee> list = new ArrayList<Employee>();
			    try {
			    	Employee empOne = service.showPickedEmp(eName);
			    	 if(list.size() == 0) {
			    		 list.add(empOne);
			    		// JOptionPane.showMessageDialog(null,list.size()); //1
			    	 }else { //이까지 안옴 ㅠㅠ 0222
			    		 JOptionPane.showMessageDialog(null, "이미 사원이 조회되어 있습니다. 취소 후 다시 조회해주세요.");
							return;
			    	 }
			    	pEmpTblPanel.loadTableData(list);
			    }catch(SQLException e1) {
					System.out.println("해당 사원이 없습니다.");
					e1.printStackTrace();
			    }
			}
			
			
			//팝업메뉴 액션리스너
			ActionListener myPopListener = new ActionListener() {
				
				private JButton btn;

				@Override
				public void actionPerformed(ActionEvent e) {
					//추가일때
					if(e.getActionCommand().contentEquals("추가")) {
					//	System.out.println("추가추가");
						if(dlgEmp == null) {
						dlgEmp = new DlgEmp();
						}
						//부서 리스트 가져와서 콤보박스에 넣기 
						dlgEmp.removeAll();
						dlgEmp = new DlgEmp();
						dlgEmp.setCmbDeptList(service.showDeptList());
						dlgEmp.setVisible(true);
						dlgEmp.getBtnOk().addActionListener(myDlgActionListner); //ok가 애초에 추가로 설정 
				        dlgEmp.getBtnCancel().addActionListener(myDlgActionListner);
						
					//수정일때
					}if(e.getActionCommand().contentEquals("수정")) {
					//	System.out.println("수정수정");
						//선택한 위치의 employee객체를 구하고 그 데이터를 다이얼로그에 세팅
					
							Employee emp = pEmpTblPanel.getSelectedItem();
							if(dlgEmp == null) {		
								dlgEmp = new DlgEmp();
							}
								dlgEmp.removeAll();
								dlgEmp = new DlgEmp();
							
							dlgEmp.setActionCommendClose().addActionListener(myDlgActionListner);
							dlgEmp.setActionCommendToUpdate().addActionListener(myDlgActionListner);
							dlgEmp.setCmbDeptList(service.showDeptList());
							dlgEmp.setVisible(true);
							dlgEmp.setItem(emp);
				    
						
					}if(e.getActionCommand().contentEquals("삭제")) {
						//선택한 위치의  employee객체를 구하고 그걸 데이터에서 삭제 
						Employee emp = pEmpTblPanel.getSelectedItem();
						
						service.removeEmp(emp);
						JOptionPane.showMessageDialog(null, "삭제되었습니다");
						pEmpTblPanel.loadTableData(service.showEmpList());
						
					}
			
					
				}
			};
			
		private JPopupMenu createPopup() {
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
	
	
	
	
		
	
}
