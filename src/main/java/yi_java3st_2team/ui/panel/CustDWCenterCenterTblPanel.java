package yi_java3st_2team.ui.panel;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import yi_java3st_2team.dto.Customer;
import yi_java3st_2team.ui.absPanel.AbsCenterTblPanel;

public class CustDWCenterCenterTblPanel extends AbsCenterTblPanel<Customer> {

	/**
	 * Create the panel.
	 */
	public CustDWCenterCenterTblPanel() {

	}

	@Override
	protected void setTblWidthAlign() {
		setColumnAlign(SwingConstants.CENTER, 0,1,2,3,4,5);
		setColumnWidth(100,100,100,100,100,100);
	}

	@Override
	protected String[] getColumns() {
		return new String[] {"고객명", "계좌번호" ,"입출금 구분", "금액", "잔액", "일시"};
	}

	@Override
	protected Object[] toArray(Customer item) {
		return new Object[] {
				item.getCustName(),
				item.getBankbook().getAccountNum(),
				null,
				null,
				item.getBankbook().getAccountBalance(),
				null
				
		};
	}

	@Override
	protected void updateRow(Customer item, int updateIdx) {
		
		
	}

	@Override
	protected Customer getSelectedItem() {
		return null;
	}

}
