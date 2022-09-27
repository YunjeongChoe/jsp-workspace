package com.study.member.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.study.code.service.CommCodeServiceImpl;
import com.study.code.service.ICommCodeService;
import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.member.dao.MemberDaoOracle;
import com.study.member.service.IMemberService;
import com.study.member.service.MemberServiceImpl;
import com.study.member.vo.MemberVO;
import com.study.servlet.Handler;

public class MemberDelete implements Handler {


	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		MemberVO member = new MemberVO();
		BeanUtils.populate(member, req.getParameterMap());
		req.setAttribute("member", member);
		
		ResultMessageVO resultMessageVO = new ResultMessageVO();
		
			IMemberService memberService = new MemberServiceImpl();
			try{
				memberService.removeMember(member);
				resultMessageVO.messageSetting(true, "REMOVE", "회원삭제성공", "/member/memberList.wow", "목록으로");

				}catch(BizNotFoundException bnf){
					resultMessageVO.messageSetting(false, "NotFound", "삭제실패", "/member/memberList.wow", "목록으로");
				}catch(BizNotEffectedException bne){
					resultMessageVO.messageSetting(false, "NotEffected", "삭제실패", "/member/memberList.wow", "목록으로");
				}
			
			req.setAttribute("resultMessageVO", resultMessageVO);

			return "common/message";
		
	}
}
