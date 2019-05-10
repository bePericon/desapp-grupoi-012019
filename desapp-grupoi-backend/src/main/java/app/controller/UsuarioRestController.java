package app.controller;

import app.model.account.Usuario;
import app.service.account.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import util.CustomErrorType;

import java.util.List;


@RestController
@RequestMapping(value = {"app"})
@EnableAutoConfiguration
public class UsuarioRestController {

    private String nuevalinea = "</br>";

    @Autowired
    private UsuarioService usuarioService;

//    @RequestMapping(value = "/usuarios/{id}", method = RequestMethod.GET)
    @GetMapping("/usuarios/{id}")
    public String get(@PathVariable String id) {
        try {
            Usuario usuario = (Usuario) usuarioService.getById(Long.parseLong(id));
            return "Usuario: " + usuario.getNombre() +" "+usuario.getApellido();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

//    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @GetMapping("/usuarios")
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

    @PostMapping("/usuarios")
    public ResponseEntity<String> nuevoUsuario(@RequestBody Usuario nuevoUsuario) {
        if(this.usuarioService.yaExiste(nuevoUsuario)){
            CustomErrorType error = new CustomErrorType("Ya existe un usuario con email: " +nuevoUsuario.getEmail());
            return new ResponseEntity(error ,HttpStatus.CONFLICT);
        }

        if(this.usuarioService.esValido(nuevoUsuario)){
            this.usuarioService.save(Usuario.build(nuevoUsuario));
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }
}