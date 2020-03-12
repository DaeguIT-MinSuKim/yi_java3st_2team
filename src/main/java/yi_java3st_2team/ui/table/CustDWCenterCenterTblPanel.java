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
		setColumnAlign(SwingConstants.CENTER, 0,1);
		setColumnAlign(SwingConstants.RIGHT, 2);
		setColumnWidth(100,100,100);
	}

	@Override
	protected String[] getColumns() {
		return new String[] {"고객명", "계좌번호" , "잔액"};
		
	}

	@Override
	protected Object[] toArray(Customer item) {
		return new Object[] {
				item.getCustName(),
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
		String custName = (String) model.getValueAt(selectedIdx, 0);
		String accountNum = (String) model.getValueAt(selectedIdx, 1);
		String balance = (String) model.getValueAt(selectedIdx, 2);
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
