package yi_java3st_2team.ui.table;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import yi_java3st_2team.dto.Plan;
import yi_java3st_2team.ui.absPanel.AbsCenterTblPanel;

public class CustPlanCenterCenterTblPanel extends AbsCenterTblPanel<Plan> {

	/**
	 * Create the panel.
	 */
	public CustPlanCenterCenterTblPanel() {

	}

	@Override
	protected void setTblWidthAlign() {
		setColumnAlign(SwingConstants.CENTER, 0,1,2,3,4);
		setColumnWidth(100,100,100,100,100);
	}

	@Override
	protected String[] getColumns() {
		return new String[] {"상품 코드", "상품 세부코드", "상품 이름", "상품 설명", "상품 구분코드"};
	}

	@Override
	protected Object[] toArray(Plan item) {
		return new String[] {
				item.getPlanCode(),
				item.getPlanDetail(),
				item.getPlanName(),
				item.getPlanDesc(),
				item.getPlanDiv()
		};
	}

	@Override
	protected void updateRow(Plan item, int updateIdx) {
		model.setValueAt(item.getPlanCode(), updateIdx, 0);
		model.setValueAt(item.getPlanDetail(), updateIdx, 1);
		model.setValueAt(item.getPlanName(), updateIdx, 2);
		model.setValueAt(item.getPlanDesc(), updateIdx, 3);
		model.setValueAt(item.getPlanDiv(), updateIdx, 4);
		
	}

	@Override
	public Plan getSelectedItem() {
		int selectedIdx = getSelectedRowIdx();
		if(selectedIdx==-1) {
			return null;
		}else {
			String planCode = (String) model.getValueAt(selectedIdx, 0);
			String planDetail = (String) model.getValueAt(selectedIdx, 1);
			String planName = (String) model.getValueAt(selectedIdx, 2);
			String planDesc = (String) model.getValueAt(selectedIdx, 3);
			String planDiv = (String) model.getValueAt(selectedIdx, 4);
			return new Plan(planCode, planDetail, planName, planDesc, planDiv);
		}
	}

}
