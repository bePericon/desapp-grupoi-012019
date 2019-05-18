package app.controller;

import app.model.account.Cuenta;
import app.model.account.Dinero;
import app.service.account.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
        if (cuenta == null) {
            CustomErrorType error = new CustomErrorType("No se encontro ninguna cuenta para el usuario con id: " + id);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Cuenta>(cuenta, HttpStatus.OK);
    }
}
