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
import java.awt.event.ActionEvent;

public class CustPlanUIPanel extends JPanel{
	PlanService planService = new PlanService();
	private CustPlanCenterCenterTblPanel panel_1;
	private CustPlanCenterNorthSearchPanel panel;
	
	public CustPlanUIPanel() {

		initialize();
	}
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		panel = new CustPlanCenterNorthSearchPanel();
		panel.getBtnSearch().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String planName = panel.getTfSearch().getText().trim();
				List<Plan> list = new ArrayList<>();
				try {
					if(list.size()==0){
						list = planService.showPlansByName(planName);
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
								planService.addPlan(plan);
								JOptionPane.showMessageDialog(null, "신규 상품이 추가되었습니다.");
								dlgcustplan.dispose();
								refreshTbl();
							}
								
							
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, "이미 존재하는 고객 입니다. 다시 입력하세요.");
						} 
						
					}
					
				});
				dlgcustplan.setVisible(true);
				
			}
			
		});
		JMenuItem editMenu = new JMenuItem("수정");
		editMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DlgCustPlan dlgcustplan = new DlgCustPlan();
				dlgcustplan.setActiontoEdit();
				try {
					Plan plan = panel_1.getSelectedItem();
					dlgcustplan.setVisible(true);
					dlgcustplan.setItem(plan);
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
					planService.removePlan(plan);
					JOptionPane.showMessageDialog(null, "삭제 되었습니다.");
					refreshTbl();
					
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

}
