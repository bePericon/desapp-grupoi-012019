package app.controller;

import app.model.web.ApiResponse;
import app.model.web.NewEvento;
import app.model.web.NewTemplate;
import app.model.event.Evento;
import app.model.event.Template;
import app.service.account.CuentaService;
import app.service.event.EventoService;
import app.service.event.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
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
    @GetMapping("/populares/all")
    public ApiResponse<List<Evento>> getAllEventos() {
        List<Evento> eventos = this.eventoService.getAllEventos();
        return new ApiResponse<List<Evento>>(HttpStatus.OK.value(), "Todos los eventos.", eventos);
    }

    // Api para Pasados
    @GetMapping("/pasados/all")
    public ApiResponse<List<Evento>> getAllEventosPasados() {
        List<Evento> eventos = this.eventoService.getAllEventosPasados();
        return new ApiResponse<List<Evento>>(HttpStatus.OK.value(),"", eventos);
    }

    @GetMapping("/pasados/cuenta/{id}") //Creados por el usuario.
    public ApiResponse<List<Evento>> getMisEventosPasados(@PathVariable String id) {
        List<Evento> eventos = this.eventoService.getEventosPasadosByIdCuenta(Long.parseLong(id));
        return new ApiResponse<List<Evento>>(HttpStatus.OK.value(),"", eventos);
    }

    @GetMapping("/pasados/usuario/{id}") //Que invitaron al usuario.
    public ApiResponse<List<Evento>> getEventosMeInvitaronPasados(@PathVariable String id) {
        List<Evento> eventos = this.eventoService.getEventosMeInvitaronPasados(Long.parseLong(id));
        return new ApiResponse<List<Evento>>(HttpStatus.OK.value(),"", eventos);
    }

    // Api para Estoy invitado y estan en curso
    @GetMapping("/encurso/usuario/{id}")
    public ApiResponse<List<Evento>> getEventosMeInvitaronEnCurso(@PathVariable String id) {
        List<Evento> eventos = this.eventoService.getEventosInvitadoEnCurso(Long.parseLong(id));
        return new ApiResponse<List<Evento>>(HttpStatus.OK.value(),"", eventos);
    }

    // Api para Creados por mi
    @GetMapping("/cuenta/{id}") //Los eventos creados por el usuario.
    public ApiResponse<List<Evento>> getMisEventos(@PathVariable String id) {
        List<Evento> eventos = this.eventoService.getEventosByCuentaId(Long.parseLong(id));
        return new ApiResponse<List<Evento>>(HttpStatus.OK.value(),"", eventos);
    }

    @PostMapping("/{id}") //Crear evento para el usuario con Id
    public ApiResponse nuevoEvento(@PathVariable String id, @RequestBody NewEvento nuevoEvento) {
        Evento evento = this.eventoService.createNuevoEvento(Long.parseLong(id), nuevoEvento);
        return new ApiResponse<Template>(HttpStatus.CREATED.value(),"",evento);
    }

    // Api para Templates
    @PostMapping("/template/{id}") //Crear template para el usuario con Id
    public ApiResponse nuevoTemplate(@PathVariable String id, @RequestBody NewTemplate nuevoTemplate) {
        Template template = this.templateService.createNuevoTemplate(Long.parseLong(id), nuevoTemplate);
        return new ApiResponse<Template>(HttpStatus.CREATED.value(),"",template);
    }

    @GetMapping("/template/{id}") //Retorna el template con ese id.
    public ApiResponse<Template> getTemplateById(@PathVariable String id) {
        Template template = this.templateService.getTemplateById(Long.parseLong(id));
        return new ApiResponse<Template>(HttpStatus.OK.value(),"",template);
    }

    @GetMapping("/template/usuario/{id}") //Retorna los templates del usuario con id.
    public ApiResponse<List<Template>> getTemplatesByIdUsuario(@PathVariable String id) {
        List<Template> templates = this.templateService.getTemplatesByIdUsuario(Long.parseLong(id));
        return new ApiResponse<List<Template>>(HttpStatus.OK.value(),"",templates);
    }
    
    
    @GetMapping("/template/publicos") //todos los templates publicos
    public ApiResponse<List<Template>> getTemplatesPublicos() {
    	List<Template> templates = this.templateService.getTemplatesPublicos();
        return new ApiResponse<List<Template>>(HttpStatus.OK.value(),"", templates);
    }
    
    @PutMapping("/template/publico/{idTemplate}")
    public ApiResponse<Template> actualizarTemplateAPublico(@PathVariable String idTemplate) {
        Template templateN = this.templateService.actualizarTemplateAPublico(Long.parseLong(idTemplate));
        return new ApiResponse<Template>(HttpStatus.OK.value(), "El template ahora es Público.", templateN);
    }

}
