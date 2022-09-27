package com.study.common.util;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MapController {
	
	@RequestMapping("/more/map.wow")
	public String showMap() {
		return "more/map";
	}
}
