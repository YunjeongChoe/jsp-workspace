package com.di.step2;

public class FreeBoardServiceImpl3 implements IFreeBoardService{


	IFreeBoardDao freeBoardDao;
	
	public FreeBoardServiceImpl3(IFreeBoardDao freeBoardDao) {
		this.freeBoardDao=freeBoardDao;
	}
	
	@Override
	public void modifyBoard() {
		freeBoardDao.updateBoard();
		
	}

	
	
	
}
