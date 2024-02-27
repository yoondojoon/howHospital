package kr.or.iei.member.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.hospital.model.dto.Hospital;
import kr.or.iei.hospital.model.dto.HospitalRowMapper;
import kr.or.iei.member.model.dto.Child;
import kr.or.iei.member.model.dto.ChildRowMapper;
import kr.or.iei.member.model.dto.Member;
import kr.or.iei.member.model.dto.MemberRowMapper;
import kr.or.iei.member.model.dto.MyReviewRowMapper;

@Repository
public class MemberDao {
	
	
	@Autowired
	public JdbcTemplate jdbc;
	
	@Autowired
	public MemberRowMapper memberRowMapper;
	
	@Autowired
	public MyReviewRowMapper myReviewRowMapper;
	
	
	@Autowired
	public HospitalRowMapper hospitalRowMapper;

	//로그인

	@Autowired
	public ChildRowMapper childRowMapper;

	public Member signIn(String memberEmail, String memberPassword) {
		
		
		String query = "select * from member_tbl where member_email =? and member_password=?";
		
		Object[] params = {memberEmail,memberPassword};
		
		List list = jdbc.query(query, memberRowMapper, params);
			
		if(list.isEmpty()) {
			
			return null; 
		
		}else {
		
			return (Member)list.get(0);
			
		}
	}
	
	//회원가입
	public int signUp(Member member) {
		
		String query = "insert into member_tbl values(member_seq.nextval,?,?,?,?,?,?,?,to_char(sysdate,'yyyy-mm-dd'),?,?)";
		
		Object[] params = {member.getMemberType(),member.getMemberEmail(),member.getMemberPassword(),member.getMemberName(),member.getMemberPhone(),member.getMemberRrn(),member.getMemberAddress(),member.getMemberStatus(),member.getReportCount()};
		
		int result = jdbc.update(query,params);
		
		
		return result;
		
	}
	
	//이메일 중복체크
	public int checkEmail(String memberEmail) {
		
		
		String query = "select count(*) from member_tbl where member_email = ?";
		
		int cnt = jdbc.queryForObject(query, Integer.class, memberEmail);
		
		return cnt;
		
		
	}

	
	//회원탈퇴
	public int confirmDelete(String memberEmail,String memberPassword) {
		
		String query = "delete from member_tbl where member_email=? and member_password=?";
		
		Object[] params = {memberEmail,memberPassword};
		
		
		int cnt = jdbc.update(query,params);
		
		
		return cnt;
	}


	//비밀번호 중복 체크
	public int checkPassword(String memberPassword, String memberEmail) {
		
		
		String query = "select count(*) from member_tbl where member_email = ? and member_password=?";
		
		int cnt = jdbc.queryForObject(query, Integer.class,memberEmail,memberPassword);
		
		return cnt;
	}

	public int updateInfo(Member m) {
		
		String query = "update member_tbl set member_address=?, member_phone=?, member_password=? where member_no =?";
		
		Object[] params = {m.getMemberAddress(), m.getMemberPhone(), m.getMemberPassword(),m.getMemberNo()};
		
		int result = jdbc.update(query,params);
		
		
		return result;
	}

	public int childAdd(Integer memberNo, String childName, String childRrn) {
		
		String query = "insert into child_tbl values(child_seq.nextval, ?, ?, ?)";
		
		Object[] params = {memberNo,childName,childRrn};
		
		int result = jdbc.update(query,params);
		
		
		
		return result;
		
	}
	//내 자녀 보기
	public List selectMyChildInfo(int memberNo) {
		String query = "select * from child_tbl where member_no=?";
		Object[] params = {memberNo};
		List child = jdbc.query(query, childRowMapper, params);
		return child;

	}

	public int deleteChild(int childNo) {
		
		
		String query = "delete from child_tbl where child_no =?";
		
		Object[] params = {childNo};
		
		int cnt = jdbc.update(query, params);
		

		
		return cnt;
	}

	
	
	//내리뷰 보기
	public List reivewList(int memberNo) {
		
		String query = "select * from review_tbl where member_no=?";
		
		Object[] params = {memberNo};
		
		List list = jdbc.query(query, myReviewRowMapper,params);
		
		
		return list;
	}

	public List<Hospital> hospitalTbl(int memberNo) {
		
		String query = "select hospital_tbl.hospital_name " + 
				"from review_tbl " + 
				"inner join hospital_tbl ON review_tbl.hospital_no = hospital_tbl.hospital_no " + 
				"where review_tbl.member_no = ?";
		
		
 		Object[] params = {memberNo};
		
		List<Hospital> hospital = jdbc.query(query, hospitalRowMapper,params);
		
		
		
		
		
		return hospital;
	}

	/*
	public String getHospitalName(int rsNo) {
		
		String query = "select hospital_tbl.hospital_name from hospital_tbl "
				+ "		inner join reservation_tbl "
				+ "		on hospital_tbl.hospital_no = reservation_tbl.hospital_no "
				+ "		where reservation_tbl.reservation_no = ? and reservation_tbl.reservation_status = 4";
		
		Object[] params = {rsNo};
		
		
		
		
		return hospitalName;
	}
	
	
	*/
}
