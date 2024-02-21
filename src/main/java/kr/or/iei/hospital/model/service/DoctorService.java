package kr.or.iei.hospital.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.iei.hospital.model.dao.DoctorDao;
import kr.or.iei.hospital.model.dto.Doctor;

@Service
public class DoctorService {
	@Autowired
	private DoctorDao doctorDao;

}
