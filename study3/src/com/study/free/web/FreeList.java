package com.study.free.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.study.code.service.CommCodeServiceImpl;
import com.study.code.service.ICommCodeService;
import com.study.code.vo.CodeVO;
import com.study.free.service.FreeBoardServiceImpl;
import com.study.free.service.IFreeBoardService;
import com.study.free.vo.FreeBoardSearchVO;
import com.study.free.vo.FreeBoardVO;
import com.study.servlet.Handler;


public class FreeList implements Handler{
	

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		//원래 freeList에서 하던 코드 작성
		FreeBoardSearchVO searchVO = new FreeBoardSearchVO();  //값이 없는 searchVO
		BeanUtils.populate(searchVO, req.getParameterMap());
		//이후부터는 searchVO에 값이 세팅됨
		req.setAttribute("searchVO", searchVO);
		

		IFreeBoardService freeBoardService = new FreeBoardServiceImpl();
		//searchVO는 curPage만 세팅된 값
		List<FreeBoardVO> freeBoardList = freeBoardService.getBoardList(searchVO);
		//이 이후의 paging VO는 전부 세팅된 값
		req.setAttribute("freeBoardList", freeBoardList);
		
		ICommCodeService codeService = new CommCodeServiceImpl();
		List<CodeVO> cateList = codeService.getCodeListByParent("BC00");
		req.setAttribute("cateList", cateList);
		
		
		return "/free/freeList";
	}
	

	
	

}
