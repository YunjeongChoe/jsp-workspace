package com.study.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.study.user.vo.UserVO;

@Mapper
public interface IUserDao {
	public List<UserVO> getUserList();
}
