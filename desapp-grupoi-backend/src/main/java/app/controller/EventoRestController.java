package app.controller;

import app.model.account.Cuenta;
import app.model.account.Usuario;
import app.model.event.Evento;
import app.service.account.CuentaService;
import app.service.account.UsuarioService;
import app.service.event.EventoService;
import app.util.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = {"app/evento"})
@EnableAutoConfiguration
public class EventoRestController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private CuentaService cuentaService;

    // Api para Mas populares
    @GetMapping("/populares/all")
    public ResponseEntity<List<Evento>> getAllEventos() {
        List<Evento> eventos = this.eventoService.getAllEventos();
        return new ResponseEntity<List<Evento>>(eventos, HttpStatus.OK);
    }

    @GetMapping("/populares/cuenta/{id}")
    public ResponseEntity<List<Evento>> getMisEventos(@PathVariable String id) {
        List<Evento> eventos = this.eventoService.getEventosByCuentaId(Long.parseLong(id));
        return new ResponseEntity<List<Evento>>(eventos, HttpStatus.OK);
    }

    @GetMapping("/populares/usuario/{id}")
    public ResponseEntity<List<Evento>> getEventosQueEstoyInvitado(@PathVariable String id) {
        List<Evento> eventos = this.eventoService.getEventosInvitado(Long.parseLong(id));
        return new ResponseEntity<List<Evento>>(eventos, HttpStatus.OK);
    }

    // Api para Pasados
    @GetMapping("/pasados/all")
    public ResponseEntity<List<Evento>> getAllEventosPasados() {
        List<Evento> eventos = this.eventoService.getAllEventosPasados();
        return new ResponseEntity<List<Evento>>(eventos, HttpStatus.OK);
    }

    @GetMapping("/pasados/cuenta/{id}")
    public ResponseEntity<List<Evento>> getMisEventosPasados(@PathVariable String id) {
        List<Evento> eventos = this.eventoService.getEventosByCuentaIdPasados(Long.parseLong(id));
        return new ResponseEntity<List<Evento>>(eventos, HttpStatus.OK);
    }

    @GetMapping("/pasados/usuario/{id}")
    public ResponseEntity<List<Evento>> getEventosQueEstoyInvitadoPasados(@PathVariable String id) {
        List<Evento> eventos = this.eventoService.getEventosInvitadoPasados(Long.parseLong(id));
        return new ResponseEntity<List<Evento>>(eventos, HttpStatus.OK);
    }
}
