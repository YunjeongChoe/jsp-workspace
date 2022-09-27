package com.di.step3;

public class Test2 {

	public static void main(String[] args) {
		Assembler assembler = new Assembler();
		//assembler는 freeBoardDao, service1~3의 객체를 가지고 있다.
		IFreeBoardService freeBoardService1=assembler.getFreeBoardService1();
		freeBoardService1.modifyBoard();
		
		IFreeBoardService freeBoardService2=assembler.getFreeBoardService2();
		freeBoardService2.modifyBoard();
		
		IFreeBoardService freeBoardService3=assembler.getFreeBoardService3();
		freeBoardService3.modifyBoard();
		
	}

}
