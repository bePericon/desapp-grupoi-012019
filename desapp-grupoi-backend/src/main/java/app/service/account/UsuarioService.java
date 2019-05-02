package app.service.account;

import app.model.account.UsuarioPrueba;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import app.persistence.account.IUsuarioDao;

import javax.persistence.EntityManagerFactory;

@Service
@Transactional
public class UsuarioService implements IUsuarioService {

    @Autowired
    private IUsuarioDao usuarioDao;

    @Override
    public void create(UsuarioPrueba usuarioPrueba) {
//        usuarioDao.create(usuarioPrueba);
    }

    @Override
    public UsuarioPrueba getById(Long id) {
        return usuarioDao.getById(id);
    }
}