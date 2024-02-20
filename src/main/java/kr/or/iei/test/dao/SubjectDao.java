package kr.or.iei.test.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.test.dto.SubjectRowMapper;

@Repository
public class SubjectDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private SubjectRowMapper subjectRowMapper;
}
