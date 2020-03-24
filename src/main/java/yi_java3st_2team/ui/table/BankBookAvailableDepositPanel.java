package yi_java3st_2team.ui.table;

import javax.swing.SwingConstants;

import yi_java3st_2team.dto.BankBook;
import yi_java3st_2team.ui.absPanel.AbsCenterTblPanel;

@SuppressWarnings("serial")
public class BankBookAvailableDepositPanel extends AbsCenterTblPanel<BankBook> {

	/**
	 * Create the panel.
	 */
	public BankBookAvailableDepositPanel() {
		
	}

	@Override
	protected void setTblWidthAlign() {
		setColumnAlign(SwingConstants.CENTER, 0);
		setColumnWidth(300);
	}

	@Override
	protected String[] getColumns() {
		return new String[] {"계좌번호"};
	}

	@Override
	protected Object[] toArray(BankBook item) {
		return new Object[] {item.getAccountNum()};
	}

	@Override
	protected void updateRow(BankBook item, int updateIdx) {
		
	}

	@Override
	public BankBook getSelectedItem() {
		int idx = getSelectedRowIdx();
		BankBook bankbook = new BankBook();
		bankbook.setAccountNum((String)(model.getValueAt(idx, 0)));
		bankbook.setConnectChk(true);
		return bankbook;
	}
	

}
