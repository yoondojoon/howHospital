package kr.or.iei.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.iei.test.dao.HospitalDao;

@Service
public class HospitalService {
	@Autowired
	private HospitalDao hospitalDao;
}
