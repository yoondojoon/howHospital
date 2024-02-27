package kr.or.iei.admin.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.admin.model.dto.AdminBusinessAuth;
import kr.or.iei.admin.model.dto.AdminBusinessAuthRowMapper;
import kr.or.iei.admin.model.dto.Faq;
import kr.or.iei.admin.model.dto.FaqRowMapper;
import kr.or.iei.admin.model.dto.HospitalReport;
import kr.or.iei.admin.model.dto.HospitalReportRowMapper;
import kr.or.iei.admin.model.dto.MemberReport;
import kr.or.iei.admin.model.dto.MemberReportRowMapper;
import kr.or.iei.admin.model.dto.Notice;
import kr.or.iei.admin.model.dto.NoticeRowMapper;
import kr.or.iei.admin.model.dto.Review;
import kr.or.iei.admin.model.dto.ReviewRowMapper;
import kr.or.iei.hospital.model.dto.BusinessAuth;
import kr.or.iei.hospital.model.dto.BusinessAuthFileRowMapper;
import kr.or.iei.hospital.model.dto.BusinessAuthRowMapper;
import kr.or.iei.member.model.dto.Member;
import kr.or.iei.member.model.dto.MemberRowMapper;

@Repository
public class AdminDao {
	@Autowired
	private JdbcTemplate jdbc;
	
	@Autowired
	private BusinessAuthRowMapper baRowMapper;
	
	@Autowired
	private MemberRowMapper mRowMapper;
	
	@Autowired
	private NoticeRowMapper noticeRowMapper;
	
	@Autowired
	private MemberReportRowMapper memberReportRowMapper;
	
	@Autowired
	private AdminBusinessAuthRowMapper abaRowMapper;
	
	@Autowired
	private BusinessAuthFileRowMapper bafRowMapper;
	
	@Autowired
	private FaqRowMapper faqRowMapper;
	
	@Autowired
	private ReviewRowMapper reviewRowMapper;
	
	@Autowired
	private HospitalReportRowMapper hrRowMapper;

	public List selectAllNotice(int start, int end) {
		String query = "SELECT  * FROM (SELECT ROWNUM AS RNUM, N.* FROM (SELECT NOTICE_NO, MEMBER_TBL.MEMBER_NO, NOTICE_TITLE, NOTICE_CONTENT, READ_COUNT, REQ_DATE, MEMBER_NAME FROM MEMBER_TBL RIGHT OUTER JOIN NOTICE_TBL ON MEMBER_TBL.MEMBER_NO = NOTICE_TBL.MEMBER_NO ORDER BY NOTICE_NO DESC)N) WHERE RNUM BETWEEN ? AND ?";
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
		String query = "SELECT  * FROM (SELECT ROWNUM AS RNUM, N.* FROM (SELECT NOTICE_NO, MEMBER_TBL.MEMBER_NO, NOTICE_TITLE, NOTICE_CONTENT, READ_COUNT, REQ_DATE, MEMBER_NAME FROM MEMBER_TBL RIGHT OUTER JOIN NOTICE_TBL ON MEMBER_TBL.MEMBER_NO = NOTICE_TBL.MEMBER_NO where NOTICE_TITLE like '%'||?||'%' ORDER BY NOTICE_NO DESC)N) WHERE RNUM BETWEEN ? AND ?";
		Object[] params = {keyword, start, end};
		List list = jdbc.query(query, noticeRowMapper, params);
		return list;
	}

	public List selectSearchWriter(int start, int end, String keyword) {
		String query = "SELECT  * FROM (SELECT ROWNUM AS RNUM, N.* FROM (SELECT NOTICE_NO, MEMBER_TBL.MEMBER_NO, NOTICE_TITLE, NOTICE_CONTENT, READ_COUNT, REQ_DATE, MEMBER_NAME FROM MEMBER_TBL RIGHT OUTER JOIN NOTICE_TBL ON MEMBER_TBL.MEMBER_NO = NOTICE_TBL.MEMBER_NO where MEMBER_NAME like '%'||?||'%' ORDER BY NOTICE_NO DESC)N) WHERE RNUM BETWEEN ? AND ?";
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

	public int insertNotice(Notice n) {
		String query = "insert into notice_tbl values (notice_seq.nextval,?,?,?,0,to_char(sysdate,'yyyy-mm-dd'))";
		Object[] params = {n.getMemberNo(), n.getNoticeTitle(),n.getNoticeContent()};
		int result = jdbc.update(query,params);
		return result;
	}

	public Notice searchNoticeDetail(int noticeNo) {
		String query = "SELECT NOTICE_NO, MEMBER_TBL.MEMBER_NO, NOTICE_TITLE, NOTICE_CONTENT, READ_COUNT, REQ_DATE, MEMBER_NAME FROM MEMBER_TBL RIGHT OUTER JOIN NOTICE_TBL ON MEMBER_TBL.MEMBER_NO = NOTICE_TBL.MEMBER_NO where notice_no=?";
		Object[] params = {noticeNo};
		Notice n = jdbc.queryForObject(query, noticeRowMapper, params);
		return n;
	}

	public int deleteNotice(int noticeNo) {
		String query = "delete from notice_tbl where notice_no =?";
		Object[] params = {noticeNo};
		int result = jdbc.update(query,params);
		return result;
	}

	public List selectAllMemberReport(int start, int end) {
		String query = "SELECT  * FROM (SELECT ROWNUM AS RNUM, N.* FROM (SELECT report_NO, MEMBER_TBL.MEMBER_NO, board_no, report_title, report_content, report_status, MEMBER_NAME FROM MEMBER_TBL RIGHT OUTER JOIN Member_Report_TBL ON MEMBER_TBL.MEMBER_NO = Member_Report_TBL.MEMBER_NO ORDER BY Report_NO DESC)N) WHERE RNUM BETWEEN ? AND ?";
		Object[] params = {start, end};
		List list = jdbc.query(query, memberReportRowMapper, params);
		return list;
	}

	public int selectAllMemberReportCount() {
		String query = "select count(*) from Member_report_tbl";
		int totalCount = jdbc.queryForObject(query, Integer.class);
		return totalCount;
	}

	public int deleteChk(int reportNo) {
		String query = "delete from member_report_tbl where report_no = ?";
		Object[] params = {reportNo};
		int result = jdbc.update(query,params);
		return result;
	}

	public MemberReport searchReportDetail(int reportNo) {
		String query = "SELECT report_NO, MEMBER_TBL.MEMBER_NO, board_no, report_title, report_content, report_status, MEMBER_NAME FROM MEMBER_TBL RIGHT OUTER JOIN Member_Report_TBL ON MEMBER_TBL.MEMBER_NO = Member_Report_TBL.MEMBER_NO WHERE report_no= ?";
		Object[] params = {reportNo};
		MemberReport mr = jdbc.queryForObject(query, memberReportRowMapper,params);
		return mr;
	}

	public int deleteReport(int reportNo) {
		String query = "delete from member_report_tbl where report_no=?";
		Object[] params = {reportNo};
		int result = jdbc.update(query,params);
		return result;
	}

	public int confirmReport(int reportNo) {
		String query = "update member_report_tbl set report_status = 1 where report_no=?";
		Object[] params = {reportNo};
		int result = jdbc.update(query,params);
		return result;
	}

	public List selectAllBusinessAuth(int start, int end) {
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, N.* FROM(SELECT MEMBER_NAME, BUSINESSAUTH_NO, MEMBER_EMAIL, MEMBER_PHONE, REPRESENTATIVE_NO, REG_DATE FROM MEMBER_TBL RIGHT OUTER JOIN BUSINESSAUTH_TBL ON MEMBER_TBL.MEMBER_NO = BUSINESSAUTH_TBL.MEMBER_NO WHERE MEMBER_STATUS = 4 ORDER BY BUSINESSAUTH_NO DESC)N) WHERE RNUM BETWEEN ? AND ?";
		Object[] params = {start,end};
		List list = jdbc.query(query, abaRowMapper, params);
		return list;
	}

	public int selectAllBusinessAuthCount() {
		String query = "SELECT COUNT(*) FROM (SELECT ROWNUM AS RNUM, N.* FROM(SELECT MEMBER_NAME, BUSINESSAUTH_NO, MEMBER_EMAIL, MEMBER_PHONE, REPRESENTATIVE_NO, REG_DATE FROM MEMBER_TBL RIGHT OUTER JOIN BUSINESSAUTH_TBL ON MEMBER_TBL.MEMBER_NO = BUSINESSAUTH_TBL.MEMBER_NO WHERE MEMBER_STATUS = 4 ORDER BY BUSINESSAUTH_NO DESC)N)";
		int totalCount = jdbc.queryForObject(query, Integer.class);
		return totalCount;
	}

	public AdminBusinessAuth confirmAuth(int businessAuthNo) {
		String query = "SELECT * FROM MEMBER_TBL RIGHT OUTER JOIN BUSINESSAUTH_TBL ON MEMBER_TBL.MEMBER_NO = BUSINESSAUTH_TBL.MEMBER_NO WHERE MEMBER_STATUS = 4 AND BUSINESSAUTH_NO=?";
		Object[] params = {businessAuthNo};
		AdminBusinessAuth aba = jdbc.queryForObject(query, abaRowMapper,params);
		return aba;
	}

	public List confirmAuthFile(int businessAuthNo) {
		String query = "select * from businessAuth_file_tbl where businessAuth_no=?";
		Object[] params = {businessAuthNo};
		List fileList = jdbc.query(query, bafRowMapper,params);
		return fileList;
	}
	
	public BusinessAuth searchMember(int businessAuthNo) {
			String query = "select * from businessAuth_tbl where businessAuth_no=?";
			Object[] params = {businessAuthNo};
			BusinessAuth ba = jdbc.queryForObject(query, baRowMapper, params);
			return ba;
		}
	
	public int authConfirmSuccess(BusinessAuth ba) {
		String query = "update member_tbl set member_status=1 where member_no = ?";
		Object[] params = {ba.getMemberNo()};
		int result = jdbc.update(query,params);
		return result;
	}

	public int deleteAuthInfo(int businessAuthNo) {
		String query = "delete from businessAuth_tbl where businessAuth_no = ?";
		Object[] params = {businessAuthNo};
		int result = jdbc.update(query, params);
		return result;
	}

	public List<Faq> selectCategory() {
		String query="select * from faq_tbl where faq_ref is null";
		List list = jdbc.query(query, faqRowMapper);
		return list;
	}

	public List<Faq> selectContentList() {
		String query="select * from faq_tbl where faq_ref is not null";
		List list = jdbc.query(query, faqRowMapper);
		return list;
	}

	public List<Review> selectAllReview() {
		String query = "select * from member_tbl join review_tbl on member_tbl.member_no = review_tbl.member_no";
		List list = jdbc.query(query, reviewRowMapper);
		return list;
	}

	public int deleteReview(int reviewNo) {
		String query = "delete from review_tbl where review_no=?";
		Object[] params = {reviewNo};
		int result = jdbc.update(query,params);
		return result;
	}

	public List<Member> selectAllMember() {
		String query = "select * from member_tbl";
		List<Member> list = jdbc.query(query, mRowMapper);
		return list;
	}

	public int unBlock(int memberNo) {
		String query = "update member_tbl set member_status = 1 where member_no=?";
		Object[] params = {memberNo};
		int result = jdbc.update(query,params);
		return result;
	}

	public int block(int memberNo) {
		String query = "update member_tbl set member_status = 3 where member_no=?";
		Object[] params = {memberNo};
		int result = jdbc.update(query,params);
		return result;
	}

	public List selectAllHospitalReport(int start, int end) {
		String query ="SELECT * FROM (SELECT ROWNUM AS RNUM, N.* FROM(SELECT REPORT_NO, REPORT_TITLE, REPORT_CONTENT,REPORT_STATUS,J_TBL.MEMBER_NO, HOSPITAL_NAME FROM(SELECT * FROM HOSPITAL_REPORT_TBL JOIN RESERVATION_TBL ON HOSPITAL_REPORT_TBL.RESERVATION_NO = RESERVATION_TBL.RESERVATION_NO) J_TBL JOIN HOSPITAL_TBL ON J_TBL.MEMBER_NO = HOSPITAL_TBL.MEMBER_NO ORDER BY REPORT_NO DESC)N) WHERE RNUM BETWEEN ? AND ?";
		Object[] params = {start, end};
		List list = jdbc.query(query, hrRowMapper, params);
		return list;
	}

	public int selectAllHospitalReportCount() {
		String query="select count(*) from hospital_report_tbl";
		int result = jdbc.queryForObject(query, Integer.class);
		return result;
	}

	public HospitalReport selectOneHospitalReport(int reportNo) {
		String query = "SELECT REPORT_NO, REPORT_TITLE, REPORT_CONTENT,REPORT_STATUS,J_TBL.MEMBER_NO, HOSPITAL_NAME FROM(SELECT * FROM HOSPITAL_REPORT_TBL JOIN RESERVATION_TBL ON HOSPITAL_REPORT_TBL.RESERVATION_NO = RESERVATION_TBL.RESERVATION_NO) J_TBL JOIN HOSPITAL_TBL ON J_TBL.MEMBER_NO = HOSPITAL_TBL.MEMBER_NO WHERE REPORT_NO = ?";
		Object[] params = {reportNo};
		HospitalReport hr = jdbc.queryForObject(query, hrRowMapper, params);
		System.out.println(hr);
		return hr;
	}

	public int deleteHospitalReport(int reportNo) {
		String query = "delete from hospital_report_tbl where report_no=?";
		Object[] params = {reportNo};
		int result = jdbc.update(query,params);
		return result;
	}

	public int confirmHospitalReport(int reportNo) {
		String query = "update hospital_report_tbl set report_status = 1 where report_no=?";
		Object[] params = {reportNo};
		int result = jdbc.update(query,params);
		return result;
	}

	
}
