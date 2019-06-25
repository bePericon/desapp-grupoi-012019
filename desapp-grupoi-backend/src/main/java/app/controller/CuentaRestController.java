package app.controller;

import app.model.account.*;
import app.model.web.ApiResponse;
import app.model.web.NewCredito;
import app.model.web.NewMovimiento;
import app.service.account.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.PUT})
@RequestMapping(value = {"app"})
@EnableAutoConfiguration
public class CuentaRestController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping("/cuenta/usuario/{id}")
    public ApiResponse<?> getCuentaByIdUsuario(@PathVariable String id) {
        Cuenta cuenta = (Cuenta) this.cuentaService.getCuentaByIdUsuario(Long.parseLong(id));
        return new ApiResponse<Cuenta>(HttpStatus.OK.value(),"", cuenta);
    }

    /*
    * Edicion de Tarjeta en Cuenta.
    */
    @PutMapping("/cuenta/tarjeta/{idCuenta}")
    public ApiResponse<?> setTarjetaCreditoCuenta(@PathVariable String idCuenta, @RequestBody TarjetaCredito tarjeta) {
        Cuenta cuenta = this.cuentaService.setTarjetaCredito(Long.parseLong(idCuenta), tarjeta);
        return new ApiResponse<Cuenta>(HttpStatus.OK.value(),"",cuenta);
    }

    @PutMapping("/cuenta/movimiento/deposito/{idCuenta}")
    public ApiResponse<?> nuevoMovimientoDeposito(@PathVariable String idCuenta, @RequestBody NewMovimiento newMovimiento) {
        Cuenta cuenta = this.cuentaService.nuevoMovimiento(Long.parseLong(idCuenta), EnumTipos.TipoMovimiento.DEPOSITAR, newMovimiento);
        return new ApiResponse<Cuenta>(HttpStatus.OK.value(),"",cuenta);
    }

    @PutMapping("/cuenta/movimiento/retiro/{idCuenta}")
    public ApiResponse<?> nuevoMovimientoRetiro(@PathVariable String idCuenta, @RequestBody NewMovimiento newMovimiento) {
        Cuenta cuenta = this.cuentaService.nuevoMovimiento(Long.parseLong(idCuenta), EnumTipos.TipoMovimiento.RETIRAR, newMovimiento);
        return new ApiResponse<Cuenta>(HttpStatus.OK.value(),"",cuenta);
    }

    /*
    * Api para creditos
    */
    @PutMapping("/cuenta/movimiento/credito/{idCuenta}")
    public ApiResponse<?> nuevoMovimientoCredito(@PathVariable String idCuenta) {
        Cuenta cuenta = this.cuentaService.nuevoMovimiento(Long.parseLong(idCuenta), EnumTipos.TipoMovimiento.CREDITO, new NewMovimiento());
        return new ApiResponse<Cuenta>(HttpStatus.OK.value(),"",cuenta);
    }

    @GetMapping("/cuenta/credito/{idCuenta}")
    public ApiResponse<?> getAllCreditos(@PathVariable String idCuenta) {
        List<Credito> creditos = this.cuentaService.getAllCreditosByIdCuenta(Long.parseLong(idCuenta));
        return new ApiResponse<List<Credito>>(HttpStatus.OK.value(),"", creditos);
    }

    @GetMapping("/cuenta/credito/finalizado/{idCuenta}")
    public ApiResponse<?> getAllCreditosFinalizados(@PathVariable String idCuenta) {
        List<Credito> creditos = this.cuentaService.getAllCreditosFinalizados(Long.parseLong(idCuenta));
        return new ApiResponse<List<Credito>>(HttpStatus.OK.value(),"", creditos);
    }

    @GetMapping("/cuenta/credito/ultimo/{idCuenta}")
    public ApiResponse<?> getUltimoCredito(@PathVariable String idCuenta) {
        Credito credito = this.cuentaService.getUltimoCredito(Long.parseLong(idCuenta));
        List<Credito> creditos = new ArrayList<>();
        if(credito != null)
            creditos.add(credito);
        return new ApiResponse<List<Credito>>(HttpStatus.OK.value(),"", creditos);
    }

    @PutMapping("/cuenta/movimiento/pagarcuota/{idCuenta}")
    public ApiResponse<?> nuevoMovimientoPagarCuota(@PathVariable String idCuenta) {
        Cuenta cuenta = this.cuentaService.nuevoMovimiento(Long.parseLong(idCuenta), EnumTipos.TipoMovimiento.PAGARCUOTA, new NewMovimiento());
        return new ApiResponse<Cuenta>(HttpStatus.OK.value(),"",cuenta);
    }
}
