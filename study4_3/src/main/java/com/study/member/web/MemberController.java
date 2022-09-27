package com.study.member.web;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.code.service.ICommCodeService;
import com.study.code.vo.CodeVO;
import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizDuplicateKeyException;
import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.member.service.IMemberService;
import com.study.member.vo.MemberSearchVO;
import com.study.member.vo.MemberVO;

@Controller
public class MemberController {

	//list view edit modify delete  form regist
	
	@Inject
	IMemberService memberService;
	
	@Inject
	ICommCodeService codeService;
	
	@ModelAttribute("jobList")
	public List<CodeVO> jobList(){
		return codeService.getCodeListByParent("JB00");
	}
	
	@ModelAttribute("hobbyList")
	public List<CodeVO> hobbyList(){
		return codeService.getCodeListByParent("HB00");
	}
	
	
	
	
	@RequestMapping ("member/memberList.wow")
	public String memberList(Model model, @ModelAttribute("searchVO") MemberSearchVO searchVO) {
		
		List<MemberVO> memberList = memberService.getMemberList(searchVO);
		model.addAttribute("memberList", memberList);

		return "member/memberList";
	}
	
	
	@RequestMapping ("member/memberView.wow")
	public String memberView(Model model, String memId) {
		
		try{
			MemberVO member=memberService.getMember(memId);
			model.addAttribute("member", member);
			return "member/memberView";
		}catch (BizNotFoundException enf){
			//에러가 났을 때 freeView에 있는 너무 간단한 화면말고 message.jsp로 이동하자
			ResultMessageVO resultMessageVO=new ResultMessageVO();
			resultMessageVO.messageSetting
			(false, "회원 찾기 실패", "회원을 찾는데 실패했습니다.",
					"/member/memberList.wow", "목록으로");
			model.addAttribute("resultMessageVO", resultMessageVO);
			return "common/message";
		}
	}
	
	
	@RequestMapping ("member/memberEdit.wow")
	public String memberEdit(Model model, String memId) {

		try {
			MemberVO member = memberService.getMember(memId);
			model.addAttribute("member", member);
			return "member/memberEdit";
		} catch (BizNotFoundException enf) {
			//에러가 났을 때 freeView에 있는 너무 간단한 화면말고 message.jsp로 이동하자
			ResultMessageVO resultMessageVO=new ResultMessageVO();
			resultMessageVO.messageSetting
			(false, "회원 찾기 실패", "회원을 찾는데 실패했습니다.",
					"/member/memberList.wow", "목록으로");
			model.addAttribute("resultMessageVO", resultMessageVO);
			return "common/message";
		}

	}
		
	
	
	
	@PostMapping ("member/memberModify.wow")
	public String memberModify(Model model, @ModelAttribute("member") MemberVO member) {
		
		try {
			memberService.modifyMember(member);
			ResultMessageVO resultMessageVO=new ResultMessageVO();
			resultMessageVO.messageSetting
			(true, "회원 수정 성공 ", "회원정보를 수정했습니다.",
					"/member/memberList.wow", "목록으로");
			model.addAttribute("resultMessageVO", resultMessageVO);
			return "common/message";
		} catch (BizNotEffectedException ene) {
			ResultMessageVO resultMessageVO=new ResultMessageVO();
			resultMessageVO.messageSetting
			(false, "회원 삭제 실패", "회원을 삭제하는데 실패했습니다.",
					"/member/memberList.wow", "목록으로");
			model.addAttribute("resultMessageVO", resultMessageVO);
			return "common/message";
		} catch (BizNotFoundException enf) {
			//에러가 났을 때 freeView에 있는 너무 간단한 화면말고 message.jsp로 이동하자
			ResultMessageVO resultMessageVO=new ResultMessageVO();
			resultMessageVO.messageSetting
			(false, "회원 찾기 실패", "회원을 찾는데 실패했습니다.",
					"/member/memberList.wow", "목록으로");
			model.addAttribute("resultMessageVO", resultMessageVO);
			return "common/message";
		}
		
	}
	
	
	
	@PostMapping ("member/memberDelete.wow")
	public String memberDelete(Model model, @ModelAttribute("member") MemberVO member) {

		try {
			memberService.removeMember(member);
			ResultMessageVO resultMessageVO=new ResultMessageVO();
			resultMessageVO.messageSetting
			(true, "회원 삭제 성공 ", "회원 삭제했습니다.",
					"/member/memberList.wow", "목록으로");
			model.addAttribute("resultMessageVO", resultMessageVO);
			return "common/message";
		} catch (BizNotEffectedException ene) {
			ResultMessageVO resultMessageVO=new ResultMessageVO();
			resultMessageVO.messageSetting
			(false, "회원 삭제 실패", "회원을 삭제하는데 실패했습니다.",
					"/member/memberList.wow", "목록으로");
			model.addAttribute("resultMessageVO", resultMessageVO);
			return "common/message";
		} catch (BizNotFoundException enf) {
			//에러가 났을 때 freeView에 있는 너무 간단한 화면말고 message.jsp로 이동하자
			ResultMessageVO resultMessageVO=new ResultMessageVO();
			resultMessageVO.messageSetting
			(false, "회원 찾기 실패", "회원을 찾는데 실패했습니다.",
					"/member/memberList.wow", "목록으로");
			model.addAttribute("resultMessageVO", resultMessageVO);
			return "common/message";
		}
		
	}

	
	@RequestMapping ("member/memberForm.wow")
	public String memberForm(Model model) {
		
		return "member/memberForm";
	}
	
	
	@PostMapping ("member/memberRegist.wow")
	public String memberRegist(Model model, @ModelAttribute("member") MemberVO member) {
	
		try {
			memberService.registMember(member);
			ResultMessageVO resultMessageVO=new ResultMessageVO();
			resultMessageVO.messageSetting
			(true, "회원 등록 성공 ", "회원을 등록했습니다.",
					"/member/memberList.wow", "목록으로");
			model.addAttribute("resultMessageVO", resultMessageVO);
			return "common/message";
		} catch (BizNotEffectedException ene) {
			ResultMessageVO resultMessageVO=new ResultMessageVO();
			resultMessageVO.messageSetting
			(false, "회원 삭제 실패", "회원을 삭제하는데 실패했습니다.",
					"/member/memberList.wow", "목록으로");
			model.addAttribute("resultMessageVO", resultMessageVO);
			return "common/message";
		} catch (BizDuplicateKeyException ede) {
			ResultMessageVO resultMessageVO=new ResultMessageVO();
			resultMessageVO.messageSetting
			(false, "회원 등록 실패", "회원아이디가 이미 존재합니다.",
					"/member/memberList.wow", "목록으로");
			model.addAttribute("resultMessageVO", resultMessageVO);
			return "common/message";
		}	
		
	}
	
	
	
	
	
}
