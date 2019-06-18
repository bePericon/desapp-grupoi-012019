package app.controller;

import app.model.web.ApiResponse;
import app.model.event.Invitacion;
import app.model.web.Invitaciones;
import app.service.event.InvitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping(value = {"app/invitacion"})
@EnableAutoConfiguration
public class InvitacionController {

    @Autowired
    private InvitacionService invitacionService;

    @GetMapping("/pendiente/{idUsuario}")
    public ApiResponse<List<Invitacion>> getInvitacionesPendientes(@PathVariable String idUsuario) {
        List<Invitacion> invitaciones = this.invitacionService.getInvitacionesPendientes(Long.parseLong(idUsuario));
        return new ApiResponse<List<Invitacion>>(HttpStatus.OK.value(),"", invitaciones);
    }

    @GetMapping("/aceptada/{idUsuario}")
    public ApiResponse<List<Invitacion>> getInvitacionesAceptadasEnCurso(@PathVariable String idUsuario) {
        List<Invitacion> invitaciones = this.invitacionService.getInvitacionesAceptadasEnCurso(Long.parseLong(idUsuario));
        return new ApiResponse<List<Invitacion>>(HttpStatus.OK.value(),"", invitaciones);
    }

    @GetMapping("/pasada/{idUsuario}")
    public ApiResponse<List<Invitacion>> getInvitacionesPasadas(@PathVariable String idUsuario) {
        List<Invitacion> invitaciones = this.invitacionService.getInvitacionesPasadas(Long.parseLong(idUsuario));
        return new ApiResponse<List<Invitacion>>(HttpStatus.OK.value(),"", invitaciones);
    }

    @GetMapping("/rechazada/{idUsuario}")
    public ApiResponse<List<Invitacion>> getInvitacionesRechazadas(@PathVariable String idUsuario) {
        List<Invitacion> invitaciones = this.invitacionService.getInvitacionesRechazadas(Long.parseLong(idUsuario));
        return new ApiResponse<List<Invitacion>>(HttpStatus.OK.value(),"", invitaciones);
    }

    @PostMapping("/nuevalista/")
    public ApiResponse<?> guardarListaInvitaciones(@RequestBody Invitaciones invitaciones) {
        this.invitacionService.guardarListaInvitaciones(invitaciones);
        return new ApiResponse<Boolean>(HttpStatus.CREATED.value(),"", true);
    }

}
