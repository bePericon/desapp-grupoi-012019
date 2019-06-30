package app.aspect;

import java.security.Timestamp;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;



@Aspect
@Component
@Order(0)
public class LogServiceAspect {

	private static final Logger logger = LogManager.getLogger(LogServiceAspect.class);
	
	@Pointcut("execution(* app.service.account.*.*(..))") 
	public void methodsStarterServicePointcut() {
	}

	@Before("methodsStarterServicePointcut()")
	public void beforeMethods() throws Throwable {

		Date date = new Date();
		Timestamp timestamp =  Timestamp(date.getTime());
		
		logger.info(timestamp+" "+"GET USUARIO QUE ESTá LOGUEADO "+" "+"acceso a servicio"+" "+"GET PARAMETROS ??");

	}

	private Timestamp Timestamp(long time) {
		return null;
	}

//	@After("methodsStarterServicePointcut()")
//	public void afterMethods() throws Throwable {
//		logger.info("/////// AFTER  /////");
//	}

}
