package kr.or.iei.hospital.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.iei.hospital.model.dao.HospitalDao;
import kr.or.iei.hospital.model.dto.BusinessAuth;
import kr.or.iei.hospital.model.dto.BusinessAuthFile;
import kr.or.iei.hospital.model.dto.Doctor;
import kr.or.iei.hospital.model.dto.Hospital;
import kr.or.iei.hospital.model.dto.Subject;
import kr.or.iei.hospital.model.dto.Time;

@Service
public class HospitalService {

	@Autowired
	private HospitalDao hospitalDao;

	public List searchHospital(String keyword) {
		List hospitalList = hospitalDao.searchHospital(keyword);
		if(!hospitalList.isEmpty()) {
			for(Object o : hospitalList) {
				Hospital h = (Hospital) o;
				List subjectList = hospitalDao.searchSubjectList(h.getHospitalNo());
				List keywordList = hospitalDao.searchKeywordList(h.getHospitalNo());
				h.setSubjectList(subjectList);
				h.setKeywordList(keywordList);
			}
		}
		return hospitalList;
	}

	public Hospital searchHospitalDetail(int hospitalNo) {
		Hospital h = hospitalDao.searchHospitalDetail(hospitalNo);
		if(h != null) {
			List subjectList = hospitalDao.searchSubjectList(hospitalNo);
			List keywordList = hospitalDao.searchKeywordList(hospitalNo);
			Time time = hospitalDao.searchHospitalTime(hospitalNo);
			List doctorList = hospitalDao.searchSubjectDoctorList(hospitalNo);
			h.setSubjectList(subjectList);
			h.setKeywordList(keywordList);
			h.setTime(time);
			h.setDoctorList(doctorList);
		}
		return h;
	}

	@Transactional
	public int insertBusinessAuth(BusinessAuth ba, List<BusinessAuthFile> fileList) {
		// 1. BusinessAuth 테이블 insert
		// file은 insert안할 것이므로 ba만 보내면 됨
		int result = hospitalDao.insertBusinessAuth(ba); //DTO에 FileList 추가하여 통으로 전달
		if (result > 0) {
			//  insert 한  BusinessAuth 테이블의 데이터의 businessauth_no가 필요 -> 외래키 사용
			int businessAuthNo = hospitalDao.selectBusinessAuthNo();
			
			// 2. BusinessAuth_file테이블에 insert
			for (BusinessAuthFile businessAuthFile : fileList) {
				System.out.println(businessAuthFile);
				businessAuthFile.setBusinessAuthNo(businessAuthNo);
				result += hospitalDao.insertBusinessAuthFile(businessAuthFile);
			}
			
			result += hospitalDao.updateMemberStatus(ba.getMemberNo()); 
			
		}
		return result;
	}

	public List selectReviewList(int hospitalNo, int sortValue, int start, int amount) {
		int end = start+amount-1;
		if(sortValue == 1) {
			List reviewList = hospitalDao.selectReviewList(hospitalNo, sortValue, start, end);			
			return reviewList;
		}else {
			List reviewList = hospitalDao.selectReviewList2(hospitalNo, sortValue, start, end);
			return reviewList;
		}
	}
	
}










