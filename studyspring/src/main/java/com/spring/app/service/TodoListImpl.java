package com.spring.app.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.app.dao.ITodoDao;
import com.spring.app.vo.TodoVO;

@Service
public class TodoListImpl implements ITodoService{
	
	@Inject
	ITodoDao todoDao;

	@Override
	public List<TodoVO> getList(TodoVO todo) {
		List<TodoVO> todoList = todoDao.getList(todo);
		return todoList;
	}

	
	@Override
	public TodoVO getTdNo(int tdNo) {
		TodoVO view = todoDao.getTdNo(tdNo);
		return view;
	}
	
	@Override
	public int insert(TodoVO todo) {
		
		return todoDao.insert(todo);
		
		
	}

	@Override
	public void update(TodoVO todo) {
		todoDao.update(todo);
	}

	@Override
	public void delete(TodoVO todo) {
		// TODO Auto-generated method stub
		
	}


}
