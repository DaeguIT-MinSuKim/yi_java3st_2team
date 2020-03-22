package yi_java3st_2team.ui.table;

import javax.swing.SwingConstants;

import yi_java3st_2team.dto.AccountInfo;
import yi_java3st_2team.ui.absPanel.AbsCenterTblPanel;

@SuppressWarnings("serial")
public class TerminationInfoTblPanel extends AbsCenterTblPanel<AccountInfo> {

	/**
	 * Create the panel.
	 */
	public TerminationInfoTblPanel() {

	}

	@Override
	protected void setTblWidthAlign() {
		setColumnAlign(SwingConstants.CENTER, 0,1,2);
		setColumnWidth(100,150,200);
	}
	@Override
	protected String[] getColumns() {
		return new String[] {"고객이름","계좌번호","해지일자"};
	}
	@Override
	protected Object[] toArray(AccountInfo item) {
		return new Object[] {item.getCustName(),item.getAccountNum(),item.getTransDate()};
	}
	@Override
	protected void updateRow(AccountInfo item, int updateIdx) {
		// TODO Auto-generated method stub
	}
	@Override
	protected AccountInfo getSelectedItem() {
		// TODO Auto-generated method stub
		return null;
	}
}
