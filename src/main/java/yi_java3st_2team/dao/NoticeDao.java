package yi_java3st_2team.dao;

import java.sql.SQLException;
import java.util.List;

import yi_java3st_2team.dto.Notice;

public interface NoticeDao {
	public List<Notice> selectNoticeByAll() throws SQLException;
	public Notice selectNoticeByNo(Notice notice) throws SQLException;
	public int insertNotice(Notice notice) throws SQLException;
	public int updateNotice(Notice notice) throws SQLException;
	public int deleteNotice(Notice notice) throws SQLException;
	public int resetAutoIncrement() throws SQLException;
}
