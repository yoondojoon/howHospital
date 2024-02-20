package kr.or.iei.test.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.test.dto.HospitalRowMapper;

@Repository
public class HospitalDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private HospitalRowMapper hospitalRowMapper;
}
