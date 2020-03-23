package yi_java3st_2team.ui.table;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import yi_java3st_2team.dto.AccountInfo;
import yi_java3st_2team.ui.absPanel.AbsCenterTblPanel;
import yi_java3st_2team.ui.service.BankBookService;

@SuppressWarnings({ "serial", "unused" })
public class DormantInfoTblPanel extends AbsCenterTblPanel<AccountInfo> {
	private BankBookService service;
	public DormantInfoTblPanel() {
		service = new BankBookService();
		try {
			List<AccountInfo> list = service.showDormantAccountInfo();
			if(list.isEmpty()) {
				JOptionPane.showMessageDialog(null, "해지 계좌가 존재하지 않습니다");
			}
			else {
				loadTableData(list);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
