package com.study.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.code.service.CommCodeServiceImpl;
import com.study.code.service.ICommCodeService;
import com.study.code.vo.CodeVO;
import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.free.service.FreeBoardServiceImpl;
import com.study.free.service.IFreeBoardService;
import com.study.free.vo.FreeBoardSearchVO;
import com.study.free.vo.FreeBoardVO;

public class A01SimpleController extends HttpServlet {
	
	//지금까지는 doGet했지만 이 메소드는 get방식만 처리.
	//post방식도 했었는데 get/post/기타 등등 전부 한번에 처리하려면 (service)
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//요청은 localhost:8080/study3/free/freeList.wow,
		//	/free/freeView.wow  ,	/free/freeEdit.wow 등등으로
		//브라우저가 view로 요청했는지, edit으로 요청했는지, list로 요청했는지..
		
		String uri = req.getRequestURI();
		uri = uri.substring(req.getContextPath().length());
		
		if(uri.equals("/free/freeList.wow")) {
			
			FreeBoardSearchVO searchVO = new FreeBoardSearchVO();
			
			IFreeBoardService freeBoardService = new FreeBoardServiceImpl();
			//searchVO는 curPage만 세팅된 값
			List<FreeBoardVO> freeBoardList = freeBoardService.getBoardList(searchVO);
			//이 이후의 paging VO는 전부 세팅된 값
			req.setAttribute("freeBoardList", freeBoardList);
			
			ICommCodeService codeService = new CommCodeServiceImpl();
			List<CodeVO> cateList = codeService.getCodeListByParent("BC00");
			req.setAttribute("cateList", cateList);
			
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/free/freeList.jsp");
			rd.forward(req, resp);
			
			
		}else if(uri.equals("/free/freeView.wow")) {
			int boNo = Integer.parseInt(req.getParameter("boNo"));	
			IFreeBoardService freeBoardService = new FreeBoardServiceImpl();
			
			try{
				freeBoardService.increaseHit(boNo);
				FreeBoardVO freeBoard = freeBoardService.getBoard(boNo);
				req.setAttribute("freeBoard", freeBoard);
			}catch(BizNotFoundException | BizNotEffectedException bne){
				req.setAttribute("bne", bne);
			}
			
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/free/freeView.jsp");
			rd.forward(req, resp);
			
			
		}else if(uri.equals("/free/freeEdit.wow")) {

			int boNo = Integer.parseInt(req.getParameter("boNo"));
			IFreeBoardService freeBoardService = new FreeBoardServiceImpl();
			
			try{
				FreeBoardVO freeBoard = freeBoardService.getBoard(boNo);
				req.setAttribute("freeBoard", freeBoard);
			}catch(BizNotFoundException e){
				req.setAttribute("e", e);
			}

			ICommCodeService codeService = new CommCodeServiceImpl();
			List<CodeVO> categoryList = codeService.getCodeListByParent("BC00");
			req.setAttribute("categoryList", categoryList);
			
			
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/free/freeEdit.jsp");
			rd.forward(req, resp);
			
		}
		//uri가 1000개면 switch문..?
		
		
		
	}
	
	
	
	
	
	
}
