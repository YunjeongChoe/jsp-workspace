package com.aop.step2;

public class RecCalculator implements Calculator{
	@Override
	public long factorial(long num) {
		return num==0 ? 1 : num*factorial(num-1);  
	}

}