package kr.or.iei.hospital.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import kr.or.iei.FileUtils;
import kr.or.iei.hospital.model.dto.BusinessAuth;
import kr.or.iei.hospital.model.dto.BusinessAuthFile;
import kr.or.iei.hospital.model.dto.Doctor;
import kr.or.iei.hospital.model.dto.Hospital;
import kr.or.iei.hospital.model.dto.PrescriptionFile;
import kr.or.iei.hospital.model.dto.Subject;
import kr.or.iei.hospital.model.dto.Time;
import kr.or.iei.hospital.model.service.DoctorService;
import kr.or.iei.hospital.model.service.HospitalService;
import kr.or.iei.hospital.model.service.PrescriptionService;
import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.reservation.model.dto.ReservationListData;
import kr.or.iei.reservation.model.service.ReservationDetailService;
import kr.or.iei.reservation.model.service.ReservationService;

@Controller
@RequestMapping(value = "/hospital")
public class HospitalController {
	@Autowired
	private HospitalService hospitalService;
	@Autowired
	private ReservationService reservationService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private ReservationDetailService reservationDetailService;
	@Autowired
	private PrescriptionService prescriptionService;

	@Value("${file.root}")
	private String root; // 자바 전체에서 쓸수있는 변수, application.properties에 선언되어있는 값을 문자열로 가져옴.

	@Autowired
	private FileUtils fileUtils;

	@GetMapping(value = "/myHospitalMain")
	public String myHospitalMain() {
		return "hospital/myHospitalMain";
	}

	@PostMapping(value="/myHospitalEnroll")
	public String myHospitalEnroll(Hospital hospital, String postCode, String hospitalAddrMain, String hospitalAddrSub,
			String dayOpenHour, String dayCloseHour, String weekendOpenHour, String weekendCloseHour, String lunchOpenHour, String lunchCloseHour, String[] hol, 
			String hospitalTelFirst, String hospitalTelLast,
			String[] doctor_name, String[] doctor_education, String[] doctor_experience, String[] subjectSelect, MultipartFile[] doctor_picture, String CostOne, String CostTwo) {
			
			//System.out.println(hospital.getHospitalAddress());
		
			//VO 값 세팅
			hospital.setHospitalAddress(postCode +" "+ hospitalAddrMain + " " + hospitalAddrSub);
			
			Time time = new Time();
			time.setDayHour(dayOpenHour + "~" +  dayCloseHour);
			time.setWeekendHour(weekendOpenHour + "~" + weekendCloseHour);
			time.setLunchHour(lunchOpenHour + "~" + lunchCloseHour);
			
			String holiday = String.join("", hol);
			time.setHoliday(Integer.parseInt(holiday));
			
			System.out.println("회원번호:"+hospital.getMemberNo());
			hospital.setHospitalTel(hospitalTelFirst + "-" + hospitalTelLast);
			System.out.println(hospital.getLat());
			//의사정보 등록
			List<Doctor> doctorList = new ArrayList<Doctor>();
			List<Subject> subjectList = new ArrayList<Subject>();
	        for(int i = 0; i < doctor_name.length; i++) {
	            Doctor doctor = new Doctor(); 
	            doctor.setDoctorName(doctor_name[i]);
	            doctor.setDoctorEducation(doctor_education[i]); 
	            doctor.setDoctorExperience(doctor_experience[i]);
	            
    			String savepath = root + "/doctor/";
    			// 업로드한 파일명을 추출
				String filename = doctor_picture[i].getOriginalFilename();
				String filepath = fileUtils.upload(savepath, doctor_picture[i]);
				doctor.setDoctorPicture(filepath);
				doctorList.add(doctor);
				
				Subject subject = new Subject();
				subject.setSubjectName(subjectSelect[i]);
	            subjectList.add(subject);
	        }
	        
	        int result = hospitalService.insertHospitalEnroll(hospital, time, doctorList, subjectList);

		return "redirect:/";
	}

	@PostMapping(value = "/businessAuthEnroll")
	public String businessAuthEnroll(BusinessAuth ba, MultipartFile[] upfile, Model model) {
		List<BusinessAuthFile> fileList = new ArrayList<BusinessAuthFile>();
		if (!upfile[0].isEmpty()) {
			String savepath = root + "/hospital/";
			for (MultipartFile file : upfile) {
				// 업로드한 파일명을 추출
				String filename = file.getOriginalFilename();
				String filepath = fileUtils.upload(savepath, file);
				BusinessAuthFile businessAuthFile = new BusinessAuthFile();
				businessAuthFile.setFilename(filename);
				businessAuthFile.setFilepath(filepath);
				fileList.add(businessAuthFile);
			}
		}
		// ba : businessAuthNo, memberNo
		// fileList : (BusinessAuthFile) x 첨부파일갯수(2개)
		// 총 3차례 insert

		int result = hospitalService.insertBusinessAuth(ba, fileList);

		// insert 성공 테이블 결과(1) + 파일 테이블 결과(파일갯수)
		if (result == (fileList.size() + 2)) { // notice 테이블 1 포함
			System.out.println("성공");
			return "redirect:/";
		} else {
			System.out.println("실패");
			return "redirect:/";
		}
	}

	@GetMapping(value = "/businessAuth")
	public String businessAuth(Model model, HttpSession session) {
		return "hospital/businessAuth";
	}

	@GetMapping(value = "/myHospitalFrm")
	public String myHospitalFrm() {
		return "hospital/myHospitalFrm";
	}

	@GetMapping("/myHospitalReservation")
	public String myHospitalReservation(int reqPage, Model model) {
		// 병원 예약 조회해오기
		ReservationListData nld = reservationService.selectReservation(reqPage);
		model.addAttribute("reservation", nld.getList());
		model.addAttribute("pageNavi", nld.getPageNavi());
		return "hospital/myHospitalReservationList";
	}

	@ResponseBody
	@GetMapping("/changeReservationType")
	public int changeReservationType(int selectValue, int reservationNo) {
		// 병원 예약 업데이트
		int result = reservationService.updateReservation(selectValue, reservationNo);
		if (result > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	@ResponseBody
	@PostMapping("/prescriptionRegistration")
	public int prescriptionRegistration(MultipartFile file, PrescriptionFile pf) {
		String savepath = root + "/prescription/";
		String filename = file.getOriginalFilename();
		String filepath = fileUtils.upload(savepath, file);
		pf.setPrescriptionName(filename);
		pf.setPrescriptionPath(filepath);
		int result = prescriptionService.insertPrescription(pf);
		if (result > 0) {
			return 1;
		} else {
			return 0;
		}
	}

}
