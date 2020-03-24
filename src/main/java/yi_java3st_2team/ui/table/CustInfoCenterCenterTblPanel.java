package yi_java3st_2team.ui.table;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import yi_java3st_2team.dto.Customer;
import yi_java3st_2team.ui.absPanel.AbsCenterTblPanel;

public class CustInfoCenterCenterTblPanel extends AbsCenterTblPanel<Customer> {

	/**
	 * Create the panel.
	 */
	public CustInfoCenterCenterTblPanel() {
		setBorder(new EmptyBorder(10, 50, 30, 50));
	}

	@Override
	protected void setTblWidthAlign() {
		setColumnAlign(SwingConstants.CENTER, 0,1,2,3,4,5);
		setColumnWidth(50,50,50,50,100,100);
	}

	@Override
	protected String[] getColumns() { 
		return new String[] {"고객 코드","고객 이름", "고객 등급", "고객 신용등급", "고객 주소", "고객 연락처"};
	}

	@Override
	protected Object[] toArray(Customer item) {
		String rank;
		if(item.getCustRank().equals("D")) {
			rank = "Diamond";
		}else if(item.getCustRank().equals("P")) {
			rank = "Platinum";
		}else if(item.getCustRank().equals("G")) {
			rank = "Gold";
		}else if(item.getCustRank().equals("S")) {
			rank = "Silver";
		}else{
			rank = "Bronze";
		}
		String tel = item.getCustTel();
		int lastIdx = tel.lastIndexOf("-");
		String new_tel = tel.substring(0,lastIdx+1)+"****";
		return new Object[] {
				
				item.getCustCode(),
				item.getCustName(),
				rank,
				item.getCustCredit(),
				item.getCustAddr(),
				new_tel
		};
	}

	@Override
	protected void updateRow(Customer item, int updateIdx) {
		model.setValueAt(item.getCustCode(), updateIdx, 0);
		model.setValueAt(item.getCustName(), updateIdx, 1);
		model.setValueAt(item.getCustRank(), updateIdx, 2);
		model.setValueAt(item.getCustCredit(), updateIdx, 3);
		model.setValueAt(item.getCustAddr(), updateIdx, 4);
		model.setValueAt(item.getCustTel(), updateIdx, 5);
		
		
	}

	@Override
	public Customer getSelectedItem() {
		//선택된 고객이 없을 경우 추상 패널에서 던지는 RuntimeException 에러 처리 
		int selectedIdx = -1;
		try {
			selectedIdx = getSelectedRowIdx();
		}catch(RuntimeException e) {
			return null;
		}
		if(selectedIdx==-1) {
			return null;
		}else {
			String custCode = (String) model.getValueAt(selectedIdx, 0);
			String custName = (String) model.getValueAt(selectedIdx, 1);
			String custRank = (String) model.getValueAt(selectedIdx, 2);
			int custCredit = (int) model.getValueAt(selectedIdx, 3);
			String custAddr = (String) model.getValueAt(selectedIdx, 4);
			String custTel = (String) model.getValueAt(selectedIdx, 5);
			return new Customer(custCode, custName, custRank, custCredit, custAddr, custTel);
		}
	}

}
