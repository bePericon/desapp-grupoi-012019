package app.controller;

import app.model.account.Cuenta;
import app.model.account.TarjetaCredito;
import app.service.account.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.util.CustomErrorType;

@RestController
@RequestMapping(value = {"app"})
@EnableAutoConfiguration
public class CuentaRestController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping("/cuenta/usuario/{id}")
    public ResponseEntity<?> getCuentaByIdUsuario(@PathVariable String id) {
        Cuenta cuenta = (Cuenta) this.cuentaService.getCuentaByIdUsuario(Long.parseLong(id));
        return new ResponseEntity<Cuenta>(cuenta, HttpStatus.OK);
    }

    @PutMapping("/cuenta/{id}")
    public ResponseEntity<?> setTarjetaCreditoCuenta(@PathVariable String id, @RequestBody TarjetaCredito tarjeta) {
        Cuenta cuenta = this.cuentaService.setTarjetaCredito(Long.parseLong(id), tarjeta);
        return new ResponseEntity<Cuenta>(cuenta, HttpStatus.OK);
    }
}
