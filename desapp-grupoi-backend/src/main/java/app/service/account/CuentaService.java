package app.service.account;

import app.model.account.Cuenta;
import app.model.account.Usuario;
import app.persistence.GenericDao;
import app.persistence.IGenericDao;
import app.persistence.account.CuentaDao;
import app.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;

@Service
@Transactional
public class CuentaService extends GenericService<Cuenta> {

    @Autowired
    private CuentaDao dao;

    @Override
    protected CuentaDao getDao() {
        return dao;
    }

    public CuentaService() {
        super();
    }

    public Cuenta getDisponibleParaEliminar(Usuario usuario) {
        if(usuario.tieneInvitacionesPendientes()){
//            throw Exception;
        }
        return this.getByUsuarioEmail(usuario.getEmail());
    }

    private Cuenta getByUsuarioEmail(String email) {
        return this.getDao().getByUsuarioEmail(email);
    }
}