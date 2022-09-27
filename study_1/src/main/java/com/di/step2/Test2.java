package com.di.step2;

public class Test2 {

	public static void main(String[] args) {
		//DI : Dependency Inject 의존성 주입
		//의존하는 객체를 외부에서 주입함으로써 코드의 재사용성을 높이는 기술 

		//step2에서는 의존객체를 외부에서 주입 -> 의존 주입
		//생성자방식, setter방식, 필드방식(only spring)
		
		IFreeBoardDao freeBoardDao = new FreeBoardDaoOracle();//daoOracle대신 mysql로 변경할 때 여기만 바꾸면 됨 
		IFreeBoardService freeBoardService = new FreeBoardServiceImpl1(freeBoardDao);
		freeBoardService.modifyBoard();
		IFreeBoardService freeBoardService2 = new FreeBoardServiceImpl1(freeBoardDao);
		freeBoardService2.modifyBoard();
		IFreeBoardService freeBoardService3 = new FreeBoardServiceImpl1(freeBoardDao);
		freeBoardService3.modifyBoard();
		
		
	}

}
