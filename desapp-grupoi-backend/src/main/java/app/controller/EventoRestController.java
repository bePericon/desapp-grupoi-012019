package app.controller;

import app.model.NewTemplate;
import app.model.event.Evento;
import app.model.event.Template;
import app.service.account.CuentaService;
import app.service.event.EventoService;
import app.service.event.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping(value = {"app/evento"})
@EnableAutoConfiguration
public class EventoRestController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private TemplateService templateService;

    // Api para Mas populares
    // TODO: crear calificacion.
    @GetMapping("/populares/all")
    public ResponseEntity<List<Evento>> getAllEventos() {
        List<Evento> eventos = this.eventoService.getAllEventos();
        return new ResponseEntity<List<Evento>>(eventos, HttpStatus.OK);
    }

    // Api para Pasados
    @GetMapping("/pasados/all")
    public ResponseEntity<List<Evento>> getAllEventosPasados() {
        List<Evento> eventos = this.eventoService.getAllEventosPasados();
        return new ResponseEntity<List<Evento>>(eventos, HttpStatus.OK);
    }

    @GetMapping("/pasados/cuenta/{id}") //Creados por el usuario.
    public ResponseEntity<List<Evento>> getMisEventosPasados(@PathVariable String id) {
        List<Evento> eventos = this.eventoService.getEventosPasadosByIdCuenta(Long.parseLong(id));
        return new ResponseEntity<List<Evento>>(eventos, HttpStatus.OK);
    }

    @GetMapping("/pasados/usuario/{id}") //Que invitaron al usuario.
    public ResponseEntity<List<Evento>> getEventosMeInvitaronPasados(@PathVariable String id) {
        List<Evento> eventos = this.eventoService.getEventosMeInvitaronPasados(Long.parseLong(id));
        return new ResponseEntity<List<Evento>>(eventos, HttpStatus.OK);
    }

    // Api para Estoy invitado y estan en curso
    @GetMapping("/encurso/usuario/{id}")
    public ResponseEntity<List<Evento>> getEventosMeInvitaronEnCurso(@PathVariable String id) {
        List<Evento> eventos = this.eventoService.getEventosInvitadoEnCurso(Long.parseLong(id));
        return new ResponseEntity<List<Evento>>(eventos, HttpStatus.OK);
    }

    // Api para Creados por mi
    @GetMapping("/cuenta/{id}") //Los eventos creados por el usuario.
    public ResponseEntity<List<Evento>> getMisEventos(@PathVariable String id) {
        List<Evento> eventos = this.eventoService.getEventosByCuentaId(Long.parseLong(id));
        return new ResponseEntity<List<Evento>>(eventos, HttpStatus.OK);
    }

    // Api para Templates
    @PostMapping("/template/{id}") //Crear template apara el usuario con Id
    public ResponseEntity nuevoTemplate(@PathVariable String id, @RequestBody NewTemplate nuevoTemplate) {
        Template template = this.templateService.createNuevoTemplate(Long.parseLong(id), nuevoTemplate);
        return new ResponseEntity<Template>(template,HttpStatus.CREATED);
    }

    @GetMapping("/template/{id}") //Retorna el template con ese id.
    public ResponseEntity<Template> getTemplateById(@PathVariable String id) {
        Template template = this.templateService.getTemplateById(Long.parseLong(id));
        return new ResponseEntity<Template>(template, HttpStatus.OK);
    }

    @GetMapping("/template/usuario/{id}") //Retorna los templates del usuario con id.
    public ResponseEntity<List<Template>> getTemplatesByIdUsuario(@PathVariable String id) {
        List<Template> templates = this.templateService.getTemplatesByIdUsuario(Long.parseLong(id));
        return new ResponseEntity<List<Template>>(templates, HttpStatus.OK);
    }
}
