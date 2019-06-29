package app.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class MailSenderAspect {


    @Before("execution(*app.service.account.*.*(..))")
    public void before(JoinPoint joinPoint) {


    	System.out.println("FUNCIONAAAAAAAAAAAAAAAAAAAAAAAAAAAAA1");
    	System.out.print("sarasa");
    }
    
    @After("execution(* app.service.account.*.*(..))")
    public void afterReturning(JoinPoint joinPoint) {

    	System.out.print("sarasa");
    	System.out.println("FUNCIONAAAAAAAAAAAAAAAAAAAAAAAAAAAAA2");
    }
	
	
    
    @Before("execution(*app.service.account.UsuarioService.*.*(..))")
    public void before2(JoinPoint joinPoint) {
        //Advice
//        logger.info(" Check for user access ");
//        logger.info(" Allowed execution for {}", joinPoint);
    	System.out.print("sarasa");
    	System.out.println("FUNCIONAAAAAAAAAAAAAAAAAAAAAAAAAAAAA3");
    }
    
    @Before("execution(*app.service.account.UsuarioService.*.*(..))")
    public void before3(JoinPoint joinPoint) {
        //Advice
//        logger.info(" Check for user access ");
//        logger.info(" Allowed execution for {}", joinPoint);
    	System.out.print("sarasa");
    	System.out.println("FUNCIONAAAAAAAAAAAAAAAAAAAAAAAAAAAAA4");
    }
}
