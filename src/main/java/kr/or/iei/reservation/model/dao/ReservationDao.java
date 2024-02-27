package kr.or.iei.reservation.model.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.or.iei.hospital.model.dto.DoctorRowMapper;
import kr.or.iei.hospital.model.dto.Hospital;
import kr.or.iei.hospital.model.dto.PrescriptionFile;
import kr.or.iei.hospital.model.dto.PrescriptionFileRowMapper;
import kr.or.iei.reservation.model.dto.H_Reservation;
import kr.or.iei.reservation.model.dto.H_ReservationRowMapper;
import kr.or.iei.reservation.model.dto.MyReservationDetailRowMapper;
import kr.or.iei.reservation.model.dto.MyReservationHistoryRowMapper;
import kr.or.iei.reservation.model.dto.ReceiptData;
import kr.or.iei.reservation.model.dto.ReceiptDataRowMapper;
import kr.or.iei.reservation.model.dto.Reservation;
import kr.or.iei.reservation.model.dto.ReservationDetail;
import kr.or.iei.reservation.model.dto.ReservationFile;
import kr.or.iei.reservation.model.dto.ReservationFileRowMapper;
import kr.or.iei.reservation.model.dto.ReservationRowMapper;

@Repository
public class ReservationDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private H_ReservationRowMapper h_ReservationRowMapper;
	@Autowired
	private DoctorRowMapper doctorRowMapper;
	@Autowired
	private MyReservationHistoryRowMapper myReservationHistoryRowMapper;
	@Autowired
	private MyReservationDetailRowMapper myReservationDetailRowMapper;
	@Autowired
	private ReservationFileRowMapper reservationFileRowMapper;
	@Autowired
	private PrescriptionFileRowMapper prescriptionFileRowMapper;
	@Autowired
	private ReceiptDataRowMapper receiptDataRowMapper;
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
		String query = "insert into reservation_tbl values(reservation_seq.nextval,?,?,1,to_char(sysdate,'yyyy-mm-dd hh24:mm:ss'),?,?)";
		Object[] params = {r.getHospitalNo(),r.getMemberNo(),r.getReservationType(),r.getReservationTime()};
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

	
	public int insertReservationFile(ReservationFile rFile) {
		String query = "insert into reservation_file values(reservation_file_seq.nextval,?,?,?)";
		Object[] params = {rFile.getReservationNo(), rFile.getFilename(), rFile.getFilepath()};
		int result = jdbc.update(query, params);
		return result;
	}
	
	public int insertReservationContactless(Reservation r) {
		String query = "insert into reservation_tbl values(reservation_seq.nextval,?,?,1,to_char(sysdate,'yyyy-mm-dd hh24:mm:ss'),?,to_char(sysdate,'yyyy-mm-dd hh24:mm:ss'))";
		Object[] params = {r.getHospitalNo(),r.getMemberNo(),r.getReservationType()};
		int result = jdbc.update(query, params);
		return result;
	}

	public int updateReservationDetail(H_Reservation hr) {
		String query = "update reservation_tbl set reservation_status=? where reservation_no=?";
		Object[] params = {hr.getReservationStatus(), hr.getReservationNo()};
		int result = jdbc.update(query,params);
		return result;
	}


	public int myResTotalCount(int memberNo) {
		String query = "select count(*) from reservation_tbl where member_no=?";
		Object[] params = {memberNo};
		int result = jdbc.queryForObject(query, Integer.class, params);
		return result;
	}
	
	public List selectMyResHistory(int memberNo, int start, int end) {
		String query = "select * from\r\n" + 
				"(select rownum rnum, r2.* from\r\n" + 
				"(select reservation_no, reservation_status, reservation_type,\r\n" + 
				"(select member_name from member_tbl where member_no=r.member_no) member_name,\r\n" + 
				"(select child_name from child_tbl where child_no in (select child_no from reservation_detail_tbl where reservation_no=r.reservation_no)) child_name, hospital_no,\r\n" + 
				"(select hospital_name from hospital_tbl where hospital_no=r.hospital_no) hospital_name,\r\n" + 
				"substr(reservation_time,1,instr(reservation_time,' ',1,1)-1) res_time_date,\r\n" + 
				"to_char(to_date(substr(reservation_time,1,instr(reservation_time,' ',1,1)-1),'yyyy-mm-dd'),'dy') res_time_day,\r\n" + 
				"substr(reservation_time,instr(reservation_time,' ',1,1)+1) res_time_time,\r\n" + 
				"(select count(*) from review_tbl where reservation_no=r.reservation_no) review_count\r\n" + 
				"from reservation_tbl r where member_no=? order by 1 desc) r2)\r\n" + 
				"where rnum between ? and ?";
		Object[] params = {memberNo, start, end};
		List myHistoryList = jdbc.query(query, myReservationHistoryRowMapper, params);
		return myHistoryList;
	}

	public ReservationDetail selectMyResDetail(int reservationNo) {
		String query = "select substr(reg_reservation,1,instr(reg_reservation,' ',1,1)-1) reg_date,\r\n" + 
				"to_char(to_date(substr(reg_reservation,1,instr(reg_reservation,' ',1,1)-1),'yyyy-mm-dd'),'dy') reg_day,\r\n" + 
				"substr(reg_reservation,instr(reg_reservation,' ',1,1)+1) reg_time,\r\n" + 
				"(select doctor_name from doctor_tbl where doctor_no in(select doctor_no from reservation_detail_tbl where reservation_no=r.reservation_no)) doctor_name,\r\n" + 
				"(select subject_name from subject_tbl where subject_no in (select subject_no from doctor_tbl where doctor_no in (select doctor_no from reservation_detail_tbl where reservation_no=r.reservation_no))) subject_name,\r\n" + 
				"(select symptom from reservation_detail_tbl where reservation_no=r.reservation_no) symptom\r\n" + 
				"from reservation_tbl r where reservation_no=?";
		Object[] params = {reservationNo};
		List list = jdbc.query(query, myReservationDetailRowMapper, params);
		if(list.isEmpty()) {
			return null;
		}else {
			return (ReservationDetail)list.get(0);
		}
	}

	public List selectMyReservationFiles(int reservationNo) {
		String query = "select * from reservation_file where reservation_no=?";
		Object[] params = {reservationNo};
		List list = jdbc.query(query, reservationFileRowMapper, params);
		return list;
	}

	public int cancelMyReservation(int reservationNo) {
		String query = "update reservation_tbl set reservation_status=2 where reservation_no=?";
		Object[] params = {reservationNo};
		int result = jdbc.update(query, params);
		return result;
	}

	public PrescriptionFile selectMyPrescription(int reservationNo) {
		String query = "select * from prescription_tbl where reservation_no=?";
		Object[] params = {reservationNo};
		List list = jdbc.query(query, prescriptionFileRowMapper, params);
		if(list.isEmpty()) {
			return null;
		}else {
			return (PrescriptionFile)list.get(0);
		}
	}

	public ReceiptData getReceipt(int reservationNo) {
		String query = "SELECT \r\n" + 
				"    R.RESERVATION_NO,R.MEMBER_NO,R.REG_RESERVATION,H.HOSPITAL_NO,M.MEMBER_NAME,M.MEMBER_ADDRESS,RD.DOCTOR_NO,D.DOCTOR_NAME,H.HOSPITAL_NAME,H.COST_ONE,M.MEMBER_PHONE\r\n" + 
				"FROM RESERVATION_TBL R \r\n" + 
				"JOIN HOSPITAL_TBL H ON R.HOSPITAL_NO = H.HOSPITAL_NO\r\n" + 
				"JOIN MEMBER_TBL M ON R.MEMBER_NO = M.MEMBER_NO\r\n" + 
				"left JOIN RESERVATION_DETAIL_TBL RD ON R.RESERVATION_NO = RD.RESERVATION_NO\r\n" + 
				"left JOIN DOCTOR_TBL D ON RD.DOCTOR_NO = D.DOCTOR_NO\r\n" + 
				"WHERE R.RESERVATION_NO=?";
		Object[] params = {reservationNo};
		List list = jdbc.query(query, receiptDataRowMapper,params);
		return (ReceiptData)list.get(0);
	}

	public int updateReservationStatus(int reservationNo) {
		String query = "update reservation_tbl set reservation_status=5 where reservation_no=?";
		Object[] params = {reservationNo};
		int result = jdbc.update(query,params);
		return result;
	}

	
	
}
