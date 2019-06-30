package app.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;



@Aspect
@Component
@Order(0)
public class MailSenderAspect {

	private static final Logger logger = LogManager.getLogger(MailSenderAspect.class);
	
	
	@Pointcut("execution(* app.model.event.*.*(..))")
	public void methodsStarterServicePointcut() {
	}

	@Before("methodsStarterServicePointcut()")
	public void beforeMethods() throws Throwable {
		logger.info("/////// BEFORE /////");
	}

	@After("methodsStarterServicePointcut()")
	public void afterMethods() throws Throwable {
		logger.info("/////// AFTER  /////");
	}

}
