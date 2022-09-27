package com.aop.step1;

public class ExeTimeCalculator implements Calculator {

	
	private Calculator deligate;
	
	public ExeTimeCalculator(Calculator calculator) {

		deligate=calculator;
	}
	
	@Override
	public long factorial(long num) {
		long startTime=System.nanoTime();
		long result=deligate.factorial(num);
		long endTime=System.nanoTime();
		System.out.println("걸린 시간 : " + (endTime - startTime));
		return result;
	}

	
}
