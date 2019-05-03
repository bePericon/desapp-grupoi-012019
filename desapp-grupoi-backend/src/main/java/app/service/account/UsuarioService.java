package app.service.account;

import app.persistence.GenericDao;
import app.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;

@Service
@Transactional
public class UsuarioService extends GenericService {

    @Autowired
    public UsuarioService(EntityManagerFactory factory, GenericDao usuarioDao) {
        super(factory, usuarioDao);
    }
}