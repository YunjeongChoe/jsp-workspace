package com.study.mypage.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizNotFoundException;
import com.study.login.vo.UserVO;
import com.study.member.service.IMemberService;
import com.study.member.service.MemberServiceImpl;
import com.study.member.vo.MemberVO;
import com.study.servlet.Handler;

public class Info implements Handler {
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		HttpSession session=req.getSession();
		UserVO user=(UserVO)session.getAttribute("USER_INFO");
		
		if(user == null) {
			return "redirect:"+req.getContextPath()+"/login/login.wow";
		}
		
		IMemberService memberService = new MemberServiceImpl();
		String memId = req.getParameter("memId");
		try{
			MemberVO member = memberService.getMember(memId);
			req.setAttribute("member", member);

		}catch(BizNotFoundException bne){
			ResultMessageVO resultMessageVO = new ResultMessageVO();
			resultMessageVO.messageSetting(false, "NotFound", "찾을 수 없음", "/member/memberList.wow", "목록으로");
			req.setAttribute("resultMessageVO", resultMessageVO);
			return "common/message";
		}
		
		return "mypage/info";
		
	}
}
