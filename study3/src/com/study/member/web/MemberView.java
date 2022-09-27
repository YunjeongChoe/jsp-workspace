package com.study.member.web;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.code.service.CommCodeServiceImpl;
import com.study.code.service.ICommCodeService;
import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizNotFoundException;
import com.study.member.service.IMemberService;
import com.study.member.service.MemberServiceImpl;
import com.study.member.vo.MemberVO;
import com.study.servlet.Handler;


public class MemberView implements Handler {

	
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		
		IMemberService memberService = new MemberServiceImpl();
		String memId = req.getParameter("memId");
		try{
			MemberVO member = memberService.getMember(memId);
			req.setAttribute("member", member);
			System.out.println("member"+member);
			return "/member/memberView";
		}catch(BizNotFoundException bne){
			ResultMessageVO resultMessageVO = new ResultMessageVO();
			resultMessageVO.messageSetting(false, "NotFound", "찾을 수 없음", "/member/memberList.wow", "목록으로");
			req.setAttribute("resultMessageVO", resultMessageVO);
			return "common/message";
		}
	}
}
