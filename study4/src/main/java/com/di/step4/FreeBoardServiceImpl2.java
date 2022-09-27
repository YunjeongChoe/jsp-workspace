package com.di.step4;

public class FreeBoardServiceImpl2 implements IFreeBoardService{


	IFreeBoardDao freeBoardDao;
	
	public FreeBoardServiceImpl2(IFreeBoardDao freeBoardDao) {
		this.freeBoardDao=freeBoardDao;
	}
	
	@Override
	public void modifyBoard() {
		freeBoardDao.updateBoard();
		
	}

	
	
	
}
