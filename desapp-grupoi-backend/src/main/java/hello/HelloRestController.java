package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {

    @RequestMapping("/")
    public String hello() {
        return "HELLO!, It's running all right!";
    }
}