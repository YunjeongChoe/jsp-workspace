package com.study.common.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


@Aspect
public class CommonParameter {


	//study4-3에 AOP적용하기, web에 있는 모든 클래스의 모든 메소드 중에서 파라미터가 1개 이상인거 
	@Pointcut("execution(public * com.study.*.web..*(*,..))")
	private void controllerTarget() {
	}

	@Around("controllerTarget()")
	public Object showParameter(ProceedingJoinPoint joinPoint) throws Throwable {
		Object[] args = joinPoint.getArgs(); //파라미터가 0일 때도 null을 return안함, 길이가 0인 배열을 return
		System.out.println("클래스 이름 : " + joinPoint.getTarget().getClass().getSimpleName());
		System.out.println("메소드 이름 : " + joinPoint.getSignature().getName());
		System.out.println("파라미터 이름들");
		for (Object obj : args) {
			//model은 데이터가 너무 많아서 내가 원하는 파라미터(searchVO, boNo)등을 보기가 힘들다. model을 제외하면?
			if(obj==null) {
				System.out.println(obj.getClass().getSimpleName()+ " : " + obj.toString());
			}else if(!obj.getClass().getSimpleName().equals("BindingAwareModelMap")){
				System.out.println(obj.getClass().getSimpleName()+ " : " + obj.toString());
			}
		}
		System.out.println("파라미터 끝~~~ ");
		try {
			Object result = joinPoint.proceed();
			return result;
		} finally {
			
		}
		
	}
	
	
	
	
	
}
