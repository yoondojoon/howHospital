package kr.or.iei.hospital.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class TimeRowMapper implements RowMapper<Time> {

	@Override
	public Time mapRow(ResultSet rs, int rowNum) throws SQLException {
		Time time = new Time();
		time.setHospitalNo(rs.getInt("hospital_no"));
		time.setDayHour(rs.getString("day_hour"));
		time.setWeekendHour(rs.getString("weekend_hour"));
		time.setLunchHour(rs.getString("lunch_hour"));
		time.setHoliday(rs.getInt("holiday"));
		return time;
	}

}
