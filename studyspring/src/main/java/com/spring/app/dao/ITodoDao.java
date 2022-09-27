package com.spring.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spring.app.vo.TodoVO;

@Mapper
public interface ITodoDao {

	public List<TodoVO> getList(TodoVO todo);
	public TodoVO getTdNo(int tdNo);
	public int insert(TodoVO todo);
	public void update(TodoVO todo);
	public void delete(TodoVO todo);
}
