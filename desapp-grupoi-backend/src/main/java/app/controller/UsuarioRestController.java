package app.controller;

import app.model.account.Usuario;
import app.model.web.ApiResponse;
import app.service.account.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
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
    public ApiResponse<Usuario> getUsuario(@PathVariable String id) {
        Usuario usuario = this.usuarioService.getByIdUsuario(Long.parseLong(id));
        return new ApiResponse<Usuario>(HttpStatus.OK.value(), "Usuario encontrado.", usuario);
    }

    @GetMapping("/usuario/all")
    public  ApiResponse<List<Usuario>>  getAllUsuarios() {
        List<Usuario> usuarios = this.usuarioService.getAllUsuarios();
        return new ApiResponse<List<Usuario>>(HttpStatus.OK.value(), "Todos los usuarios.", usuarios);
    }

    @PostMapping("/usuario")
    public ApiResponse nuevoUsuario(@RequestBody Usuario nuevoUsuario) {
        Usuario usuario = this.usuarioService.createNuevoUsuario(nuevoUsuario);
        return new ApiResponse<Usuario>(HttpStatus.CREATED.value(), "Usuario creado exitosamente.", usuario);
    }

    @PutMapping("/usuario/{id}")
    public ApiResponse<?> actualizarUsuario(@PathVariable String id, @RequestBody Usuario usuario) {
        Usuario usuarioActual = this.usuarioService.updateUsuario(Long.parseLong(id), usuario);
        return new ApiResponse<Usuario>(HttpStatus.OK.value(), "Usuario actualizado exitosamente.", usuarioActual);
    }

    @DeleteMapping("/usuario/{id}")
    public ApiResponse<?> eliminarUsuario(@PathVariable String id) {
        Usuario usuario = this.usuarioService.deleteByIdUsuario(Long.parseLong(id));
        return new ApiResponse<Usuario>(HttpStatus.OK.value(), "Usuario eliminado.", usuario);
    }
}