package com.study.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.free.service.FreeBoardServiceImpl;
import com.study.free.service.IFreeBoardService;
import com.study.free.vo.FreeBoardVO;

public class A00SimpleFreeViewController extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	//freeView.jsp에서 소스코드 옮기기, jsp에서는 자바코드 삭제
	// /WEB-INF/view/free/freeView.jsp로 포워딩 
	
	
	
	//Exception : BizException, DaoException(직접 만든거)
	//BizException은 Service단에서 논리적 오류 
	//DaoException은 SQLException 발생 시 에러 강제 발생

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
			
			
			
	}
	
	
	
	
	

}
