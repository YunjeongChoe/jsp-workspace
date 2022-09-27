package com.di.step5;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

@Component
public class FreeBoardServiceImpl2 implements IFreeBoardService{

	IFreeBoardDao freeBoardDao;
	
	@Inject
	public FreeBoardServiceImpl2(IFreeBoardDao freeBoardDao) {
		this.freeBoardDao=freeBoardDao;
	}
	
	@Override
	public void modifyBoard() {
		freeBoardDao.updateBoard();
		
	}

	
	
	
}
