package yi_java3st_2team.ui.service;

import java.sql.SQLException;
import java.util.List;

import yi_java3st_2team.dao.NoticeDao;
import yi_java3st_2team.dao.impl.NoticeDaoImpl;
import yi_java3st_2team.dto.Notice;

public class NoticeService {
	private NoticeDao dao;

	public NoticeService() {
		dao = NoticeDaoImpl.getInstance();
	}
	public List<Notice> showNoticeByAll() throws SQLException {
		return dao.selectNoticeByAll();
	}
	public Notice showNoticeByNo(Notice notice) throws SQLException {
		return dao.selectNoticeByNo(notice);
	}
	public int addNotice(Notice notice) throws SQLException {
		return dao.insertNotice(notice);
	}
	public int modifyNotice(Notice notice) throws SQLException {
		return dao.updateNotice(notice);
	}
	public int removeNotice(Notice notice) throws SQLException {
		return dao.deleteNotice(notice);
	}
	
}
