package kr.or.iei.member.model.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.iei.member.model.dao.MemberDao;
import kr.or.iei.member.model.dto.Member;

@Service
public class MemberService {
	
	@Autowired
	private MemberDao memberDao;

	public Member signIn(String memberEmail, String memberPassword) {
		
		
		
		Member member = memberDao.signIn(memberEmail,memberPassword);
		
		
		
		return member;
	}

	@Transactional
	public int signUp(Member member) {
		
		
		if(member.getMemberType()==2) {
			
			int memberStatus = 2;
			
			member.setMemberStatus(memberStatus);
		}else if(member.getMemberType()==1) {
			
			int memberStatus = 1 ;
			
			member.setMemberStatus(memberStatus);
			
		}
		
		
		int result = memberDao.signUp(member);
		
		
		return result;
		
	}

	public Member selectMember() {
		Member member = memberDao.selectMember();
		return member;
	}


	

}
