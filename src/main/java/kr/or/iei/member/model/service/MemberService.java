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
	public MemberDao memberDao;

	public Member signIn(String memberEmail, String memberPassword) {
		
		
		
		Member member = memberDao.signIn(memberEmail,memberPassword);
		
		
		
		return member;
	}
	


	

}
