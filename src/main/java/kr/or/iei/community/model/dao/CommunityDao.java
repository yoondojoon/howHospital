package kr.or.iei.community.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.community.model.dto.CommunityRowMapper;

@Repository
public class CommunityDao {
	@Autowired
	private JdbcTemplate jdbc;
	
	@Autowired
	private CommunityRowMapper communityRowMapper;

	public List selectCommunityList(String keyword, int start, int end) {
		String query = "select * from (select rownum rnum, c2.* from (select board_no, board_title, board_content, read_count, (select replace(member_name,substr(member_name,2,1),'*') from member_tbl where member_no=c.member_no) board_writer, write_date from community_tbl c where board_title||board_content like '%'||?||'%' order by 1 desc)c2) where rnum between ? and ?";
		Object[] params = {keyword, start, end};
		List list = jdbc.query(query, communityRowMapper, params);
		return list;
	}

	public int selectTotalCount() {
		String query = "select count(*) from community_tbl";
		int totalCount = jdbc.queryForObject(query, Integer.class);
		return totalCount;
	}
}
