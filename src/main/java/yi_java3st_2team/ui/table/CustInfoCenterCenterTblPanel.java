package yi_java3st_2team.ui.table;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import yi_java3st_2team.dto.Customer;
import yi_java3st_2team.ui.absPanel.AbsCenterTblPanel;

public class CustInfoCenterCenterTblPanel extends AbsCenterTblPanel<Customer> {

	/**
	 * Create the panel.
	 */
	public CustInfoCenterCenterTblPanel() {

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
		return new Object[] {
				item.getCustCode(),
				item.getCustName(),
				item.getCustRank(),
				item.getCustCredit(),
				item.getCustAddr(),
				item.getCustTel()
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
		int selectedIdx = getSelectedRowIdx();
		String custCode = (String) model.getValueAt(selectedIdx, 0);
		String custName = (String) model.getValueAt(selectedIdx, 1);
		String custRank = (String) model.getValueAt(selectedIdx, 2);
		int custCredit = (int) model.getValueAt(selectedIdx, 3);
		String custAddr = (String) model.getValueAt(selectedIdx, 4);
		String custTel = (String) model.getValueAt(selectedIdx, 5);
		return new Customer(custCode, custName, custRank, custCredit, custAddr, custTel);
	}

}
