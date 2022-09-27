package com.di.step5;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

@Component
public class FreeBoardServiceImpl3 implements IFreeBoardService{

	IFreeBoardDao freeBoardDao;
	
	@Inject
	public FreeBoardServiceImpl3(IFreeBoardDao freeBoardDao) {
		this.freeBoardDao=freeBoardDao;
	}
	
	@Override
	public void modifyBoard() {
		freeBoardDao.updateBoard();
		
	}

	
	
	
}
