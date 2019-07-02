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

import java.util.List;
import java.util.stream.Collectors;

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
        Cuenta cuenta = this.getDao().getByUsuarioEmail(usuario.getEmail());
        if(cuenta.tieneInvitacionesPendientes())
            throw new ExceptionNotAcceptable("Hay invitaciones pendientes.");

        return cuenta;
    }

    private Cuenta getByUsuarioEmail(String email) {
        Cuenta cuenta = this.getDao().getByUsuarioEmail(email);
        if(cuenta == null)
            throw new ExceptionNotFound("No se encontro ninguna cuenta con ese email.");
        return cuenta;
    }

    public Cuenta getByUsuarioEmailWithException(String email) {
        Cuenta cuenta = this.getDao().getByUsuarioEmail(email);
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
            throw new ExceptionNotAcceptable("El usuario no puede solicitar credito en situacion: MOROSO.");
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

    public List<Credito> getAllCreditosByIdCuenta(long idCuenta) {
        Cuenta cuenta = this.getDao().getById(idCuenta);
        return cuenta.getCreditos();
    }

    public Cuenta createNuevoCredito(long idCuenta) {
        Cuenta cuenta = this.getDao().getById(idCuenta);
        cuenta.solicitarCredito();

        this.getDao().update(cuenta);

        return this.getDao().getCuentaByIdUsuario(idCuenta);
    }

    public Credito getUltimoCredito(long idCuenta) {
        Cuenta cuenta = this.getDao().getById(idCuenta);

        if(! cuenta.hayCreditoEnCurso())
            return null;

        return cuenta.obtenerUltimoCredito();
    }

    public List<Credito> getAllCreditosFinalizados(long idCuenta) {
        return this.getAllCreditosByIdCuenta(idCuenta).stream()
            .filter(c -> c.getEstado().equals(EnumEstados.EstadoCredito.FINALIZADO)).collect(Collectors.toList());
    }

	public List<Cuenta> getAllCuentas() {
		List<Cuenta> cuenta = this.getDao().getAllCuentas();
        if (cuenta == null)
            throw new ExceptionNotFound("No se encontraron cuentas");

        return cuenta;
	}

	public List<Cuenta> getAllCuentasConCreditosEnCurso(){
        return this.getDao().getAllCuentasConCreditosEnCurso();
    }
}