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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
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
import kr.or.iei.member.model.dto.Member;
import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.reservation.model.dto.H_Reservation;
import kr.or.iei.reservation.model.dto.ReservationDetail;
import kr.or.iei.reservation.model.dto.ReservationDetailList;
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

	@Value("${file.prescriptionRoot}")
	private String prescriptionRoot;

	@Autowired
	private FileUtils fileUtils;

	@GetMapping(value = "/myHospitalMain" )
	public String myHospitalMain() {
		return "hospital/myHospitalMain";
	}

	@PostMapping(value = "/myHospitalEnroll")
	public String myHospitalEnroll(Hospital hospital, String hospitalPostcode, String hospitalAddrMain,
			String hospitalAddrSub, String dayOpenHour, String dayCloseHour, String weekendOpenHour,
			String weekendCloseHour, String lunchOpenHour, String lunchCloseHour, String[] hol, String hospitalTelFirst,
			String hospitalTelLast, String[] doctor_name, String[] doctor_education, String[] doctor_experience,
			String[] subjectSelect, MultipartFile hospital_picture, MultipartFile[] doctor_picture, String CostOne,
			String CostTwo, Model model) {

		// VO 값 세팅
//		hospital.setHospitalAddress(hospitalPostcode + " " + hospitalAddrMain + " " + hospitalAddrSub);

		// 병원사진
		String savepath1 = root + "/hospital/";
		String filepath1 = fileUtils.upload(savepath1, hospital_picture); // 파일 업로드 및 경로 반환
		hospital.setHospitalPicture(filepath1); // 중복처리된 이름 여기에 넣어줌. 번호는 시퀀스쓴다.

		Time time = new Time();
		time.setDayHour(dayOpenHour + "~" + dayCloseHour);
		time.setWeekendHour(weekendOpenHour + "~" + weekendCloseHour);
		time.setLunchHour(lunchOpenHour + "~" + lunchCloseHour);
		String holiday = String.join("", hol);
		time.setHoliday(Integer.parseInt(holiday));

		hospital.setHospitalTel(hospitalTelFirst + "-" + hospitalTelLast);
		System.out.println(hospital.getLat());
		// 의사정보 등록
		List<Doctor> doctorList = new ArrayList<Doctor>();
		List<Subject> subjectList = new ArrayList<Subject>();
		for (int i = 0; i < doctor_name.length; i++) {
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
		if (result == (doctorList.size() + subjectList.size() + 2)) {
			System.out.println("성공");

			return "redirect:/";

		} else {
			System.out.println("실패");
			return "redirect:/";
		}
	}

	@GetMapping(value = "/myHospitalDetail")
	// hospital enroll 진입: hospitalNo
	// 마이페이지에서 진입: hospitalNo
	public String myHospitalDetail(Model model, @SessionAttribute(required = false) Member member) {
		Hospital h = hospitalService.selectHospital(member.getMemberNo());
		if (h != null) {
			model.addAttribute("h", h);
			return "hospital/myHospitalDetail";
		}
		return "redirect:/hospitalAuth";

	}

	@PostMapping(value = "/myHospitalUpdateFrm")
	public String myHospitalUpdateFrm(Model model,
			@RequestParam(value = "hospitalNo", required = false) int hospitalNo) {
		Hospital h = hospitalService.findHospitalInfo(hospitalNo);
		System.out.println(h.getSubjectList());
		System.out.println(h.getDoctorList());

		model.addAttribute("h", h);
		return "hospital/myHospitalUpdateFrm";
	}

	@PostMapping(value = "/myHospitalUpdate")
	public String myHospitalUpdate(Hospital hospital, String hospitalPostcode, String hospitalAddrMain,
			String hospitalAddrSub, String dayOpenHour, String dayCloseHour, String weekendOpenHour,
			String weekendCloseHour, String lunchOpenHour, String lunchCloseHour, String[] hol, String hospitalTelFirst,
			String hospitalTelLast, int[] existDoctor_no, String[] doctor_name, String[] doctor_education,
			String[] doctor_experience, String[] subjectSelect, MultipartFile hospital_picture,
			MultipartFile[] doctor_picture, String CostOne, String CostTwo, Model model, int[] updateDoctorNo,
			int[] delDoctorNo, int[] existSubject_no) {
	    System.out.println("컨트롤러 멀티파트: " + doctor_picture);

		// 병원정보 및 시간 Update
		hospital.setHospitalTel(hospitalTelFirst + "-" + hospitalTelLast);
		Time time = new Time();
		time.setDayHour(dayOpenHour + "~" + dayCloseHour);
		time.setWeekendHour(weekendOpenHour + "~" + weekendCloseHour);
		time.setLunchHour(lunchOpenHour + "~" + lunchCloseHour);
		String holiday = String.join("", hol);
		time.setHoliday(Integer.parseInt(holiday));

		List<Doctor> doctorList = new ArrayList<Doctor>();
		List<Subject> subjectList = new ArrayList<Subject>();

		int result = 0;
		result += hospitalService.updateHospital(hospital, time);

		// 의사정보, 진료과목정보 수정
		for (int i = 0; i < doctor_name.length; i++) {
			Doctor doctor = new Doctor();
			doctor.setDoctorName(doctor_name[i]);
			doctor.setDoctorEducation(doctor_education[i]);
			doctor.setDoctorExperience(doctor_experience[i]);
			doctor.setDoctorNo(existDoctor_no[i]);
			doctor.setSubjectName(subjectSelect[i]);
			doctor.setSubjectNo(existSubject_no[i]);
			doctorList.add(doctor);
		}

		result += hospitalService.updateDoctorSubject(doctorList);

		// 의사사진 수정 (변경된 의사번호랑, 변경된 파일명 받아서 업데이트)
		if (updateDoctorNo != null) {
		    String savepath = root + "/doctor/";
		    List<Doctor> doctorPictureList = new ArrayList<>(); // List를 초기화

		    for (int i = 0; i < updateDoctorNo.length; i++) {
		        Doctor doctor = new Doctor();
		        doctor.setDoctorNo(updateDoctorNo[i]); // 의사번호 설정

		        // 의사 사진 업로드
		        if (i < doctor_picture.length) {
		            MultipartFile file = doctor_picture[i];
		            String filename = file.getOriginalFilename();
		            String filepath = fileUtils.upload(savepath, file);
		            System.out.println("컨트롤러 파일패스 " + filepath);
		            doctor.setDoctorPicture(filepath); // 의사사진 설정
		        }
		        
		        doctorPictureList.add(doctor); // 리스트에 의사 객체 추가
		    }
		    
		    result += hospitalService.updateDoctorPicture(doctorPictureList);
		}
		
		
		
		
	if (updateDoctorNo != null) {
		if (result == (doctorList.size() + subjectList.size() + updateDoctorNo.length + 2)) {
			System.out.println("성공");
			return "redirect:/";

		} else {
			System.out.println("실패");
			return "redirect:/";
		}
	}else {
		if (result == (doctorList.size() + subjectList.size() + 2)) {
			System.out.println("성공");
			return "redirect:/";

		} else {
			System.out.println("실패");
			return "redirect:/";
		}
	}

	}

	@PostMapping(value = "/businessAuthEnroll")
	public String businessAuthEnroll(BusinessAuth ba, MultipartFile[] upfile, Model model) {
		List<BusinessAuthFile> fileList = new ArrayList<BusinessAuthFile>();
		if (!upfile[0].isEmpty()) {
			String savepath = root + "/auth/";
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
		if (result == (fileList.size() + 2)) {
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

	@GetMapping("/myHospitalReviewList")
	public String myHospitalReivewList() {
		return "hospital/myHospitalReviewList";
	}
		
	
	
	@GetMapping("/myHospitalReservation")
	public String myHospitalReservation(int reqPage, Model model, @SessionAttribute Member member, int doctorNo) {
		// 회원 번호로 해당하는 병원 정보 가져오기.
		int memberNo = member.getMemberNo();
		// 병원 예약 조회해오기
		ReservationListData nld = reservationService.selectReservation(reqPage, memberNo, doctorNo);
		model.addAttribute("reservation", nld.getList());
		model.addAttribute("pageNavi", nld.getPageNavi());
		model.addAttribute("doctorList", nld.getDoctorList());
		model.addAttribute("doctorNo", doctorNo);
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
		String savepath = prescriptionRoot + "/prescription/";
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

	@GetMapping("/detailReservation")
	public String detailReservation(H_Reservation hr, Model model) {
		ReservationDetailList rdl = reservationDetailService.selectOneReservation(hr);
		model.addAttribute("reservationDetailList", rdl);
		return "hospital/detailReservationFrm";
	}
	
	@ResponseBody
	@PostMapping(value="/selectMyReviewHistory")
	public List selectMyResHistory(int memberNo, int start, int amount) {
		List myHistoryList = hospitalService.selectMyReviewHistory(memberNo, start, amount);
		return myHistoryList;
	}
	

}
