package kr.or.iei.hospital.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.hospital.model.dto.Doctor;
import kr.or.iei.hospital.model.dto.DoctorRowMapper;

@Repository
public class DoctorDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private DoctorRowMapper doctorRowMapper;
	
}
