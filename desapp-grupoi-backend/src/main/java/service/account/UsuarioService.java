package service.account;

import model.account.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import persistence.account.IUsuarioDAO;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class UsuarioService {

    @Autowired
    private IUsuarioDAO usuarioDAO;

    public List<Usuario> getAll(){
        return this.usuarioDAO.getAll();
    }

    public void create(Usuario usuario) {
        usuarioDAO.create(usuario);
    }
}
