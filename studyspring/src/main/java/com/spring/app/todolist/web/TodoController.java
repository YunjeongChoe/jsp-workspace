package com.spring.app.todolist.web;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.app.service.ITodoService;
import com.spring.app.vo.TodoVO;

@Controller
public class TodoController {

	@Inject
	ITodoService todoService;

	@RequestMapping(value = "/todoList.wow")
	public String gettodolist(TodoVO todo, Model model) {
		List<TodoVO> todoList = todoService.getList(todo);
		model.addAttribute("todoList", todoList);
		return "todoList";
	}
	
	
	@RequestMapping(value = "/todoWrite.wow")
	public String writeTodo(@ModelAttribute("todo") TodoVO todo, Model model) {
		return "todoWrite";
	}
	
	@RequestMapping(value = "/todoRegist.wow")
	public String registTodo(@ModelAttribute("todo") TodoVO todo, Model model) {
		int todoWrite = todoService.insert(todo);
		model.addAttribute("todoWrite", todoWrite);
		return "backTo";
	}
	
	
	@RequestMapping(value = "/todoView.wow")
	public String viewTodo(int tdNo, Model model) {
		TodoVO view = todoService.getTdNo(tdNo);
		model.addAttribute("view", view);
		return "todoView";
	}
	
	
	@RequestMapping(value = "/todoModify.wow")
	public String modifyTodo(int tdNo, Model model) {
		TodoVO view = todoService.getTdNo(tdNo);
		model.addAttribute("view", view);
		return "todoModify";
	}
	
	@RequestMapping(value = "/todoUpdate.wow")
	public String updateTodo(TodoVO todo, HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		todoService.update(todo);
		return "backTo";
	}
	
	
	
	
	

}
