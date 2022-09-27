package com.study.member.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.study.code.service.CommCodeServiceImpl;
import com.study.code.service.ICommCodeService;
import com.study.code.vo.CodeVO;
import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizNotFoundException;
import com.study.member.service.IMemberService;
import com.study.member.service.MemberServiceImpl;
import com.study.member.vo.MemberVO;
import com.study.servlet.Handler;

public class MemberEdit implements Handler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		
		
		
		String memId = req.getParameter("memId");
		IMemberService memberService = new MemberServiceImpl();
		
		try{
			MemberVO member = memberService.getMember(memId);
			req.setAttribute("member", member);
			return "member/memberEdit";
		}catch (BizNotFoundException e){
			ResultMessageVO resultMessageVO = new ResultMessageVO();
			resultMessageVO.messageSetting(false, "NotFound", "수정실패", "/member/memberList.wow", "목록으로");
		}

		ICommCodeService codeService = new CommCodeServiceImpl();
		List<CodeVO> jobList = codeService.getCodeListByParent("JB00");
		req.setAttribute("jobList", jobList);
		List<CodeVO> hobbyList = codeService.getCodeListByParent("HB00");
		req.setAttribute("hobbyList", hobbyList);	
		
		return "common/message";
	
	}
}
