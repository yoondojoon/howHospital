package kr.or.iei.reservation.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.iei.hospital.model.dao.SubjectDao;

@Service
public class SubjectService {
	@Autowired
	private SubjectDao subjectDao;
}
