package com.study.member.web;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.study.code.service.ICommCodeService;
import com.study.code.vo.CodeVO;
import com.study.common.valid.Step1;
import com.study.common.valid.Step2;
import com.study.common.valid.Step3;
import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizDuplicateKeyException;
import com.study.exception.BizNotEffectedException;
import com.study.member.service.IMemberService;
import com.study.member.vo.MemberVO;


@SessionAttributes("member") //이 컨트롤러에서만 유지되는 세션 
//이 컨트롤러에서 model의 "member"값을 유지시키는 용도
@Controller
public class MemberJoinController {
	
	@Inject
	ICommCodeService codeService;
	
	@Inject
	IMemberService memberService;
	
	
	@ModelAttribute("member")
	public MemberVO member() {
		return new MemberVO();
	}
	
	
	@ModelAttribute("jobList")
	public List<CodeVO> jobList() {
		return codeService.getCodeListByParent("JB00");
	}
	
	@ModelAttribute("hobbyList")
	public List<CodeVO> hobbyList() {
		return codeService.getCodeListByParent("HB00");
	}
	
	@RequestMapping("/join/step1.wow")
	public String step1(Model model, @ModelAttribute("member") @Validated(value = {Step3.class}) MemberVO member,BindingResult error, SessionStatus sessionStatus) {
		if(error.hasErrors()) {
			return "join/step1";

		}
		try {
			memberService.registMember(member);
			sessionStatus.setComplete(); //sessionAttribute에 있는 값(여기서는 "member") 제거
			
			ResultMessageVO resultMessageVO=new ResultMessageVO();
			resultMessageVO.messageSetting
			(true, "회원 등록 성공 ", "회원을 등록했습니다.", "/member/memberList.wow", "목록으로");
			model.addAttribute("resultMessageVO", resultMessageVO);
			return "common/message";
		} catch (BizNotEffectedException ene) {
			ResultMessageVO resultMessageVO=new ResultMessageVO();
			resultMessageVO.messageSetting
			(false, "회원 등록 실패", "회원을 등록하는데 실패했습니다.", "/member/memberList.wow", "목록으로");
			model.addAttribute("resultMessageVO", resultMessageVO);
			return "common/message";
		} catch (BizDuplicateKeyException ede) {
			ResultMessageVO resultMessageVO=new ResultMessageVO();
			resultMessageVO.messageSetting
			(false, "회원 등록 실패", "회원아이디가 이미 존재합니다.","/member/memberList.wow", "목록으로");
			model.addAttribute("resultMessageVO", resultMessageVO);
	
		}	
		return "common/message";
		
	}
	
	@RequestMapping("/join/step2.wow")
	public String step2(@ModelAttribute("member") @Validated(value = {Step1.class}) MemberVO member
			,BindingResult error) {
		if(error.hasErrors()) {
			return "join/step1";
		}
		return "join/step2";
	}
	
	@RequestMapping("/join/step3.wow")
	public String step3(@ModelAttribute("member") @Validated(value = {Step2.class}) MemberVO member
			,BindingResult error) {
		if(error.hasErrors()) {
			return "join/step2";
		}
		return "join/step3";
	}
	
	@RequestMapping("/join/regist.wow")
	public String regist(@ModelAttribute("member") @Validated(value = {Step3.class}) MemberVO member
		,BindingResult error) {
			if(error.hasErrors()) {
				return "join/step3";
			}
		return "common/message";
	}
	
	@RequestMapping("/join/cancel")
	public String cancel(@ModelAttribute("member") MemberVO member) {
		
		return "redirect:/";
	}
	
	
	
	
	
}