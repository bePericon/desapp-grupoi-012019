package app.service.account;

import app.model.account.Usuario;
import app.persistence.account.UsuarioDao;
import app.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Service
@Transactional
public class UsuarioService extends GenericService {

    @Autowired
    public UsuarioService(EntityManagerFactory factory, UsuarioDao usuarioDao) {
        super(factory, usuarioDao);
    }

    public boolean yaExiste(Usuario nuevoUsuario) {
        String s = "from Usuario where email = :email ";
        List<Usuario> list = (List<Usuario>) this.executeQueryList(s, "email", nuevoUsuario.getEmail());
        return list.size() > 0;
    }

    public boolean esValido(Usuario usuario){
        return true;
    }
}