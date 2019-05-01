package service.account;

import model.account.Usuario;
import model.account.UsuarioPrueba;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.account.IUsuarioDAO;
import persistence.account.UsuarioRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public List<UsuarioPrueba> getAll(){
        return (List<UsuarioPrueba>) this.usuarioRepository.findAll();
    }
}
