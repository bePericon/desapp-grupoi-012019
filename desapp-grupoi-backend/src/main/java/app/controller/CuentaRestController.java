package app.controller;

import app.model.account.*;
import app.model.web.ApiResponse;
import app.service.account.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/cuenta/{id}")
    public ApiResponse<?> setTarjetaCreditoCuenta(@PathVariable String id, @RequestBody TarjetaCredito tarjeta) {
        Cuenta cuenta = this.cuentaService.setTarjetaCredito(Long.parseLong(id), tarjeta);
        return new ApiResponse<Cuenta>(HttpStatus.OK.value(),"",cuenta);
    }

    @PutMapping("/cuenta/movimiento/deposito/{idCuenta}")
    public ApiResponse<?> nuevoMovimientoDeposito(@PathVariable String idCuenta, @RequestBody Dinero dinero) {
        Cuenta cuenta = this.cuentaService.nuevoMovimiento(Long.parseLong(idCuenta), EnumTipos.TipoMovimiento.DEPOSITAR, dinero);
        return new ApiResponse<Cuenta>(HttpStatus.OK.value(),"",cuenta);
    }

    @PutMapping("/cuenta/movimiento/retiro/{idCuenta}")
    public ApiResponse<?> nuevoMovimientoRetiro(@PathVariable String idCuenta, @RequestBody Dinero dinero) {
        Cuenta cuenta = this.cuentaService.nuevoMovimiento(Long.parseLong(idCuenta), EnumTipos.TipoMovimiento.RETIRAR, dinero);
        return new ApiResponse<Cuenta>(HttpStatus.OK.value(),"",cuenta);
    }

    @PutMapping("/cuenta/movimiento/credito/{idCuenta}")
    public ApiResponse<?> nuevoMovimientoCredito(@PathVariable String idCuenta, @RequestBody Dinero dinero) {
        Cuenta cuenta = this.cuentaService.nuevoMovimiento(Long.parseLong(idCuenta), EnumTipos.TipoMovimiento.CREDITO, dinero);
        return new ApiResponse<Cuenta>(HttpStatus.OK.value(),"",cuenta);
    }
}
