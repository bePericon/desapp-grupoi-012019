package app.controller;

import app.model.account.Cuenta;
import app.model.account.Usuario;
import app.service.account.CuentaService;
import app.service.account.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.util.CustomErrorType;

import java.util.List;


@RestController
@RequestMapping(value = {"app"})
@EnableAutoConfiguration
public class UsuarioRestController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CuentaService cuentaService;

    @GetMapping("/usuario/{id}")
    public ResponseEntity<?> getUsuario(@PathVariable String id) {
        Usuario usuario = this.usuarioService.getByIdUsuario(Long.parseLong(id));
        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }

    @GetMapping("/usuario/all")
    public  ResponseEntity<List<Usuario>>  getAllUsuarios() {
        List<Usuario> usuarios = this.usuarioService.getAll();
        if (usuarios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    }

    @PostMapping("/usuario")
    public ResponseEntity nuevoUsuario(@RequestBody Usuario nuevoUsuario) {
        if(this.usuarioService.yaExiste(nuevoUsuario)){
            CustomErrorType error = new CustomErrorType("Ya existe un usuario con email: " +nuevoUsuario.getEmail());
            return new ResponseEntity<>(error ,HttpStatus.CONFLICT);
        }

        if(this.usuarioService.esValido(nuevoUsuario)){
            this.usuarioService.save(Usuario.build(nuevoUsuario));
            Usuario usuario = this.usuarioService.getByEmailAndContrasenia(nuevoUsuario.getEmail(),nuevoUsuario.getContrasenia());
            Cuenta cuenta = new Cuenta(usuario);
            this.cuentaService.save(cuenta);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable String id, @RequestBody Usuario usuario) {
        Usuario usuarioActual = (Usuario) this.usuarioService.getById(Long.parseLong(id));

        if (usuarioActual == null) {
            CustomErrorType error = new CustomErrorType("No se encontro ningun usuario con id: " + id);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        usuarioActual.actualizar(usuario);
        this.usuarioService.save(usuarioActual);
        return new ResponseEntity<Usuario>(usuarioActual, HttpStatus.OK);
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable String id) {
        Usuario usuario = (Usuario) this.usuarioService.getById(Long.parseLong(id));
        if (usuario == null) {
            CustomErrorType error = new CustomErrorType("No se encontro ningun usuario con id: " + id);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        Cuenta cuenta = this.cuentaService.getDisponibleParaEliminar(usuario);
        this.cuentaService.deleteById(cuenta.getId());
        this.usuarioService.deleteById(Long.parseLong(id));
        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }
}