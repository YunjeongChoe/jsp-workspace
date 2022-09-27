package com.study.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.code.service.CommCodeServiceImpl;
import com.study.code.service.ICommCodeService;
import com.study.code.vo.CodeVO;
import com.study.free.service.FreeBoardServiceImpl;
import com.study.free.service.IFreeBoardService;
import com.study.free.vo.FreeBoardSearchVO;
import com.study.free.vo.FreeBoardVO;

public class A00SimpleFreeListController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//어떤 작업을 하고나서 /WEB-INF/views/free/freeList.jsp로 포워딩
		//어떤 작업 : JSP에 있는 자바코드
		//<jsp:useBean id="searchVO" class="com.study.free.vo.FreeBoardSearchVO"></jsp:useBean>
		//<jsp:setProperty property="*" name="searchVO"/>
		//파라미터들 전부 VO에 한번에 세팅해주는 것. 대신 lib가 필요.
		
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
		
		
		
		
	}
	

}
