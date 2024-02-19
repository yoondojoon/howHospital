package kr.or.iei;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping(value="/")
	public String home() {
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
