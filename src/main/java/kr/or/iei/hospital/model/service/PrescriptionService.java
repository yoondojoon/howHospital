package kr.or.iei.hospital.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.or.iei.hospital.model.dao.PrescriptionDao;
import kr.or.iei.hospital.model.dto.PrescriptionFile;

@Service
public class PrescriptionService {
	@Autowired
	private PrescriptionDao prescriptionDao;

	public int insertPrescription(PrescriptionFile pf) {
		int result = prescriptionDao.insertPrescription(pf);
		return result;
	}
}
