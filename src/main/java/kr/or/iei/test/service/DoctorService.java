package kr.or.iei.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.iei.test.dao.DoctorDao;
import kr.or.iei.test.dto.Doctor;

@Service
public class DoctorService {
	@Autowired
	private DoctorDao doctorDao;

}
