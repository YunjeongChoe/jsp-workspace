package com.spring.app.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TodoController {
	
	@RequestMapping(value = "/todolist/todolist.wow")
	public String todoList() {
		return "todolist/todolist";
	}
	
}
