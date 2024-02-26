package kr.or.iei.member.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.member.model.dto.Child;
import kr.or.iei.member.model.dto.ChildRowMapper;
import kr.or.iei.member.model.dto.Member;
import kr.or.iei.member.model.dto.MemberRowMapper;

@Repository
public class MemberDao {
	
	
	@Autowired
	public JdbcTemplate jdbc;
	
	@Autowired
	public MemberRowMapper memberRowMapper;
	

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
	
	
	
}
