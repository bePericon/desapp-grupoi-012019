package app;

import app.model.account.UsuarioPrueba;
import app.service.account.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@EnableAutoConfiguration
public class AppRestController {

    @Autowired
    private IUsuarioService usuarioService;

    @RequestMapping("/hello")
    public String hello() {
        return "HELLO!, It's running all right!";
    }

    @RequestMapping("/app")
    public String app() {
        return "ESTA ES LA APP!";
    }

    @RequestMapping(value = "/usuario/{id}", method = RequestMethod.GET)
    public String get(@PathVariable String id) {
        try {
            UsuarioPrueba usuarioPrueba = usuarioService.get(Long.parseLong(id));
            return "Usuario: " + usuarioPrueba.getNombre() +" "+usuarioPrueba.getApellido();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}