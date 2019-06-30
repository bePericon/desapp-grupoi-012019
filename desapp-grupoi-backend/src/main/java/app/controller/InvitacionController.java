package app.controller;

import app.model.web.ApiResponse;
import app.model.event.Invitacion;
import app.model.web.InvitacionUsuario;

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

    @PutMapping("/confirmar")
    public ApiResponse<Invitacion> confirmarInvitacion(@RequestBody InvitacionUsuario invitacionUsuario) {
        Invitacion invitacion = this.invitacionService.confirmarInvitacion(invitacionUsuario);
        return new ApiResponse<Invitacion>(HttpStatus.OK.value(), "La invitacion fue confirmada.", invitacion);
    }

    @PutMapping("/rechazar")
    public ApiResponse<Invitacion> rechazarInvitacion(@RequestBody InvitacionUsuario invitacionUsuario) {
        Invitacion invitacion = this.invitacionService.rechazarInvitacion(invitacionUsuario);
        return new ApiResponse<Invitacion>(HttpStatus.OK.value(), "La invitacion fue rechazada.", invitacion);
    }
}
