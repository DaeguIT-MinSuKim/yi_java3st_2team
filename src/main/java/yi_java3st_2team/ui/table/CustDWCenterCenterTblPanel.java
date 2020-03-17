package yi_java3st_2team.ui.table;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import yi_java3st_2team.dto.BankBook;
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
		setColumnAlign(SwingConstants.CENTER, 0,1,2);
		setColumnAlign(SwingConstants.RIGHT, 3);
		setColumnWidth(100,100,100,100);
	}

	@Override
	protected String[] getColumns() {
		return new String[] {"고객명", "통장","계좌번호", "잔액"};
		
	}

	@Override
	protected Object[] toArray(Customer item) {
		
		String number = item.getBankbook().getAccountNum().substring(0, 9);
		String account= number.substring(7);
		if(account.equals("11")) {
			account = "예금";
		}else {
			account = "적금";
		}
		return new Object[] {
				item.getCustName(),
				account,
				item.getBankbook().getAccountNum(),
				//item.getBankbook().getAccountBalance(),
				String.format("%,d", item.getBankbook().getAccountBalance())
				
		};
	}

	@Override
	protected void updateRow(Customer item, int updateIdx) {
		
		
	}

	@Override
	public Customer getSelectedItem() {
		int selectedIdx = getSelectedRowIdx();
		if(selectedIdx==-1) {
			return null;
		}else {
			String custName = (String) model.getValueAt(selectedIdx, 0);
			String accountNum = (String) model.getValueAt(selectedIdx, 2);
			String balance = (String) model.getValueAt(selectedIdx, 3);
			balance = balance.replace(",", "");
			long custBalance = Long.parseLong(balance);
			
			
			Customer customer = new Customer();
			BankBook bankbook = new BankBook();
			
			customer.setCustName(custName);
			bankbook.setAccountNum(accountNum);
			bankbook.setAccountBalance(custBalance);
			
			customer.setBankbook(bankbook);
			return customer;
		}
	}

}
