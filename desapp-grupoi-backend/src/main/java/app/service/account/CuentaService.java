package app.service.account;

import app.error.exception.ExceptionNotAcceptable;
import app.error.exception.ExceptionNotFound;
import app.model.account.Cuenta;
import app.model.account.TarjetaCredito;
import app.model.account.Usuario;
import app.persistence.GenericDao;
import app.persistence.IGenericDao;
import app.persistence.account.CuentaDao;
import app.service.GenericService;
import app.util.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        if(usuario.tieneInvitacionesPendientes())
            throw new ExceptionNotAcceptable("Hay invitaciones pendientes.");

        return this.getByUsuarioEmail(usuario.getEmail());
    }

    private Cuenta getByUsuarioEmail(String email) {
        Cuenta cuenta = this.getDao().getByUsuarioEmail(email);
        if(cuenta == null)
            throw new ExceptionNotFound("No se encontro ninguna cuenta con ese email.");
        return cuenta;
    }

    public Cuenta getCuentaByIdUsuario(long idUsuario) {
        Cuenta cuenta = this.getDao().getCuentaByIdUsuario(idUsuario);
        if (cuenta == null)
            throw new ExceptionNotFound("No se encontro ninguna cuenta para el usuario con id: " + idUsuario);

        return cuenta;
    }

    public Cuenta setTarjetaCredito(long idCuenta, TarjetaCredito tarjeta) {
        Cuenta cuenta = this.getDao().getById(idCuenta);
        cuenta.setTarjetaCredito(tarjeta);
        this.getDao().update(cuenta);
        return cuenta;
    }
}