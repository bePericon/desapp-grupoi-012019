package app.controller;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@EnableAutoConfiguration
public class AppRestController {

    private String nuevalinea = "</br>";

    @RequestMapping("/")
    public String hello() {
        return "<h1>Hola! todo esta corriendo perfectamente!</h1>";
    }

    @RequestMapping("/app")
    public String app() {
        return "ESTA ES LA APP!" + this.nuevalinea +
                "Para consultar un usuario: http://localhost:8080/usuario/id" + this. nuevalinea +
                "Para todos los usuarios: http://localhost:8080/usuario/all";
    }
}