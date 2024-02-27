package kr.or.iei.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.iei.FileUtils;
import kr.or.iei.community.model.dto.CommunityListData;
import kr.or.iei.community.model.service.CommunityService;

@Controller
@RequestMapping(value="/community")
public class CommunityController {
	@Autowired
	private CommunityService communityService;
	@Value("${file.root}")
	private String root;
	@Autowired
	private FileUtils fileUtils;
	
	@GetMapping(value="/searchAll")
	public String searchAll(String keyword, Model model) {
		model.addAttribute("keyword", keyword);
		return "/communityMain";
	}

	@GetMapping(value="/communityMain")
	public String communityMain(String keyword, int reqPage) {
		CommunityListData cld = communityService.selectCommunityList(keyword, reqPage);
		return "/community/communityMain";
	}
}










