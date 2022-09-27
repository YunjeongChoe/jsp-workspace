package com.di.step5;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Test {

	public static void main(String[] args) {

		GenericXmlApplicationContext context = new GenericXmlApplicationContext("di/step5.xml");
		//context = assembler, context
		//context "di/step4.xml"을 읽어서 거기에 적혀있는 객체들을 만들어서 가지고 있음
		
		//에러 발생 상황
		//1. FreeBoardServiceImpl1에는 @Component랑 @inject가 있음
		//		+FreeBoardDaoOracle이는 @Component없음 
		
		//2. FreeBoardServiceImpl1에는 @Componemt랑 @inject가 있음
		//		+FreeBoardDaoOracle에는 @Component 있음
		//		+FreeBoardDaoMysqle에도 @Component 있음
		//(원칙적으로 Dao는 둘 중 1개만 @Component있는게 맞는데 2개 있어도 해결 가능.
		
		//3. FreeBoardServiceImpl1에 @Inject가 없음
//		FreeBoardDaoOracle freeBoardDaoOracle = new FreeBoardDaoOracle();
//		IFreeBoardDao iFreeBoardDao = new FreeBoardDaoMysql();
//		FreeBoardServiceImpl1 freeBoardServiceImpl1 = new FreeBoardServiceImpl1(iFreeBoardDao);
		
		
		IFreeBoardService freeBoardService1 = context.getBean(FreeBoardServiceImpl1.class);
		freeBoardService1.modifyBoard();

	
	
	
	}

}
