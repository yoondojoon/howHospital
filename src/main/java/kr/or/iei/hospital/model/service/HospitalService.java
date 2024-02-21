package kr.or.iei.hospital.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.iei.hospital.model.dao.HospitalDao;
import kr.or.iei.hospital.model.dto.BusinessAuth;
import kr.or.iei.hospital.model.dto.Hospital;
import kr.or.iei.hospital.model.dto.Subject;

@Service
public class HospitalService {

	@Autowired
	private HospitalDao hospitalDao;

	public List searchHospital(String keyword) {
		List hospitalList = hospitalDao.searchHospital(keyword);
		if(!hospitalList.isEmpty()) {
			for(Object o : hospitalList) {
				Hospital h = (Hospital) o;
				List subjectList = hospitalDao.searchSubjectHospital(h.getHospitalNo());
				List keywordList = hospitalDao.searchKeywordHospital(h.getHospitalNo());
				h.setSubjectList(subjectList);
				h.setKeywordList(keywordList);
			}
		}
		return hospitalList;
	}

//	public int insertBusinessAuth(BusinessAuth ba, int memberNo) {
//		int result = hospitalDao.insertBusinessAuth(ba, memberNo);
//		
//		return 0;
//	}
//	
}
