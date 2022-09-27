package com.study.login.web;

import java.net.URLEncoder;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.common.util.CookieUtils;
import com.study.login.service.ILoginService;
import com.study.login.vo.UserVO;

@Controller
public class LoginController {
	
	@Inject
	ILoginService loginService;
	
	
	@GetMapping("/login/login.wow")
	public String loginGet() {
		return "login/login";
	}

	@PostMapping("/login/login.wow")
	public String loginPost(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// 사용자가 id,pass입력해서 로그인버튼 누름
		String id = req.getParameter("userId");
		String pw = req.getParameter("userPass");
		String save_id = req.getParameter("rememberMe");
		if (save_id == null) {
			CookieUtils cookieUtils = new CookieUtils(req);
			if (cookieUtils.exists("SAVE_ID")) {
				Cookie cookie = CookieUtils.createCookie("SAVE_ID", id, "/", 0);
				resp.addCookie(cookie);
			}
			save_id = "";
		}

		if ((id == null || id.isEmpty()) || (pw == null || pw.isEmpty())) {
			return "redirect:" + "/login/login.wow?msg=" + URLEncoder.encode("입력안했어요", "utf-8");
			// 직접 redirect보다는 DS한테 나 redirect하고싶어 를 알려주는게...
		} else {
			// DB에 있는 member테이블을 조회해서 로그인가능여부판단.
			UserVO user = loginService.getUser(id);
			if (user == null) {
				return "redirect:"  + "/login/login.wow?msg="
						+ URLEncoder.encode("아이디 또는 비번확인", "utf-8");
			} else { // id맞았을때
				if (user.getUserPass().equals(pw)) {// 다 맞는경우
					if (save_id.equals("Y")) {
						resp.addCookie(CookieUtils.createCookie("SAVE_ID", id, "/", 3600 * 24 * 7));
					}
					HttpSession session = req.getSession();
					session.setAttribute("USER_INFO", user);
					return "redirect:" + "/";
					// redirect:/study3
				} else {// 비번만 틀린경우
					return "redirect:" + "/login/login.wow?msg="
							+ URLEncoder.encode("아이디 또는 비번확인", "utf-8");
				}

			}
		}
	}
	
	@RequestMapping("/login/logout.wow")
	public String logout(	HttpSession session, HttpServletRequest req) {
				session.removeAttribute("USER_INFO");
				return "redirect:"+"/";  
	}
	
	
}