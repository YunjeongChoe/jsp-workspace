package com.study.free.web;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.groups.Default;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.code.service.CommCodeServiceImpl;
import com.study.code.service.ICommCodeService;
import com.study.code.vo.CodeVO;
import com.study.common.valid.Modify;
import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.exception.BizPasswordNotMatchedException;
import com.study.free.service.FreeBoardServiceImpl;
import com.study.free.service.IFreeBoardService;
import com.study.free.vo.FreeBoardSearchVO;
import com.study.free.vo.FreeBoardVO;

@Controller
public class FreeBoardController {

	//list view edit modify delete  form regist
	@Inject
	IFreeBoardService freeBoardService;

	@Inject
	ICommCodeService codeService;
	//new로 해서 쓰는거보다 @Inject해서 만들어진걸로 쓰면 됨
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@ModelAttribute("cateList") //이 컨트롤러 안에 있는 모든 메소드들에서 공통적으로 다 model에 데이터가 담긴다
	public List<CodeVO> cateList(){
		return codeService.getCodeListByParent("BC00");
	}
	
	
	
	
	//매개변수로 온 거를 model에 담을때는 @modelAttribute사용 
	//파라미터가 여러개일 때는 VO
	@RequestMapping("/free/freeList.wow")
	public String freeList(Model model, @ModelAttribute("searchVO") FreeBoardSearchVO searchVO) {
		logger.info("searchVO : {}", searchVO);
		List<FreeBoardVO> freeBoardList=freeBoardService.getBoardList(searchVO); 
		model.addAttribute("freeBoardList", freeBoardList);
		
		return "free/freeList";
	}
	
	
	
	
	@RequestMapping("/free/freeView.wow")
	//파라미터가 한개일 때는 변수쓰기
	public String freeView(Model model, @RequestParam(required = false) int boNo) {
		
		try{
			FreeBoardVO freeBoard=freeBoardService.getBoard(boNo);
			freeBoardService.increaseHit(boNo);
			model.addAttribute("freeBoard", freeBoard);
			return "free/freeView";
		}catch (BizNotFoundException  | BizNotEffectedException  e){
			ResultMessageVO resultMessageVO=new ResultMessageVO();
			resultMessageVO.messageSetting(false, 
					"NotFound or NotEffected", "글이없다 or조회수증가실패", "/free/freeList.wow", "목록으로");
			model.addAttribute("resultMessageVO", resultMessageVO);
			return "common/message";
		}
		
	}
	
	
	
	@RequestMapping("/free/freeEdit.wow")
	public String freeEdit(Model model, int boNo) {
		try{ 
			FreeBoardVO freeBoard=freeBoardService.getBoard(boNo);
			model.addAttribute("freeBoard", freeBoard);
			
			return "free/freeEdit";
		}catch(BizNotFoundException e){
			ResultMessageVO resultMessageVO=new ResultMessageVO();
			resultMessageVO.messageSetting(false, 
					"NotFound", "글이없다", "/free/freeList.wow", "목록으로");
			model.addAttribute("resultMessageVO", resultMessageVO);
			return "common/message";
		}
	
	}
	
	//수정할 때 post방식으로 옴. @RM은 get, post 기타 등등 다 수용한다.
	//freeModify.wow를 post만 허용하게 하려면?
	//@RequestMapping(value = "/free/freeModify.wow", method = RequestMethod.POST)
	@PostMapping("/free/freeModify.wow")
	public String freeModify(Model model, @ModelAttribute("freeBoard")@Validated(value = {Modify.class}) FreeBoardVO freeBoard, BindingResult error) {
		
		//freeBoard의 boNo가 있는지, boPass가 null이 아닌지, email형식인지, 전화번호 형식, 비밀번호 영문숫자 조합인지 등을 검사.
		//검사격과 객체는 대상 객체 바로 뒤에. ex. freeBoard 뒤에 BindingResult가 와야함
		if(error.hasErrors()) {
			return "free/freeEdit";
		}
		
		//이 밑은 문제가 없을 때 
		ResultMessageVO resultMessageVO= new ResultMessageVO();
		
		try{
			freeBoardService.modifyBoard(freeBoard);
			resultMessageVO.messageSetting(true, 
					"MODIFY", "수정성공", "/free/freeList.wow", "목록으로");
		}catch (BizNotFoundException bnf){
			resultMessageVO.messageSetting(false, 
					"NotFound", "수정실패", "/free/freeList.wow", "목록으로");
		}catch (BizNotEffectedException bne){
			resultMessageVO.messageSetting(false, 
					"NotEffected", "수정실패", "/free/freeList.wow", "목록으로");
		}catch(BizPasswordNotMatchedException bpn){
			resultMessageVO.messageSetting(false, 
					"비밀번호틀림", "수정실패", "/free/freeList.wow", "목록으로");
		}
		model.addAttribute("resultMessageVO", resultMessageVO);
		return "common/message";
	}	
	
//	//freeBoard 데이터가 잘 왔는지 검사, 문제가 없을때 true
//	private boolean modifyHasNotError(FreeBoardVO freeBoard) {
//		if(freeBoard.getBoNo() == 0) return false;
//		if(freeBoard.getBoPass() == null || freeBoard.getBoPass().isEmpty()) return false;
//		
//		return true;
//	}
	
	
	@PostMapping("/free/freeDelete.wow")
	public String freeDelete(Model model, @ModelAttribute("freeBoad") FreeBoardVO freeBoard) {
		
		ResultMessageVO resultMessageVO= new ResultMessageVO();
		
		try{
			freeBoardService.removeBoard(freeBoard);
			resultMessageVO.messageSetting(true, 
					"DELETE", "삭제성공", "/free/freeList.wow", "목록으로");
		}catch (BizNotFoundException bnf){
			resultMessageVO.messageSetting(false, 
					"NotFound", "삭제실패", "/free/freeList.wow", "목록으로");
		}catch (BizNotEffectedException bne){
			resultMessageVO.messageSetting(false, 
					"NotEffected", "삭제실패", "/free/freeList.wow", "목록으로");
		}catch(BizPasswordNotMatchedException bpn){
			resultMessageVO.messageSetting(false, 
					"비밀번호틀림", "비밀번호틀렸다 너.  글쓴사람 아니지?", "/free/freeList.wow", "목록으로");
		}
		model.addAttribute("resultMessageVO", resultMessageVO);
		
		return "common/message";
	}

	
	
	
	
	@RequestMapping("/free/freeForm.wow")
	public String freeForm(Model model, @ModelAttribute("codeService") ICommCodeService codeService) {
		
		return "free/freeForm";
	}
	
	
	
	
	@PostMapping("/free/freeRegist.wow")
	public String freeRegist(Model model, @ModelAttribute("freeBoard")@Validated() FreeBoardVO freeBoard, BindingResult error) {
		if(error.hasErrors()) {
			return "free/freeForm";
		}
		ResultMessageVO resultMessageVO= new ResultMessageVO();
		try {
			freeBoardService.registBoard(freeBoard);
			resultMessageVO.messageSetting(true, 
					"REGIST", "등록성공", "/free/freeList.wow", "목록으로");
		} catch (BizNotEffectedException e) {
			resultMessageVO.messageSetting(false, 
					"NotEffected", "등록실패", "/free/freeList.wow", "목록으로");
		}
		model.addAttribute("resultMessageVO", resultMessageVO);
		return "common/message";
	}
	
	

	
}
