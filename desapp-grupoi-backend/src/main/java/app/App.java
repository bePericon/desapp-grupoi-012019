package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"/app/controller","/app/service", "/app/persistence", "/app/model"})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}