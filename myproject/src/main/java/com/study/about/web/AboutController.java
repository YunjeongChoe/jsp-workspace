package com.study.about.web;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.about.vo.AlcoholVO;
import com.study.about.vo.PianoVO;


@Controller
public class AboutController {
	
	@Inject
	IAboutDao aboutDao;
	
	
	@RequestMapping("/about/test.wow")
	public String aboutTest(Model model) {
		return "about/test";
	}

	@RequestMapping("/about/chProfile.wow")
	public String chProfile() {
		return "about/chProfile";
	}
	
	@RequestMapping("/about/chAlcohol.wow")
	public String chAlcohol(Model model) {
		List<AlcoholVO> alcoholList = aboutDao.getAlcoholList();
		model.addAttribute("alcoholList", alcoholList);
		return "about/chAlcohol";
	}
	
	@RequestMapping("/about/chPiano.wow")
	public String chPiano(Model model) {
		List<PianoVO> pianoList = aboutDao.getPianoList();
		model.addAttribute("pianoList", pianoList);
		return "about/chPiano";
	}
	
	
}
