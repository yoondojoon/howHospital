package kr.or.iei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.or.iei.community.model.dto.CommunityListData;
import kr.or.iei.community.model.service.CommunityService;

@Controller
public class HomeController {
	@Autowired
	private CommunityService communityService;
	
	@GetMapping(value="/")
	public String home(Model model) {
		String keyword = "";
		int reqPage = 1;
		CommunityListData cld = communityService.selectCommunityList(keyword, reqPage);
		if(!cld.getList().isEmpty()) {
			model.addAttribute("boardList", cld.getList());
			model.addAttribute("pageNavi", cld.getPageNavi());
		}
		return "index";
	}
	
	@GetMapping(value="/ref")
	public String ref() {
		return "ref";
	}
	
	@GetMapping(value="/template")
	public String template() {
		return "template";
	}
	
}
