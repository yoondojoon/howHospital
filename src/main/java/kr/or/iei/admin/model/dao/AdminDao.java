package kr.or.iei.admin.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.admin.model.dto.NoticeListData;
import kr.or.iei.admin.model.dto.NoticeRowMapper;

@Repository
public class AdminDao {
	@Autowired
	private JdbcTemplate jdbc;
	
	@Autowired
	private NoticeRowMapper noticeRowMapper;

	public List selectAllNotice(int start, int end) {
		String query = "SELECT  * FROM (SELECT ROWNUM AS RNUM, N.* FROM (SELECT * FROM notice_tbl ORDER BY 1 DESC)N) WHERE RNUM BETWEEN ? AND ?";
		Object[] params = {start, end};
		List list = jdbc.query(query, noticeRowMapper, params);
		return list;
	}

	public int selectAllBoardCount() {
		String query = "select count(*) from notice_tbl";
		int totalCount = jdbc.queryForObject(query, Integer.class);
		return totalCount;
	}

	public List selectSearchTitle(int start, int end, String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	public List selectSearchWriter(int start, int end, String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	public int titleTotalCount(String keyword) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int writerTotalCount(String keyword) {
		// TODO Auto-generated method stub
		return 0;
	}
}
