package app;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"/app/controller","/app/service", "/app/persistence", "/app/model"})
public class App {

    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {

        SpringApplication.run(App.class, args);

        logger.info("RUN RUN RUN... EVENTEANDO SONARA MIENTRAS TODO ESTE BIEN!");
    }

}