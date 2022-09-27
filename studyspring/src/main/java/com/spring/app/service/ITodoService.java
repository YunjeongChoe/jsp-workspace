package com.spring.app.service;

import java.util.List;

import com.spring.app.vo.TodoVO;

public interface ITodoService {
	
	public List<TodoVO> getList(TodoVO todo);
	public TodoVO getTdNo(int tdNo);
	public int insert(TodoVO todo);
	public void update(TodoVO todo);
	public void delete(TodoVO todo);
	
}
