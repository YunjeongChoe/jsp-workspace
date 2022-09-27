package com.five.jo.web;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.five.jo.dao.IAlcoholDao;
import com.five.jo.vo.AlcoholVO;

@Controller
public class AlcoholController {
	
	@Inject
	IAlcoholDao alcoholDao;
	
	@RequestMapping("soju/sojuList.wow")
	public String alcoholList(Model model) {
		List<AlcoholVO> alcoholList = alcoholDao.alcoholList();
		model.addAttribute("alcoholList", alcoholList);
		return "soju/sojuList";
		
	}
}
