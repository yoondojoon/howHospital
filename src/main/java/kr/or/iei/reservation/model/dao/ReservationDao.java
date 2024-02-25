package kr.or.iei.reservation.model.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.or.iei.hospital.model.dto.DoctorRowMapper;
import kr.or.iei.reservation.model.dto.H_ReservationRowMapper;
import kr.or.iei.reservation.model.dto.Reservation;
import kr.or.iei.reservation.model.dto.ReservationDetail;
import kr.or.iei.reservation.model.dto.ReservationRowMapper;

@Repository
public class ReservationDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private H_ReservationRowMapper h_ReservationRowMapper;
	@Autowired
	private DoctorRowMapper doctorRowMapper;
	public List selectReservation(int startPage, int endPage,int memberNo) {
		String query = 
				"select * from\r\n" + 
				"            (select rownum as rnum, res.* from\r\n" + 
				"                (select reservation_no,reservation_status,reg_reservation,RESERVATION_TIME,reservation_type,(select member_name from member_tbl where member_no=r.member_no) as member_name,\r\n" + 
				"                (select DOCTOR_NAME from DOCTOR_TBL where doctor_no = (select doctor_no from reservation_detail_tbl where reservation_no=r.reservation_no)) as doctor_name,\r\n" + 
				"                (select count(*) from prescription_tbl where reservation_no = r.reservation_no) as prescription_status\r\n" + 
				"FROM \r\n" + 
				"reservation_tbl r \r\n" + 
				"where hospital_no in (select hospital_no from hospital_tbl where member_no= ?)\r\n" + 
				"ORDER BY 1 DESC)RES) WHERE Rnum BETWEEN ? AND ?";
		Object[] params = {memberNo,startPage, endPage};
		List list = jdbc.query(query, h_ReservationRowMapper, params);
		return list;
	}

	public int updateReservation(int selectValue, int reservationNo) {
		String query = "update reservation_tbl set reservation_status=? where reservation_no=?";
		Object[] params = {selectValue, reservationNo};
		int result = jdbc.update(query, params);
		return result;
	}

	public int selectAllReservationCount() {
		String query = "select count(*) from reservation_tbl";
		int totalCount = jdbc.queryForObject(query, Integer.class);
		return totalCount;
	}

	public int getTotalReservation(int memberNo) {
		String query = "select count(*) from reservation_tbl r \r\n" + 
				"where hospital_no in (select hospital_no from hospital_tbl where member_no=?)";
		Object[] params = {memberNo};
		int totalReservation = jdbc.queryForObject(query, Integer.class, params);
		return totalReservation;
	}

	public int getDoctorReservation(int memberNo, int doctorNo) {
		String query = "select count(*) from reservation_tbl r \r\n" + 
				"where hospital_no in (select hospital_no from hospital_tbl where member_no=?)\r\n" + 
				"and\r\n" + 
				"(select DOCTOR_NO from DOCTOR_TBL where doctor_no = (select doctor_no from reservation_detail_tbl where reservation_no=r.reservation_no)) = ?";
		Object[] params = {memberNo,doctorNo};
		int doctorReservation = jdbc.queryForObject(query, Integer.class,params);
		return doctorReservation;
	}

	public List selectDoctorInfo(int memberNo) {
		String query = "select * from doctor_tbl where hospital_no in (select hospital_no from hospital_tbl where member_no=?)";
		Object[] params = {memberNo};
		List list = jdbc.query(query,doctorRowMapper,params);
		return list;
	}

	public List selectDoctorReservation(int startPage, int endPage, int memberNo, int doctorNo) {
		String query = "select * from\r\n" + 
				"            (select rownum as rnum, res.* from\r\n" + 
				"                (select reservation_no,reservation_status,reg_reservation,RESERVATION_TIME,reservation_type,(select member_name from member_tbl where member_no=r.member_no) as member_name,\r\n" + 
				"                (select DOCTOR_NAME from DOCTOR_TBL where doctor_no = (select doctor_no from reservation_detail_tbl where reservation_no=r.reservation_no)) as doctor_name,\r\n" + 
				"                (select count(*) from prescription_tbl where reservation_no = r.reservation_no) as prescription_status\r\n" + 
				"FROM \r\n" + 
				"reservation_tbl r \r\n" + 
				"where hospital_no in (select hospital_no from hospital_tbl where member_no=?)\r\n" + 
				"and\r\n" + 
				"(select DOCTOR_NO from DOCTOR_TBL where doctor_no = (select doctor_no from reservation_detail_tbl where reservation_no=r.reservation_no)) = ?\r\n" + 
				"ORDER BY 1 DESC)RES) WHERE Rnum BETWEEN ? AND ?";
		Object[] params = {memberNo,doctorNo,startPage,endPage};
		List list = jdbc.query(query, h_ReservationRowMapper,params);
		return list;
	}

	public int insertReservation(Reservation r) {
		String query = "insert into reservation_tbl values(reservation_seq.nextval,?,?,1,to_char(sysdate,'yyyy-mm-dd hh24:mm:ss'),1,?)";
		Object[] params = {r.getHospitalNo(),r.getMemberNo(),r.getReservationTime()};
		int result = jdbc.update(query, params);
		return result;
	}

	public int selecteCurrResNo() {
		String query = "select max(reservation_no) from reservation_tbl";
		int currResNo = jdbc.queryForObject(query, Integer.class);
		return currResNo;
	}
	
	public int insertReservationDetail(int currResNo, ReservationDetail rd) {
		String query = "insert into reservation_detail_tbl values(reservation_detail_seq.nextval,?,?,?,?,?)";
		String doctorNo = rd.getDoctorNo() == 0 ? null : String.valueOf(rd.getDoctorNo());
		String subjectNo = rd.getSubjectNo() == 0 ? null : String.valueOf(rd.getSubjectNo());
		String childNo = rd.getChildNo() == 0 ? null : String.valueOf(rd.getChildNo());
		Object[] params = {currResNo, doctorNo, subjectNo, rd.getSymptom(), childNo};
		int result = jdbc.update(query, params);
		return result;
	}

	
}
