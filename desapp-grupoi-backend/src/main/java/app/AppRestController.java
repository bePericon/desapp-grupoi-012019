package app;

import app.model.account.Cuenta;
import app.model.account.Usuario;
import app.service.account.CuentaService;
import app.service.account.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@EnableAutoConfiguration
public class AppRestController {

    private String nuevalinea = "</br>";

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CuentaService cuentaService;

    @RequestMapping("/")
    public String hello() {
        return "Hola! todo esta corriendo perfectamente!";
    }

    @RequestMapping("/app")
    public String app() {
        return "ESTA ES LA APP!" + this.nuevalinea +
                "Para consultar un usuario: http://localhost:8080/usuario/id" + this. nuevalinea +
                "Para todos los usuarios: http://localhost:8080/usuario/all";
    }

    @RequestMapping(value = "/usuario/{id}", method = RequestMethod.GET)
    public String get(@PathVariable String id) {
        try {
            Usuario usuario = (Usuario) usuarioService.getById(Long.parseLong(id));
            return "Usuario: " + usuario.getNombre() +" "+usuario.getApellido();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/usuario/all", method = RequestMethod.GET)
    public String getAllUsuarios() {
        try {
            List<Usuario> usuarios = (List<Usuario>) usuarioService.getAll();
            String str = "";
            for (Usuario u: usuarios) {
                str = str.concat(u.getNombre()+" "+u.getApellido()+ this.nuevalinea);
            }
            return str;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/cuenta/generarcuentas", method = RequestMethod.GET)
    public String setCuentas() {
        try {
            List<Usuario> usuarios = (List<Usuario>) usuarioService.getAll();
            for (Usuario u: usuarios) {
                Cuenta cuenta = new Cuenta(u);
                this.cuentaService.save(cuenta);
            }
            return "Las cuentas se generaron bien!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}