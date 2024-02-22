package kr.or.iei.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
		return "/admin/noticeDetail?noticeNo="+noticeNo;
	}
	
	@GetMapping(value="/noticeDelete")
	public String noticeDelete(int noticeNo) {
		int result = adminService.deleteNotice(noticeNo);
		return "redirect:/admin/noticeList?reqPage=1";
	}
}
