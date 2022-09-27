package com.study.user.web;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.user.service.IUserService;
import com.study.user.vo.UserVO;


@Controller
public class UserController {
	
	@Inject
	IUserService userService;
	
	@RequestMapping("/userList.wow")
	public String userViewList(Model model, @ModelAttribute("userVO") UserVO userVO) {
		List<UserVO> userList = userService.getUserList();
		model.addAttribute("userList", userList);
		return "userList";
	}
}
