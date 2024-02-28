
package kr.or.iei.member.model.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.iei.admin.model.dto.Review;
import kr.or.iei.hospital.model.dto.Hospital;
import kr.or.iei.member.model.dao.MemberDao;
import kr.or.iei.member.model.dto.Member;
import kr.or.iei.reservation.model.dto.Reservation;

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
		
		System.out.println(member);
		
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
	
	//이메일 중복체크
	public int checkEmail(String memberEmail) {
		
		int cnt = memberDao.checkEmail(memberEmail);
		
		return cnt;
		
	}

	
	//회탈
	@Transactional
	public int confirmDelete(String memberPassword, String memberEmail) {
		int cnt = memberDao.confirmDelete(memberPassword, memberEmail);
		
		return cnt;
	}


	

	public int checkPassword(String memberPassword, String memberEmail) {
		int cnt = memberDao.checkPassword(memberPassword,memberEmail);
		
		return cnt;
	}
	//회원정보 수정
	@Transactional
	public int updateInfo(int memberNo, Member m) {
		
		int result = memberDao.updateInfo(memberNo,m);
		
		return result;
	}
	
	
	//내 자녀 추가
	@Transactional
	public int childAdd(Integer memberNo, String childName, String childRrn) {
		
		int result = memberDao.childAdd(memberNo,childName,childRrn);
		
		return result;

	}
		//내 자녀 보기
	public List selectMyChildInfo(int memberNo) {
		List child = memberDao.selectMyChildInfo(memberNo);
		return child;

	}

	public int deleteChild(int childNo, int memberNo) {
		
		
		int cnt = memberDao.deleteChild(childNo,memberNo);
		
		
		return cnt;
	}

	
	/*
	// 내 리뷰 작성
	public String getHospitalName(int rsNo) {
		
		
		String hospitalName = memberDao.getHospitalName(rsNo);
		
		
		return null;
	}
	
	*/

	
	public List reviewLsit(int memberNo) {
		
		List list = memberDao.reivewList(memberNo);
		
		
		return list;
	}

	public List<Hospital> hospitalTbl(int memberNo) {
		
		List<Hospital> hospital = memberDao.hospitalTbl(memberNo);
		
		
		return hospital;
	}

	public List<Reservation> reservation(int memberNo) {
		
		List<Reservation> reservation = memberDao.reservation(memberNo);
				
		
		return reservation;
	}
	
	@Transactional
	public int submit(int hospitalNo, int memberNo, Review review, int reservationNo) {
		
		int result = memberDao.submit(hospitalNo,memberNo,review,reservationNo);
		
		return result;
	}
	
	@Transactional
	public int reviewDel(int memberNo, int reviewNo) {
		
		
		int result = memberDao.reviewDel(memberNo,reviewNo);
		
		return result;
	}


	
	
	}
