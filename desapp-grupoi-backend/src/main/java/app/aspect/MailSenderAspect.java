package app.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;


@Aspect
@Configuration
public class MailSenderAspect {

	private static final Logger logger = LogManager.getLogger(MailSenderAspect.class);
	

	@Before("execution(* app.service.event.*.*(..))")
	public void beforee(JoinPoint joinPoint) {
		// Advice
		logger.info(" AAAAAAAAAAAAAAAAAAAAAAAAA ");
		logger.info(" AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA3", joinPoint);
	}
	

	@Before("execution(* app.service.event.*(..))")
	public void beforeee(JoinPoint joinPoint) {
		// Advice
		logger.info(" AAAAAAAAAAAAAAAAAAAAAAAAA 2");
		logger.info(" AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", joinPoint);
	}
	
	@Before("execution(* app.service.event.*.(..))")
	public void beforeee3(JoinPoint joinPoint) {
		// Advice
		logger.info(" AAAAAAAAAAAAAAAAAAAAAAAAA 1");
		logger.info(" AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", joinPoint);
	}

}
