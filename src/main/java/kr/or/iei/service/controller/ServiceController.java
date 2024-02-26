package kr.or.iei.service.controller;

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

import kr.or.iei.FileUtils;
import kr.or.iei.hospital.model.dto.Hospital;
import kr.or.iei.hospital.model.service.HospitalService;
import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.reservation.model.dto.Reservation;
import kr.or.iei.reservation.model.dto.ReservationDetail;
import kr.or.iei.reservation.model.dto.ReservationFile;
import kr.or.iei.reservation.model.service.ReservationService;

@Controller
@RequestMapping(value="/service")
public class ServiceController {
	@Autowired
	private HospitalService hospitalService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private ReservationService reservationService;
	
	@Value("${file.root}")
	private String root;
	
	@Autowired
	private FileUtils fileUtils;
	
	@GetMapping(value="/searchHospitalMain")
	public String searchHospitalMain() {
		return "/service/searchHospitalMain";
	}
	
	@ResponseBody
	@GetMapping(value="/searchHospital")
	public List searchHospital(String keyword) {
		List list = hospitalService.searchHospital(keyword);
		return list;
	}
	
	@GetMapping(value="/hospitalDetail")
	public String hospitalDetail(int hospitalNo, Model model) {
		Hospital h = hospitalService.searchHospitalDetail(hospitalNo);
		model.addAttribute("h", h);
		return "/service/hospitalDetail";
	}
	
	@ResponseBody
	@GetMapping(value="/selectReviewList")
	public List selectReviewList(int hospitalNo, int sortValue, int start, int amount) {
		List reviewList = hospitalService.selectReviewList(hospitalNo, sortValue, start, amount);
		return reviewList;
	}
	
	@GetMapping(value="/reserveContactFrm")
	public String reserveContactFrm(int hospitalNo, String hospitalName, Model model) {
		Hospital h = hospitalService.selectHospitalInfo(hospitalNo);
		h.setHospitalNo(hospitalNo);
		h.setHospitalName(hospitalName);
		model.addAttribute("h", h);
		return "/service/reserveContactFrm";
	}
	
	@ResponseBody
	@PostMapping(value="/selectMyChildInfo")
	public List selectMyChildInfo(int memberNo) {
		List childList = memberService.selectMyChildInfo(memberNo);
		return childList;
	}
	
	@PostMapping(value="/reserveContact")
	public String reserveContact(Reservation r, ReservationDetail rd, String memberName, String hospitalName, Model model) {
		int result =  reservationService.insertReserveContact(r, rd);
		if(result > 0) {
			model.addAttribute("titleMsg","예약이 접수되었습니다.");
			model.addAttribute("titleSub","마이페이지에서 예약 내역을 확인하세요.");
			model.addAttribute("hospitalName","<li><span>예약한 병원:</span><strong>"+hospitalName+"</strong></li>");
			model.addAttribute("reservationTime","<li><span>예약 일시:</span><strong>"+r.getReservationTime()+"</strong></li>");
			model.addAttribute("memberName","<li><span>접수자 성함:</span><strong>"+memberName+"</strong></li>");
			model.addAttribute("loc","/service/hospitalDetail?hospitalNo="+r.getHospitalNo());
		}else {
			model.addAttribute("titleMsg","예약에 실패했습니다.");
			model.addAttribute("titleSub","관리자에게 문의하세요.");
			model.addAttribute("loc","/");			
		}
		return "/common/modalMsg";
	}
	
	@GetMapping(value="/reserveContactlessFrm")
	public String reserveContactlessFrm(int hospitalNo, String hospitalName, Model model) {
		Hospital h = hospitalService.selectHospitalInfo(hospitalNo);
		h.setHospitalNo(hospitalNo);
		h.setHospitalName(hospitalName);
		model.addAttribute("h", h);
		return "/service/reserveContactlessFrm";
	}
	
	@PostMapping(value="/reserveContactless")
	public String reserveContactless(Reservation r, ReservationDetail rd, MultipartFile[] imgFile, String memberName, String hospitalName, Model model) {
		List<ReservationFile> fileList = new ArrayList<ReservationFile>();
		if(!imgFile[0].isEmpty()) {
			String savepath = root+"/reservation/";
			for(MultipartFile file : imgFile) {
				String filename = file.getOriginalFilename();
				String filepath = fileUtils.upload(savepath, file);
				ReservationFile rFile = new ReservationFile();
				rFile.setFilename(filename);
				rFile.setFilepath(filepath);
				fileList.add(rFile);
			}
		}
		int result = reservationService.insertReserveContactless(r, rd, fileList);
		if(result == (fileList.size()+2)) {
			model.addAttribute("titleMsg","예약이 접수되었습니다.");
			model.addAttribute("titleSub","마이페이지에서 예약 내역을 확인하세요.");
			model.addAttribute("hospitalName","<span>예약한 병원:</span><strong>"+hospitalName+"</strong>");
			if(r.getReservationTime() != null) {
				model.addAttribute("reservationTime","<span>예약 일시:</span><strong>"+r.getReservationTime()+"</strong>");				
			}
			model.addAttribute("memberName","<span>접수자 성함:</span><strong>"+memberName+"</strong>");
			model.addAttribute("loc","/service/hospitalDetail?hospitalNo="+r.getHospitalNo());
		}else {
			model.addAttribute("titleMsg","예약에 실패했습니다.");
			model.addAttribute("titleSub","관리자에게 문의하세요.");
			model.addAttribute("loc","/");			
		}
		return "/common/modalMsg";
	}
}







