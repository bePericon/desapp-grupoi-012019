package webservice.account;

import model.account.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.account.IUsuarioService;
import java.util.List;

@RestController
@EnableAutoConfiguration
public class UsuarioRestController {

    @Autowired
    private IUsuarioService usuarioService;

    //private static final Logger logger = LoggerFactory.getLogger(ApiRequestController.class);

    @RequestMapping(value = "/usuario", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getHome() {
        List<Usuario> all = this.usuarioService.getAll();

        StringBuilder sb = new StringBuilder();

        all.forEach(p -> sb.append(p.getNombre() + "<br>"+ p.getApellido()));

        return sb.toString();
    }

}
