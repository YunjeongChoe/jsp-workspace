package com.five.jo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.five.jo.vo.AlcoholVO;

@Mapper

// mapper 까먹지마세요~~~~~~~~~~











public interface IAlcoholDao {
	 List<AlcoholVO> alcoholList(); 
}
