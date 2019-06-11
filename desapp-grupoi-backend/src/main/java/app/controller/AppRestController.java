package app.controller;
import app.model.web.ApiResponse;
import app.model.web.Login;
import app.model.account.Usuario;
import app.service.account.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.POST})
@EnableAutoConfiguration
public class AppRestController {

    private String nuevalinea = "</br>";

    @Autowired
    private UsuarioService usuarioService;

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

    // ------------------------------------- //

    @PostMapping("/app/login")
    public ApiResponse<Usuario> login(@RequestBody Login us) {
        Usuario usuario = this.usuarioService.getByEmailAndContrasenia(us.getEmail(),us.getContrasenia());
        return new ApiResponse<Usuario>(HttpStatus.OK.value(), "Login exitoso.", usuario);
    }
}