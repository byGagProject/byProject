package com.grabandgo.server.advert.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Alexandr_I
 *
 */
@Aspect
@Component
public class ServiceMonitor {

	private static final Logger LOG = LoggerFactory.getLogger(ServiceMonitor.class);
	
	@Pointcut("execution(* com.grabandgo.server.advert.service.**.*.*(..))")
	public void serviceMethods() { }

	@Around("serviceMethods()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		LOG.debug("	>> Starting Service Method: {}, Args: {}", joinPoint.getSignature().toShortString(), joinPoint.getArgs());
		Object result = joinPoint.proceed();
		LOG.debug("	<< Ending Service Method: {}, Result: {}", joinPoint.getSignature().toShortString(), result);
		return result;
	}
}
