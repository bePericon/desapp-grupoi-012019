package app.service.account;

import app.model.account.UsuarioPrueba;
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
    public void create(UsuarioPrueba usuarioPrueba) {
        usuarioDao.create(usuarioPrueba);
    }

    @Override
    public UsuarioPrueba get(Long id) {
        return usuarioDao.getEmployeeById(id);
    }
}