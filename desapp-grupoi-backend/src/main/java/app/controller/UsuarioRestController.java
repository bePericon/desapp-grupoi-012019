package app.controller;

import app.model.account.Cuenta;
import app.model.account.Usuario;
import app.service.account.CuentaService;
import app.service.account.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = {"/usuario"})
@EnableAutoConfiguration
public class UsuarioRestController {

    private String nuevalinea = "</br>";

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CuentaService cuentaService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String get(@PathVariable String id) {
        try {
            Usuario usuario = (Usuario) usuarioService.getById(Long.parseLong(id));
            return "Usuario: " + usuario.getNombre() +" "+usuario.getApellido();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
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

    @RequestMapping(value = "/generarcuentas", method = RequestMethod.GET)
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