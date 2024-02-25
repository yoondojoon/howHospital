package kr.or.iei.member.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.member.model.dto.Member;
import kr.or.iei.member.model.dto.MemberRowMapper;

@Repository
public class MemberDao {
	
	
	@Autowired
	public JdbcTemplate jdbc;
	
	@Autowired
	public MemberRowMapper memberRowMapper;
	
	//로그인
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
	
	
	
}
