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
		String query = "SELECT  * FROM (SELECT ROWNUM AS RNUM, N.* FROM (SELECT NOTICE_NO, MEMBER_TBL.MEMBER_NO, NOTICE_TITLE, NOTICE_CONTENT, READ_COUNT, REQ_DATE, MEMBER_NAME FROM MEMBER_TBL RIGHT OUTER JOIN NOTICE_TBL ON MEMBER_TBL.MEMBER_NO = NOTICE_TBL.MEMBER_NO ORDER BY NOTICE_NO DESC)N ORDER BY RNUM DESC) WHERE RNUM BETWEEN ? AND ?";
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
		String query = "SELECT  * FROM (SELECT ROWNUM AS RNUM, N.* FROM (SELECT NOTICE_NO, MEMBER_TBL.MEMBER_NO, NOTICE_TITLE, NOTICE_CONTENT, READ_COUNT, REQ_DATE, MEMBER_NAME FROM MEMBER_TBL RIGHT OUTER JOIN NOTICE_TBL ON MEMBER_TBL.MEMBER_NO = NOTICE_TBL.MEMBER_NO where NOTICE_TITLE like '%'||?||'%' ORDER BY NOTICE_NO DESC)N ORDER BY 1 DESC) WHERE RNUM BETWEEN ? AND ?";
		Object[] params = {keyword, start, end};
		List list = jdbc.query(query, noticeRowMapper, params);
		return list;
	}

	public List selectSearchWriter(int start, int end, String keyword) {
		String query = "SELECT  * FROM (SELECT ROWNUM AS RNUM, N.* FROM (SELECT NOTICE_NO, MEMBER_TBL.MEMBER_NO, NOTICE_TITLE, NOTICE_CONTENT, READ_COUNT, REQ_DATE, MEMBER_NAME FROM MEMBER_TBL RIGHT OUTER JOIN NOTICE_TBL ON MEMBER_TBL.MEMBER_NO = NOTICE_TBL.MEMBER_NO where MEMBER_NAME like '%'||?||'%' ORDER BY NOTICE_NO DESC)N ORDER BY 1 DESC) WHERE RNUM BETWEEN ? AND ?";
		Object[] params = {keyword, start, end};
		List list = jdbc.query(query, noticeRowMapper, params);
		return list;
	}

	public int titleTotalCount(String keyword) {
		String query = "SELECT COUNT(*) FROM NOTICE_TBL WHERE NOTICE_TITLE LIKE '%'||?||'%'";
		Object[] params = {keyword};
		int totalCount = jdbc.queryForObject(query, Integer.class,params);
		return totalCount;
	}

	public int writerTotalCount(String keyword) {
		String query = "SELECT COUNT(*) FROM (SELECT NOTICE_NO, MEMBER_TBL.MEMBER_NO, NOTICE_TITLE, NOTICE_CONTENT, READ_COUNT, REQ_DATE, MEMBER_NAME FROM MEMBER_TBL RIGHT OUTER JOIN NOTICE_TBL ON MEMBER_TBL.MEMBER_NO = NOTICE_TBL.MEMBER_NO) WHERE MEMBER_NAME LIKE '%'||?||'%'";
		Object[] params = {keyword};
		int totalCount = jdbc.queryForObject(query, Integer.class,params);
		return totalCount;
	}
}
