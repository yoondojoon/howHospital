package kr.or.iei.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.iei.admin.model.dto.AdminBusinessAuth;
import kr.or.iei.admin.model.dto.AdminBusinessAuthListData;
import kr.or.iei.admin.model.dto.MemberReport;
import kr.or.iei.admin.model.dto.MemberReportListData;
import kr.or.iei.admin.model.dto.Notice;
import kr.or.iei.admin.model.dto.NoticeListData;
import kr.or.iei.admin.model.service.AdminService;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping(value="/adminMain")
	public String adminMain() {
		return "/admin/adminMain";
	}
	
	@GetMapping(value="/faqList")
	public String faqList() {
		return "/admin/faqList";
	}

	@GetMapping(value="/manageReport")
	public String adminMain(int reqPage, Model model) {
		MemberReportListData mrld = adminService.selectAllMemberReport(reqPage);
		model.addAttribute("reportList",mrld.getList());
		model.addAttribute("pageNavi", mrld.getPageNavi());
		return "/admin/manageReport";
	}
	
	
	@GetMapping(value="/noticeList")
	public String noticeList(int reqPage, Model model) {
		NoticeListData nld = adminService.selectAllNotice(reqPage); 
		model.addAttribute("noticeList", nld.getList());
		model.addAttribute("pageNavi", nld.getPageNavi());
		return "admin/noticeList";
	}
	
	@GetMapping(value="/searchNotice")
	public String search(int reqPage, String type, String keyword, Model model) {
		NoticeListData nld = adminService.searchNoitce(reqPage,type,keyword);
		model.addAttribute("noticeList", nld.getList());
		model.addAttribute("pageNavi",nld.getPageNavi());
		return "admin/noticeList";
	}
	
	@GetMapping(value="/noticeWriteFrm")
	public String noticeWriteFrm() {
		return "/admin/noticeWriteFrm";
	}
	
	@GetMapping(value="/writeNotice")
	public String writeNotice(Notice n, Model model) {
		int result = adminService.insertNotice(n);
		return "redirect:/admin/noticeList?reqPage=1";
	}
	
	
	@GetMapping(value="/noticeDetail")
	public String noticeDetail(int noticeNo, Model model) {
		Notice n = adminService.searchNoticeDetail(noticeNo);
		model.addAttribute("n",n);
		return "/admin/noticeDetail";
	}
	
	@GetMapping(value="/deleteNotice")
	public String deleteNotice(int noticeNo) {
		int result = adminService.deleteNotice(noticeNo);
		return "redirect:/admin/noticeList?reqPage=1";
	}
	
	@GetMapping(value="/noticeUpdateFrm")
	public String noticeUpdateFrm(int noticeNo, Model model) {
		Notice n = adminService.searchNoticeDetail(noticeNo);
		model.addAttribute("n",n);
		return "/admin/noticeUpdateFrm";
	}
	
	@GetMapping(value="/deleteChk")
	public String deleteChk(String deleteList) {
		boolean result = adminService.deletChk(deleteList);
		if(result) {
			System.out.println("삭제성공");
		}else{
			System.out.println("삭제실패");
		}
		return "redirect:/admin/manageReport?reqPage=1";
		
	}
	
	@GetMapping(value="/reportDetail")
	public String reportDetail(int reportNo, Model model) {
		MemberReport mr = adminService.searchReportDetail(reportNo);
		model.addAttribute("mr",mr);
		return "/admin/reportDetail";
	}
	
	@GetMapping(value="/reportDelete")
	public String reportDelete(int reportNo) {
		int result = adminService.deleteReport(reportNo);
		return "redirect:/admin/manageReport?reqPage=1";
	}
	
	@GetMapping(value="/reportConfirm")
	public String reportDetail(int reportNo) {
		int result = adminService.confirmReport(reportNo);
		return "redirect:/admin/manageReport?reqPage=1";
	}
	
	@GetMapping(value="/businessAuthList")
	public String BusinessAuthList(int reqPage, Model model) {
		AdminBusinessAuthListData abld = adminService.selectAllBusinessAuth(reqPage); 
		model.addAttribute("businessAuthList", abld.getList());
		model.addAttribute("pageNavi", abld.getPageNavi());
		return "admin/businessAuthList";
	}
	
	@GetMapping(value="/confirmAuth")
	public String confirmAuth(int businessAuthNo, Model model) {
		AdminBusinessAuth aba = adminService.confirmAuth(businessAuthNo);
		model.addAttribute("authInfo",aba);
		System.out.println("aaaaaaa : "+aba.getFileList());
		return "admin/confirmAuth";
	}
	
	@GetMapping(value="/authConfirmSuccess")
	public String authConfirmSuccess(int businessAuthNo) {
		boolean result = adminService.authConfirmSuccess(businessAuthNo);
		if(result) {
			System.out.println("success!!");
		}else {
			System.out.println("fail");
		}
		return "admin/businessAuthList";
	}
	
	@GetMapping(value="/authConfirmFail")
	public String authConfirmFail(int businessAuthNo) {
		boolean result = adminService.authConfirmFail(businessAuthNo);
		if(result) {
			System.out.println("success!!");
		}else {
			System.out.println("fail");
		}
		return "admin/businessAuthList";
	}
	
	//관리자 인터셉터 메세지
	@GetMapping("/adminMsg")
	public String adminMsg(Model model) {
		model.addAttribute("title","관리자페이지");
		model.addAttribute("msg","관리자만 접근이 가능합니다");
		model.addAttribute("icon","warning");
		model.addAttribute("loc","/");
		return "common/msg";
	}
	
	@GetMapping("/blockMsg")
	public String blockMsg(Model model) {
		model.addAttribute("title","이용정지");
		model.addAttribute("msg","이용정지중입니다.");
		model.addAttribute("icon","warning");
		model.addAttribute("loc","/");
		return "common/msg";
	}
}
