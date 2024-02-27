package kr.or.iei.community.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.community.model.dto.Community;
import kr.or.iei.community.model.dto.CommunityFile;
import kr.or.iei.community.model.dto.CommunityFileRowMapper;
import kr.or.iei.community.model.dto.CommunityRowMapper;

@Repository
public class CommunityDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private CommunityRowMapper communityRowMapper;
	@Autowired
	private CommunityFileRowMapper communityFileRowMapper;

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

	public int insertCommunityBoard(Community c) {
		String query = "insert into community_tbl values(board_seq.nextval,?,?,?,0,to_char(sysdate,'yyyy-mm-dd'))";
		Object[] params = {c.getMemberNo(), c.getBoardTitle(), c.getBoardContent()};
		int result = jdbc.update(query,params);
		return result;
	}

	public int selectCommunityBoardNo() {
		String query = "select max(board_no) board_no from community_tbl";
		int boardNo = jdbc.queryForObject(query, Integer.class); 
		return boardNo;
	}

	public int insertCommunityFile(CommunityFile communityFile) {
		String query = "insert into community_file values(file_seq.nextval,?,?,?)";
		Object[] params = {communityFile.getBoardNo(), communityFile.getFilename(), communityFile.getFilepath()};
		int result = jdbc.update(query,params);
		return result;
	}

	public int updateReadCount(int boardNo) {
		String query = "update community_tbl set read_count=read_count+1 where board_no=?";
		Object[] params = {boardNo};
		int result = jdbc.update(query,params);
		return result;
	}

	public Community selectOneCommunityBoard(int boardNo) {
		String query = "select board_no, member_no, board_title, board_content, read_count, (select replace(member_name,substr(member_name,2,1),'*') from member_tbl where member_no=c.member_no) board_writer, write_date from community_tbl c where board_no=?";
		Object[] params = {boardNo};
		List list = jdbc.query(query, communityRowMapper, params);
		if(list.isEmpty()) {
			return null;
		}else {
			return (Community)list.get(0);
		}
	}

	public List selectCommunityFile(int boardNo) {
		String query = "select * from community_file where board_no=?";
		Object[] params = {boardNo};
		List list = jdbc.query(query, communityFileRowMapper, params);
		return list;
	}

	public int deleteCommunityBoard(int boardNo) {
		String query = "delete from community_tbl where board_no=?";
		Object[] params = {boardNo};
		int result = jdbc.update(query,params);
		return result;
	}

	public int updateCommunityBoard(Community c) {
		String query = "update community_tbl set board_title=?, board_content=? where board_no=?";
		Object[] params = {c.getBoardTitle(), c.getBoardContent(), c.getBoardNo()};
		int result = jdbc.update(query,params);
		return result;
	}

	public CommunityFile selectOneBoardFile(int fileNo) {
		String query = "select * from community_file where file_no=?";
		Object[] params = {fileNo};
		List list = jdbc.query(query, communityFileRowMapper, params);
		return (CommunityFile)list.get(0);
	}

	public int deleteCommunityBoardFile(int fileNo) {
		String query = "delete from community_file where file_no=?";
		Object[] params = {fileNo};
		int result = jdbc.update(query,params);
		return result;
	}

}
