package kr.or.iei.hospital.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import kr.or.iei.hospital.model.dto.PrescriptionFile;
import kr.or.iei.hospital.model.dto.PrescriptionFileRowMapper;

@Repository
public class PrescriptionDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private PrescriptionFileRowMapper prescriptionFileRowMapper;
	public int insertPrescription(PrescriptionFile pf) {
		String query = "insert into prescription_tbl values(prescription_seq.nextval,?,to_char(sysdate,'yyyy-mm-dd'),?,?)";
		Object[] params = {pf.getReservationNo(),pf.getPrescriptionName(),pf.getPrescriptionPath()};
		int result = jdbc.update(query,params);
		return result;
	}
	
	
}
