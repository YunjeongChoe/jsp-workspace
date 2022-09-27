package com.aop.step2;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ExtimeAspect {  //ExtimeCalculator역할인데, AOP 공통기능을 Aspect라고 해서 Aspect라고 만들었다
	
	@Pointcut("execution(public * com.aop.step2..*(..))")   //어떤 메소드를 대상으로 할지를 쓰는건데 For, Rec의 factorial
	private void publicTarget(){}
	
	
	//공통기능으로 시간측정
	@Around("publicTarget()")
	public Object measure(ProceedingJoinPoint jointPoint) throws Throwable {
		long startTime=System.nanoTime();
		try {
			Object result = jointPoint.proceed();  //jointPoint가 대상객체(핵심기능)
			return result;
		}finally {
			long endTime = System.nanoTime();
			System.out.println("걸린 시간 : " + (endTime-startTime));
			Signature signature = jointPoint.getSignature();
			
			System.out.println("메소드 이름 : " + signature.getName());
			System.out.println("클래스 이름 : " + jointPoint.getTarget().getClass().getName());
			System.out.println("파라미터 목록 : " + jointPoint.getArgs()[0]);
		}
	}
	
	

}
