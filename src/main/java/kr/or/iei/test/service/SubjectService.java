package kr.or.iei.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.iei.hospital.model.dao.SubjectDao;

@Service
public class SubjectService {
	@Autowired
	private SubjectDao subjectDao;
}
