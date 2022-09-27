package com.study.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityQuickController {
	
	@RequestMapping("/securityQuick/index")
	public String index(Model model) {
		return "securityQuick/index";
	}
	
	@RequestMapping("/securityQuick/admin/main")
	public String adminMain(Model model) {
		return "securityQuick/adminMain";
	}
	
	@RequestMapping("/securityQuick/manager/main")
	public String managerMain(Model model) {
		return "securityQuick/managerMain";
	}
	
	@RequestMapping("/securityQuick/member/main")
	public String memberMain(Model model) {
		return "securityQuick/memberMain";
	}
	
	
}
