package com.aop.step2;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		//ExtimeAspect (공통기능)이 ForCalculator, RecCalculator의 factorial 메소드가 실행될 때 마다
		
		GenericXmlApplicationContext context = new GenericXmlApplicationContext("aop/step2.xml");
		ForCalculator forCal = context.getBean(ForCalculator.class);
		RecCalculator recCal = context.getBean(RecCalculator.class);
		
		System.out.println("forCal결과 : " + forCal.factorial(5));
		System.out.println("recCal결과 : " + recCal.factorial(5));
		
	}

}
