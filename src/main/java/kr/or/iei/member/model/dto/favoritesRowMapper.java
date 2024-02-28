package kr.or.iei.member.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class favoritesRowMapper implements RowMapper<favorites>{
	
	
	@Override
	public favorites mapRow(ResultSet rs, int rowNum) throws SQLException{
		
		
		
		favorites favorites = new favorites();
		
		favorites.setFavoritesNo(rs.getInt("favorite_no"));
		favorites.setHospitalNo(rs.getInt("hospital_no"));
		favorites.setMemberNo(rs.getInt("member_no"));
		
		
		return favorites;
		
	}
	

}
