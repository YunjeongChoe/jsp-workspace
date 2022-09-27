package com.study.login.service;

import com.study.login.vo.UserVO;
import com.study.member.dao.IMemberDao;
import com.study.member.dao.MemberDaoOracle;
import com.study.member.vo.MemberVO;

public class LoginServiceImpl implements ILoginService{

	IMemberDao memberDao = new MemberDaoOracle();
	@Override
	public UserVO getUser(String userId) {
		MemberVO member=memberDao.getMember(userId);
		if(member==null) {
			return null;
		}
		UserVO user = new UserVO();
		user.setUserId(member.getMemId());
		user.setUserPass(member.getMemPass());
		user.setUserName(member.getMemName());
		user.setUserRole("MEMBER");  //임시 
			return user;
	}
	
	

}
