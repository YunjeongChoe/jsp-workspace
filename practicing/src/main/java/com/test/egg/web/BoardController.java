package com.test.egg.web;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.egg.dao.IBoardDao;
import com.test.egg.vo.BoardVO;

@Controller
public class BoardController {
	
	@Inject
	IBoardDao boardDao;
	
	@RequestMapping("/board/board.wow")
	public String Board(Locale locale, Model model) {
		List<BoardVO> boardList = boardDao.getBoardList();
		model.addAttribute("boardList", boardList);
		return "board/board";
	}

}
