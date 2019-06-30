package app.aspect;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;

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
	private String usuario;
	@Pointcut("execution(* app.service.account.*.*(..))") 
	public void methodsStarterServicePointcut() {
	}
	
	@Pointcut("execution(* app.controller.AppRestController.login(..))") 
	public void cuandoLogueaPointcut() {
	}

	@Before("methodsStarterServicePointcut()")
	public void beforeMethods(JoinPoint jp) throws Throwable {

		Date date = new Date();
		
		logger.info(date+" - "+this.usuario+" - "+jp +" - "+"Params: "+ jp.getArgs());

	}
	

	@Before("cuandoLogueaPointcut()")
	public void siSeLogueaa(JoinPoint jp) {
		this.usuario =  jp.getArgs()[0].toString();

	}

}
