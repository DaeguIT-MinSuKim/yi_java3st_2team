package yi_java3st_2team.ui.table;

import java.text.SimpleDateFormat;

import javax.swing.SwingConstants;

import yi_java3st_2team.dto.Notice;
import yi_java3st_2team.ui.absPanel.AbsCenterTblPanel;

@SuppressWarnings("serial")
public class NoticeTblPanel extends AbsCenterTblPanel<Notice> {

	/**
	 * Create the panel.
	 */
	public NoticeTblPanel() {

	}

	@Override
	protected void setTblWidthAlign() {
		setColumnAlign(SwingConstants.CENTER, 0,1,2,3);
		setColumnWidth(100,300,150,300);
	}

	@Override
	protected String[] getColumns() {
		return new String[] {"번호","제목","작성자","작성날짜"};
	}

	@Override
	protected Object[] toArray(Notice item) {
		return new Object[] {item.getNo(),item.getSubject(),item.getWriter(),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.getWriteDate())};
	}

	@Override
	protected void updateRow(Notice item, int updateIdx) {
		model.setValueAt(item.getNo(), updateIdx, 0);
		model.setValueAt(item.getSubject(),updateIdx,1);
		model.setValueAt(item.getWriter(), updateIdx,2);
		model.setValueAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.getWriteDate()), updateIdx, 3);
	}

	@Override
	public Notice getSelectedItem() {
		int selIdx = getSelectedRowIdx();
		int no = (int)model.getValueAt(selIdx, 0);
		String subject = (String)model.getValueAt(selIdx, 1);
		String writer = (String)model.getValueAt(selIdx, 2);
		String content = (String)model.getValueAt(selIdx, 3);
		return null;
		
	}

}
