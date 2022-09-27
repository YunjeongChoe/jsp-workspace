package com.aop.step1;

public class Main {
	public static void main(String[] args) {
		
		RecCalculator recCal = new RecCalculator();
		ForCalculator forCal = new ForCalculator();
		
//		long startRec=System.nanoTime();
		System.out.println(recCal.factorial(5));
//		long endRec = System.nanoTime();
		
//		long startFor = System.nanoTime();
		System.out.println(forCal.factorial(5));
//		long endFor = System.nanoTime();
//		//메소드별로 호출하는 시간 
//		
//		System.out.println("rec 걸린 시간 : " + (endRec - startRec));
//		System.out.println("rec 걸린 시간 : " + (endFor - startFor));
//		deligate를 만들어서 ForCal, RecCal을 대신 실행
		
		ExeTimeCalculator exFor = new ExeTimeCalculator(forCal);
		ExeTimeCalculator exRec = new ExeTimeCalculator(recCal);
		System.out.println("for factorial(5)결과 : " + exFor.factorial(5));
		System.out.println("rec factorial(5)결과 : " + exRec.factorial(5));
		
		//ExTimeCalculator를 프록시 객체라고 하고, ForCal, RecCal 객체를 대상객체라고 합니다.
		
		
		
	}
}
