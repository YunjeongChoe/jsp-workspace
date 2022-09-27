package com.study.mypage.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.login.vo.UserVO;
import com.study.member.service.IMemberService;
import com.study.member.service.MemberServiceImpl;
import com.study.member.vo.MemberVO;
import com.study.servlet.Handler;

public class Modify implements Handler {
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	
		MemberVO member = new MemberVO();
		BeanUtils.populate(member, req.getParameterMap());
		req.setAttribute("member", member);
		
		
		
			IMemberService memberService = new MemberServiceImpl();
			ResultMessageVO resultMessageVO = new ResultMessageVO();
			
			try{
			memberService.modifyMember(member);
			
			HttpSession session=req.getSession();
			UserVO user = (UserVO)session.getAttribute("USER_INFO");
			
			user.setUserName(member.getMemName());
			user.setUserPass(member.getMemPass());
			session.setAttribute("USER_INFO", user);
			
			resultMessageVO.messageSetting(true, "MODIFY", "수정성공", "/member/memberList.wow", "목록으로");

			}catch(BizNotFoundException bnf){
				resultMessageVO.messageSetting(false, "NotFound", "수정실패", "/member/memberList.wow", "목록으로");

			}catch(BizNotEffectedException bne){
				resultMessageVO.messageSetting(false, "WrongPassword", "수정실패", "/member/memberList.wow", "목록으로");

			}
			req.setAttribute("resultMessageVO", resultMessageVO);

			return "common/message";
	}
}
