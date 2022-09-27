package com.study.user.service;

import java.util.List;

import javax.inject.Inject;

import com.study.user.dao.IUserDao;
import com.study.user.vo.UserVO;

public class UserServiceImpl implements IUserService{

	@Inject
	IUserDao userDao;

	@Override
	public List<UserVO> getUserList() {
		return userDao.getUserList();
	}
	
	
}
