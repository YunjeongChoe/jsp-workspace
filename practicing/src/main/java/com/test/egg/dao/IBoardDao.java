package com.test.egg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.test.egg.vo.BoardVO;

@Mapper
public interface IBoardDao {
	
	public List<BoardVO> getBoardList();
}
