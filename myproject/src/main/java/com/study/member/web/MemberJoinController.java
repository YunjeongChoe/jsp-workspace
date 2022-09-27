package com.study.member.web;
import java.util.List;
import javax.inject.Inject;
import javax.mail.MessagingException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
import com.study.exception.BizNotFoundException;
import com.study.member.service.IMemberService;
import com.study.member.service.MailSendService;
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
	
	
	
	@ModelAttribute("hobbyList")
	public List<CodeVO> hobbyList() {
		return codeService.getCodeListByParent("HB00");
	}
	
	@RequestMapping("/join/step1.wow")
	public String step1(Model model, @ModelAttribute("member") MemberVO member) {
			return "join/step1";
	}
	
	@RequestMapping("/join/step2.wow")
	public String step2(@ModelAttribute("member") @Validated(value = {Step1.class}) MemberVO member
			,BindingResult error) {
		if(error.hasErrors()) {
			return "join/step1";
		}
		return "join/step2";
	}
	
	
	@RequestMapping("/join/regist.wow")
	public String regist(Model model, @ModelAttribute("member") @Validated(value = {Step2.class}) MemberVO member, BindingResult error, SessionStatus sessionStatus) {
		System.out.println("member"+member);
		ResultMessageVO resultMessageVO = new ResultMessageVO();
		if(error.hasErrors()) {
			return "join/step2";
		}
		try {
			memberService.registMember(member);
			sessionStatus.setComplete(); // SessionAtt에 있는 값 제거(여기서는 "member")
			
			resultMessageVO.messageSetting(true, "회원 등록 성공 ", "회원을 등록했습니다.", "/vege/recipe.wow", "목록으로");
			model.addAttribute("resultMessageVO", resultMessageVO);
		} catch (BizNotEffectedException ene) {
			resultMessageVO.messageSetting(false, "회원 삭제 실패", "회원을 삭제하는데 실패했습니다.", "/vege/recipe.wow", "목록으로");
			model.addAttribute("resultMessageVO", resultMessageVO);
		} catch (BizDuplicateKeyException ede) {
			resultMessageVO.messageSetting(false, "회원 등록 실패", "회원아이디가 이미 존재합니다.", "/vege/recipe.wow", "목록으로");
			model.addAttribute("resultMessageVO", resultMessageVO);
		}
		return "common/message";
	}
	
	
	@ResponseBody
	@RequestMapping("/join/idCheck.wow")
	public String idCheck(String id) {
		//id가 사용할 수 있는거면 return success 없는거면 return fail
		System.out.println(id);
		try {
			MemberVO member=memberService.getMember(id);
			return "fail";
		}catch (BizNotFoundException e) {
			return "success";
		}
	}
	
	@Inject
	MailSendService mailSendService;
	
	@ResponseBody
	@RequestMapping("/join/mailAuth.wow")
	public String mailAuth(String mail) throws MessagingException {
		String randomKey=mailSendService.sendAuthMail(mail); //mail : gks930620@naver.com
		return randomKey;
	}
	
	@RequestMapping("/join/mailWindow.wow")
	public String mailWindow() {
		return "join/mailWindow";
	}
	
	
	
	
}