package kr.or.iei.hospital.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.iei.hospital.model.dao.HospitalDao;
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
			List doctorList = hospitalDao.searchDoctorList(hospitalNo);
			List reviewList = hospitalDao.searchReviewList(hospitalNo);
			h.setSubjectList(subjectList);
			h.setKeywordList(keywordList);
			h.setTime(time);
			h.setDoctorList(doctorList);
			h.setReviewList(reviewList);
		}
		return h;
	}
	
}
