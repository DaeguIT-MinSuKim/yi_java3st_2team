package yi_java3st_2team.ui.table;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import yi_java3st_2team.dto.AccountInfo;
import yi_java3st_2team.ui.absPanel.AbsCenterTblPanel;

@SuppressWarnings({ "serial", "unused" })
public class DormantInfoTblPanel extends AbsCenterTblPanel<AccountInfo> {
	/**
	 * Create the panel.
	 */
	public DormantInfoTblPanel() {

	}

	@Override
	protected void setTblWidthAlign() {
		setColumnAlign(SwingConstants.CENTER, 0,1,2);
		setColumnWidth(100,150,200);
	}

	@Override
	protected String[] getColumns() {
		return new String[] {"고객이름","계좌번호","휴면전환일자"};
	}
	@Override
	protected Object[] toArray(AccountInfo item) {
		return new Object[] {item.getCustname(),item.getWorkNum(),item.getTransDate()};
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
