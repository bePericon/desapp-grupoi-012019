package app.controller;

import app.model.account.Usuario;
import app.service.account.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RequestMapping(value = {"app"})
@EnableAutoConfiguration
public class UsuarioRestController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuario/{id}")
    public ResponseEntity<?> getUsuario(@PathVariable String id) {
        Usuario usuario = this.usuarioService.getByIdUsuario(Long.parseLong(id));
        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }

    @GetMapping("/usuario/all")
    public  ResponseEntity<List<Usuario>>  getAllUsuarios() {
        List<Usuario> usuarios = this.usuarioService.getAllUsuarios();
        return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    }

    @PostMapping("/usuario")
    public ResponseEntity nuevoUsuario(@RequestBody Usuario nuevoUsuario) {
        Usuario usuario = this.usuarioService.createNuevoUsuario(nuevoUsuario);
        return new ResponseEntity<Usuario>(usuario,HttpStatus.CREATED);
    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable String id, @RequestBody Usuario usuario) {
        Usuario usuarioActual = this.usuarioService.updateUsuario(Long.parseLong(id), usuario);
        return new ResponseEntity<Usuario>(usuarioActual, HttpStatus.OK);
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable String id) {
        Usuario usuario = this.usuarioService.deleteByIdUsuario(Long.parseLong(id));
        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }
}