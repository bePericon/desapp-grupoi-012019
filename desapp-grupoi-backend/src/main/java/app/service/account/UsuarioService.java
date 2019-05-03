package app.service.account;

import app.model.account.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import app.persistence.account.IUsuarioDao;

@Service
@Transactional
public class UsuarioService implements IUsuarioService {

    @Autowired
    private IUsuarioDao usuarioDao;

    @Override
    public void create(Usuario usuarioPrueba) {
//        usuarioDao.create(usuarioPrueba);
    }

    @Override
    public Usuario getById(Long id) {
        return usuarioDao.getById(id);
    }
}