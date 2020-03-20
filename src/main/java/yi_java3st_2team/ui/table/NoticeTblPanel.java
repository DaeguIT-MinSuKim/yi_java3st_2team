package yi_java3st_2team.ui.table;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
		setColumnAlign(SwingConstants.CENTER, 0,1,2,3,4);
		setColumnWidth(100,300,80,150,300);
	}

	@Override
	protected String[] getColumns() {
		return new String[] {"번호","제목","작성자","작성내용","작성날짜"};
	}

	@Override
	protected Object[] toArray(Notice item) {
		return new Object[] {item.getNo(),item.getSubject(),item.getWriter(),item.getContent(),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.getWriteDate())};
	}

	@Override
	public void updateRow(Notice item, int updateIdx) {
		model.setValueAt(updateIdx+1, updateIdx, 0);
		model.setValueAt(item.getSubject(),updateIdx,1);
		model.setValueAt(item.getWriter(), updateIdx,2);
		model.setValueAt(item.getContent(),updateIdx,3);
		model.setValueAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.getWriteDate()), updateIdx, 4);
	}

	@Override
	public Notice getSelectedItem() {
		int selIdx = getSelectedRowIdx();
		int no = (int)model.getValueAt(selIdx, 0);
		String subject = (String)model.getValueAt(selIdx, 1);
		String writer = (String)model.getValueAt(selIdx, 2);
		String content = (String)model.getValueAt(selIdx, 3);
		Date writeDate = null;
		try {
			writeDate = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").parse((String)model.getValueAt(selIdx, 4));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Notice(no, subject, writer, writeDate, content);
	}

}
