package kr.or.iei.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/community")
public class CommunityController {

	@GetMapping(value="/searchAll")
	public String searchAll(String keyword, Model model) {
		model.addAttribute("keyword", keyword);
		return "index";
	}

	@GetMapping(value="/communityMain")
	public String communityMain() {
		return "/community/communityMain";
	}
}
