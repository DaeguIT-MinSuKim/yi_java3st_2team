package yi_java3st_2team.ui.panel;

import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import yi_java3st_2team.ui.dialog.DlgCustPlan;
import yi_java3st_2team.ui.service.PlanService;
import yi_java3st_2team.ui.table.CustPlanCenterCenterTblPanel;
import yi_java3st_2team.dto.Customer;
import yi_java3st_2team.dto.Plan;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionEvent;

public class CustPlanUIPanel extends JPanel implements ItemListener{
	PlanService planService = new PlanService();
	private CustPlanCenterCenterTblPanel panel_1;
	private CustPlanCenterNorthSearchPanel panel;
	
	public CustPlanUIPanel() {

		initialize();
	}
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		panel = new CustPlanCenterNorthSearchPanel();
		panel.getCmbSearchList().addItemListener(this);
		panel.getBtnSearch().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String search = (String) panel.getCmbSearchList().getSelectedItem();
				if(search.equals("검색 구분")) {
					JOptionPane.showMessageDialog(null, "검색 범위를 선택하세요.");
					return;
				}
				String planInfo = panel.getTfSearch().getText().trim();
				List<Plan> list = new ArrayList<>();
				try {
					if(list.size()==0){
						if(search.equals("상품 코드(A)")) {
							
							list = planService.showPlansByCode(planInfo);
						}else if(search.equals("상품 이름")) {
							list = planService.showPlansByName(planInfo);
						}else if(search.equals("상품 세부코드(AB)")) {
							list = planService.showPlansByDetail(planInfo);
						}
					}
					if(list==null) {
						JOptionPane.showMessageDialog(null, "해당 상품이 없습니다.");
						return;
					}
					panel_1.loadTableData(list);
					
				}catch (SQLException e1) {
					System.out.println("해당 상품이 없습니다.");
					e1.printStackTrace();
				}
				
			}

			
		});
		panel.getBtnCancel().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panel.tfClear();
				try {
					panel_1.loadTableData(planService.showPlans());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
		});
		add(panel, BorderLayout.NORTH);
		
		panel_1 = new CustPlanCenterCenterTblPanel();
		try {
			panel_1.loadTableData(planService.showPlans());
			panel_1.setPopupMenu(createPopup());
		} catch (SQLException e) {
			System.out.println("해당 상품이 없습니다.");
			e.printStackTrace();
		}
		add(panel_1, BorderLayout.CENTER);
	}
	
	
	
	private JPopupMenu createPopup() {
		JPopupMenu popup = new JPopupMenu();
		
		JMenuItem addMenu = new JMenuItem("추가");
		addMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DlgCustPlan dlgcustplan = new DlgCustPlan();
				dlgcustplan.setActiontoAdd();
				dlgcustplan.getCmbPlanDetail().addItemListener(new ItemListener() {
				
					@Override
					public void itemStateChanged(ItemEvent e) {
						if(dlgcustplan.getCmbPlanDetail().getSelectedIndex()==-1) {
							dlgcustplan.getLblSelectPlease().setText("상품 세부코드를 선택하세요.");
						}else {
							dlgcustplan.getLblSelectPlease().setText("");
						}
						try {
							//다이얼로그 상품세부코드 선택 시 상품코드에 자동으로 다음 번호 부여
							int planA = planService.showPlanA();
							int planB = planService.showPlanB();
							int planC = planService.showPlanC();
							int planAA = planService.showPlanAA();
							int planAB = planService.showPlanAB();
							int planAC = planService.showPlanAC();
							int planBA = planService.showPlanBA();
							int planBB = planService.showPlanBB();
							int planCA = planService.showPlanCA();
							int planCB = planService.showPlanCB();
							int planCC = planService.showPlanCC();
							String plan = (String) dlgcustplan.getCmbPlanDetail().getSelectedItem();
							int index = plan.indexOf(")");
							String Planchk = plan.substring((plan.indexOf("(")+1),index-1);//상품 세부코드 앞자리 'A'B
							String newPlan = plan.substring((plan.indexOf("(")+2),index); //상품 세부코드 뒷자리 A'B'
							
							if(Planchk.equals("A")) {
								dlgcustplan.getTfCustPlanCode().setText(Planchk + String.format("%03d", planA+1));
								if(newPlan.equals("A")) {
									dlgcustplan.getTfCustDetail().setText(String.format("%03d", planAA+1));
								}else if(newPlan.equals("B")) {
									dlgcustplan.getTfCustDetail().setText(String.format("%03d", planAB+1));
								}else if(newPlan.equals("C")){
									dlgcustplan.getTfCustDetail().setText(String.format("%03d", planAC+1));
								}
								
							}else if(Planchk.equals("B")) {
								dlgcustplan.getTfCustPlanCode().setText(Planchk + String.format("%03d", planB+1));
								if(newPlan.equals("A")) {
									dlgcustplan.getTfCustDetail().setText(String.format("%03d", planBA+1));
								}else if(newPlan.equals("B")) {
									dlgcustplan.getTfCustDetail().setText(String.format("%03d", planBB+1));
								}
							}else {
								dlgcustplan.getTfCustPlanCode().setText(Planchk + String.format("%03d", planC+1));
								if(newPlan.equals("A")) {
									dlgcustplan.getTfCustDetail().setText(String.format("%03d", planCA+1));
								}else if(newPlan.equals("B")) {
									dlgcustplan.getTfCustDetail().setText(String.format("%03d", planCB+1));
								}else {
									dlgcustplan.getTfCustDetail().setText(String.format("%03d", planCC+1));
								}
								
							}
							
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
						
					}
					
				});
				dlgcustplan.getOkButton().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						Plan plan = dlgcustplan.getItem();
						
						try {
							
							if(plan == null) {
								JOptionPane.showMessageDialog(null, "빈 칸을 채워주세요.");
								return;
							}
							if(e.getActionCommand().equals("추가")) {
								String code = plan.getPlanCode();
								List<String> list = planService.planExistChk();
								Boolean falsechk = false;
									for(int i=0; i<list.size(); i++) {											
										if(code.equals(list.get(i))) {												
											falsechk = false;
											break;
										}else {											
											falsechk = true;
										}
									}
									
									if(falsechk == true) {
										planService.addPlan(plan);
										JOptionPane.showMessageDialog(null, "신규 상품이 추가되었습니다.");
										dlgcustplan.dispose();
										refreshTbl();
									}else {
										JOptionPane.showMessageDialog(null, "이미 등록된 상품입니다.");
									}
							
							}
								
							
						} catch (SQLException e1) {
							e1.printStackTrace();
						} 
						
					}
					
				});
				dlgcustplan.setLocation(1310, 100);
				dlgcustplan.setVisible(true);
				
			}
			
		});
		JMenuItem editMenu = new JMenuItem("수정");
		editMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DlgCustPlan dlgcustplan = new DlgCustPlan();
				dlgcustplan.getLblSelectPlease().setText("");
				dlgcustplan.getTfCustPlanCode().setEditable(false);
				dlgcustplan.getTfCustDetail().setEditable(false);
				dlgcustplan.getCmbCustPlanDiv().setEnabled(false);
				dlgcustplan.getCmbCustPlanDiv().setEnabled(false);
				dlgcustplan.getCmbPlanDetail().setEnabled(false);
				dlgcustplan.setActiontoEdit();
				try {
					Plan plan = panel_1.getSelectedItem();
					if(plan!=null) {
						dlgcustplan.setLocation(1310, 100);
						dlgcustplan.setVisible(true);
						dlgcustplan.setItem(plan);
					}else {
						JOptionPane.showMessageDialog(null, "상품을 선택하세요.");
					}
					
				}catch (RuntimeException e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}

				dlgcustplan.getOkButton().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						
						try {
							Plan plan = dlgcustplan.getItem();
							planService.editPlan(plan);
							JOptionPane.showMessageDialog(null, "수정 되었습니다.");
							dlgcustplan.dispose();
							refreshTbl();
							
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					
						
					}
					
				});
				
			}
			
		});
		
		JMenuItem deleteMenu = new JMenuItem("삭제");
		deleteMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Plan plan = panel_1.getSelectedItem();
				
				try {
					if(plan!=null) {
						planService.removePlan(plan);
						JOptionPane.showMessageDialog(null, "삭제 되었습니다.");
						refreshTbl();
					}else {
						JOptionPane.showMessageDialog(null, "상품을 선택하세요.");
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
			}
			
		});
		
		popup.add(addMenu);
		popup.add(editMenu);
		popup.add(deleteMenu);
		
		return popup;
	}
	
	public void refreshTbl() throws SQLException {
		List<Plan> list = planService.showPlans();
		panel_1.loadTableData(list);
	}

	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == panel.getCmbSearchList()) {
			panelCmbSearchListItemStateChanged(e);
		}
	}
	protected void panelCmbSearchListItemStateChanged(ItemEvent e) {
		try {
			refreshTbl();
			panel.getTfSearch().setText("");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
