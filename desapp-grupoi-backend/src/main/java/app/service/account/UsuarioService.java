package app.service.account;

import app.persistence.account.UsuarioDao;
import app.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;

@Service
@Transactional
public class UsuarioService extends GenericService {

    @Autowired
    public UsuarioService(EntityManagerFactory factory, UsuarioDao usuarioDao) {
        super(factory, usuarioDao);
    }
}