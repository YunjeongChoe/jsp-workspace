package com.di.step5;

import org.springframework.stereotype.Component;

@Component
public class FreeBoardDaoOracle implements IFreeBoardDao{

	

	@Override
	public void updateBoard() {
		System.out.println("재정의 완료");
		
	}
	
}
