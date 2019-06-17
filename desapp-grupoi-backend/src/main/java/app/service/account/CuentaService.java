package app.service.account;

import app.error.exception.ExceptionNotAcceptable;
import app.error.exception.ExceptionNotFound;
import app.model.account.*;
import app.model.web.NewMovimiento;
import app.persistence.account.CuentaDao;
import app.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Cuenta nuevoMovimiento(long idCuenta, EnumTipos.TipoMovimiento tipo, NewMovimiento newMovimiento) {
        Cuenta cuenta = this.getDao().getById(idCuenta);

        switch (tipo){
            case DEPOSITAR:
                this.nuevoMovimientoDepositar(newMovimiento, cuenta);
                break;
            case RETIRAR:
                this.nuevoMovimientoRetirar(newMovimiento, cuenta);
                break;
            case CREDITO:
                this.nuevoMovimientoCredito(cuenta);
                break;
            case PAGARCUOTA:
                cuenta.debitarCuotaCredito();
                break;
        }

        this.getDao().update(cuenta);
        return cuenta;
    }

    private void nuevoMovimientoCredito(Cuenta cuenta) {
        if(! cuenta.hayCreditoEnCurso() && cuenta.getSituacionDeuda().esCumplidor())
            cuenta.solicitarCredito();
        else if(cuenta.hayCreditoEnCurso())
            throw new ExceptionNotAcceptable("Ya existe un credito en curso.");
        else if(cuenta.getSituacionDeuda().esMoroso())
            throw new ExceptionNotAcceptable("La situacion del usuario es: MOROSO.");
        else if(cuenta.getSituacionDeuda().esNormal())
            throw new ExceptionNotAcceptable("El usuario no puede solicitar credito en situacion: NORMAL.");
    }

    private void nuevoMovimientoRetirar(NewMovimiento movimiento, Cuenta cuenta) {
        if(! cuenta.matchCodigoSeguridad(movimiento.getCodigoSeguridad()))
            throw  new ExceptionNotAcceptable("Código de seguridad incorrecto.");
        else if(! cuenta.haySaldoSuficiente(movimiento.getMonto()))
            throw new ExceptionNotAcceptable("No hay saldo suficiente.");
        else
            cuenta.retirarDinero(movimiento.getMonto());
    }

    private void nuevoMovimientoDepositar(NewMovimiento movimiento, Cuenta cuenta) {
        if(! cuenta.matchCodigoSeguridad(movimiento.getCodigoSeguridad()))
            throw  new ExceptionNotAcceptable("Código de seguridad incorrecto.");
        else
            cuenta.depositarDinero(movimiento.getMonto());
    }
}