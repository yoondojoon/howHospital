package kr.or.iei.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;

import kr.or.iei.admin.model.dto.AdminBusinessAuth;
import kr.or.iei.admin.model.dto.AdminBusinessAuthListData;
import kr.or.iei.admin.model.dto.FaqListData;
import kr.or.iei.admin.model.dto.HospitalReport;
import kr.or.iei.admin.model.dto.HospitalReportListData;
import kr.or.iei.admin.model.dto.MemberReport;
import kr.or.iei.admin.model.dto.MemberReportListData;
import kr.or.iei.admin.model.dto.Notice;
import kr.or.iei.admin.model.dto.NoticeListData;
import kr.or.iei.admin.model.dto.Review;
import kr.or.iei.admin.model.service.AdminService;

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

	// 신고내역 받아오는 코드
	@GetMapping(value = "/manageReport")
	public String adminMain(int reqPage, Model model) {
		MemberReportListData mrld = adminService.selectAllMemberReport(reqPage);
		model.addAttribute("reportList", mrld.getList());
		model.addAttribute("pageNavi", mrld.getPageNavi());
		return "/admin/manageReport";
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

	@GetMapping(value = "/reportDetail")
	public String reportDetail(int reportNo, Model model) {
		MemberReport mr = adminService.searchReportDetail(reportNo);
		model.addAttribute("mr", mr);
		return "/admin/reportDetail";
	}

	@GetMapping(value = "/reportDelete")
	public String reportDelete(int reportNo) {
		int result = adminService.deleteReport(reportNo);
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
		System.out.println("aaaaaaa : " + aba.getFileList());
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
	@GetMapping(value = "/manageHospitalReport")
	public String manageHospitalReport(int reqPage, Model model) {
		HospitalReportListData hrld = adminService.selectAllHospitalReport(reqPage);
		model.addAttribute("hospitalReportList", hrld.getList());
		model.addAttribute("pageNavi", hrld.getPageNavi());
		return "admin/manageHospitalReport";
	}
	
	//병원신고 상세 가져오는 코드
	@GetMapping(value = "/hospitalReportDetail")
	public String hospitalReportDetail(int reportNo, Model model) {
		HospitalReport hr = adminService.selectOneHospitalReport(reportNo);
		model.addAttribute("hr", hr);
		return "admin/hospitalReportDetail";
	}
	
	//병원신고 거부
	@GetMapping(value = "/hospitalReportDelete")
	public String hospitalReportDelete(int reportNo) {
		int result = adminService.deleteHospitalReport(reportNo);
		return "redirect:/admin/manageHospitalReport?reqPage=1";
	}
	
	//병원신고 확인
	@GetMapping(value = "/hospitalReportConfirm")
	public String hospitalReportConfirm(int reportNo) {
		int result = adminService.confirmHospitalReport(reportNo);
		return "redirect:/admin/manageHospitalReport?reqPage=1";
	}
}
