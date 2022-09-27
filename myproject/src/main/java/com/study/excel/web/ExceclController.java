package com.study.excel.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.excel.service.SimpleExcelService;
import com.study.free.service.IFreeBoardService;
import com.study.free.vo.FreeBoardSearchVO;
import com.study.free.vo.FreeBoardVO;

@Controller
public class ExceclController {

	@Inject
	IFreeBoardService freeBoardService;

	@ResponseBody
	@RequestMapping("/free/excelDown")
	public void freeExcelDown(HttpServletResponse response, @ModelAttribute("searchVO") FreeBoardSearchVO searchVO)
			throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		// 10개만 보여주는 freeBoardExcel
		List<FreeBoardVO> freeBoardList = freeBoardService.getBoardList(searchVO);
		SimpleExcelService excelService=new SimpleExcelService("free엑셀", FreeBoardVO.class, freeBoardList);
		excelService.createExcel();
		excelService.outputStream(response);



	}

}
