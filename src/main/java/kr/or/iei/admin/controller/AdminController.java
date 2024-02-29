package kr.or.iei.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.or.iei.admin.model.dto.AdminBusinessAuth;
import kr.or.iei.admin.model.dto.AdminBusinessAuthListData;
import kr.or.iei.admin.model.dto.FaqListData;
import kr.or.iei.admin.model.dto.HospitalReportListData;
import kr.or.iei.admin.model.dto.Notice;
import kr.or.iei.admin.model.dto.NoticeListData;
import kr.or.iei.admin.model.dto.Review;
import kr.or.iei.admin.model.service.AdminService;
import kr.or.iei.hospital.model.dto.HospitalMemberReport;
import kr.or.iei.hospital.model.dto.HospitalMemberReportListData;
import kr.or.iei.member.model.dto.Member;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@GetMapping(value = "/adminMain")
	public String adminMain() {
		return "/admin/adminMain";
	}

	// 자주묻는 질문 받아오는 코드
	@GetMapping(value = "/faqList")
	public String faqList(Model model) {
		FaqListData fld = adminService.selectAllFaq();
		model.addAttribute("categoryList", fld.getCategoryList());
		model.addAttribute("contentList", fld.getContentList());
		System.out.println(fld);
		return "/admin/faqList";
	}

	// 회원정보 전체 받아오는 코드
	@GetMapping(value = "/manageMember")
	public String manageMember(Model model) {
		List list = adminService.selectAllMember();
		model.addAttribute("memberList", list);
		return "/admin/manageMember";
	}

	// 공지사항 리스트 받아오는 코드
	@GetMapping(value = "/noticeList")
	public String noticeList(int reqPage, Model model) {
		NoticeListData nld = adminService.selectAllNotice(reqPage);
		model.addAttribute("noticeList", nld.getList());
		model.addAttribute("pageNavi", nld.getPageNavi());
		return "admin/noticeList";
	}

	// 키워드에 따라 공지사항 검색하는 코드
	@GetMapping(value = "/searchNotice")
	public String search(int reqPage, String type, String keyword, Model model) {
		NoticeListData nld = adminService.searchNoitce(reqPage, type, keyword);
		model.addAttribute("noticeList", nld.getList());
		model.addAttribute("pageNavi", nld.getPageNavi());
		return "admin/noticeList";
	}

	@GetMapping(value = "/noticeWriteFrm")
	public String noticeWriteFrm() {
		return "/admin/noticeWriteFrm";
	}

	@GetMapping(value = "/writeNotice")
	public String writeNotice(Notice n, Model model) {
		int result = adminService.insertNotice(n);
		return "redirect:/admin/noticeList?reqPage=1";
	}

	@GetMapping(value = "/noticeDetail")
	public String noticeDetail(int noticeNo, Model model) {
		Notice n = adminService.searchNoticeDetail(noticeNo);
		model.addAttribute("n", n);
		return "/admin/noticeDetail";
	}

	@GetMapping(value = "/deleteNotice")
	public String deleteNotice(int noticeNo) {
		int result = adminService.deleteNotice(noticeNo);
		return "redirect:/admin/noticeList?reqPage=1";
	}

	@GetMapping(value = "/noticeUpdateFrm")
	public String noticeUpdateFrm(int noticeNo, Model model) {
		Notice n = adminService.searchNoticeDetail(noticeNo);
		model.addAttribute("n", n);
		return "/admin/noticeUpdateFrm";
	}

	@GetMapping(value = "/deleteChk")
	public String deleteChk(String deleteList) {
		boolean result = adminService.deletChk(deleteList);
		if (result) {
			System.out.println("삭제성공");
		} else {
			System.out.println("삭제실패");
		}
		return "redirect:/admin/manageReport?reqPage=1";
	}


	@GetMapping(value = "/reportConfirm")
	public String reportConfirm(int reportNo) {
		int result = adminService.confirmReport(reportNo);
		return "redirect:/admin/manageReport?reqPage=1";
	}

	@GetMapping(value = "/businessAuthList")
	public String BusinessAuthList(int reqPage, Model model) {
		AdminBusinessAuthListData abld = adminService.selectAllBusinessAuth(reqPage);
		model.addAttribute("businessAuthList", abld.getList());
		model.addAttribute("pageNavi", abld.getPageNavi());
		return "admin/businessAuthList";
	}

	@GetMapping(value = "/confirmAuth")
	public String confirmAuth(int businessAuthNo, Model model) {
		AdminBusinessAuth aba = adminService.confirmAuth(businessAuthNo);
		
		model.addAttribute("authInfo", aba);
		return "admin/confirmAuth";
	}

	@GetMapping(value = "/authConfirmSuccess")
	public String authConfirmSuccess(int businessAuthNo) {
		boolean result = adminService.authConfirmSuccess(businessAuthNo);
		if (result) {
			System.out.println("success!!");
		} else {
			System.out.println("fail");
		}
		return "admin/businessAuthList";
	}

	@GetMapping(value = "/authConfirmFail")
	public String authConfirmFail(int businessAuthNo) {
		boolean result = adminService.authConfirmFail(businessAuthNo);
		if (result) {
			System.out.println("success!!");
		} else {
			System.out.println("fail");
		}
		return "admin/businessAuthList";
	}

	// 관리자 인터셉터 메세지
	@GetMapping("/adminMsg")
	public String adminMsg(Model model) {
		model.addAttribute("title", "관리자페이지");
		model.addAttribute("msg", "관리자만 접근이 가능합니다");
		model.addAttribute("icon", "warning");
		model.addAttribute("loc", "/");
		return "common/msg";
	}

	@GetMapping("/blockMsg")
	public String blockMsg(Model model) {
		model.addAttribute("title", "이용정지");
		model.addAttribute("msg", "이용정지중입니다.");
		model.addAttribute("icon", "warning");
		model.addAttribute("loc", "/");
		return "common/msg";
	}
	
	@GetMapping("/loginMsg")
	public String loginMsg(Model model) {
		model.addAttribute("title", "로그인하세요");
		model.addAttribute("msg", "로그인을 하지 않았습니다.");
		model.addAttribute("icon", "warning");
		model.addAttribute("loc", "/");
		return "common/msg";
	}
	

	@GetMapping("/manageReview")
	public String manageReview(Model model) {
		List<Review> reviewList = adminService.selectAllReview();
		model.addAttribute("reviewList", reviewList);
		return "admin/manageReview";
	}

	// 리뷰삭제시키는 코드
	@ResponseBody
	@GetMapping("/deleteReview")
	public int deleteReview(int reviewNo) {
		int result = adminService.deleteReview(reviewNo);
		return result;
	}

	// 이용정지 해제시키는 코드
	@ResponseBody
	@GetMapping("/unBlock")
	public int unBlock(int memberNo) {
		int result = adminService.unBlock(memberNo);
		return result;
	}

	// 관리자가 이용정지시키는 코드
	@ResponseBody
	@GetMapping("/block")
	public int block(int memberNo) {
		int result = adminService.block(memberNo);
		return result;
	}
	
	//병원신고 페이지 가져오는 코드
	@GetMapping(value = "/manageHospitalMemberReport")
	public String manageHospitalMemberReport(int reqPage, Model model) {
		HospitalMemberReportListData hmrld = adminService.selectAllHospitalMemberReport(reqPage);
		model.addAttribute("hospitalMemberReportList", hmrld.getList());
		model.addAttribute("pageNavi", hmrld.getPageNavi());
		return "admin/manageHospitalMemberReport";
	}
	
	//병원신고 상세 가져오는 코드
	@GetMapping(value = "/hospitalReportDetail")
	public String hospitalReportDetail(int reportNo, Model model) {
		HospitalMemberReport hmr = adminService.selectOneHospitalMemberReport(reportNo);
		model.addAttribute("hmr", hmr);
		return "admin/hospitalReportDetail";
	}
	
	//병원신고 거부
	@GetMapping(value = "/hospitalReportDelete")
	public String hospitalReportDelete(int reportNo) {
		int result = adminService.deleteHospitalMemberReport(reportNo);
		return "redirect:/admin/manageHospitalMemberReport?reqPage=1";
	}
	
	//병원신고 확인
	@GetMapping(value = "/hospitalReportConfirm")
	public String hospitalReportConfirm(int reportNo) {
		int result = adminService.confirmHospitalMemberReport(reportNo);
		return "redirect:/admin/manageHospitalMemberReport?reqPage=1";
	}
	
	//FAQ 추가시키는 코드
	@ResponseBody
	@GetMapping("/writeFaq")
	public int writeFAQ(int category, String title, String content, @SessionAttribute Member member) {
		int memberNo = member.getMemberNo();
		int result = adminService.writeFaq(memberNo,category,title,content);
		return result;
	}
	
	@GetMapping(value = "/manageHospitalReport")
	public String manageHospitalReport(int reqPage, Model model) {
		HospitalReportListData hrld = adminService.selectAllHospitalReport(reqPage);
		model.addAttribute("hospitalReportList", hrld.getList());
		model.addAttribute("pageNavi", hrld.getPageNavi());
		System.out.println(hrld.getList());
		return "admin/manageHospitalReport";
	}
}
