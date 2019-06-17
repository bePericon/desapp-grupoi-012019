package app.controller;

import app.model.web.ApiResponse;
import app.model.event.Invitacion;
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


    // Api para Estoy invitado y estan en curso
    @GetMapping("/pendientes/{id}")
    public ApiResponse<List<Invitacion>> getInvitacionesEnCurso(@PathVariable String id) {
        List<Invitacion> invitaciones = this.invitacionService.getInvitacionesEnCurso(Long.parseLong(id));
        return new ApiResponse<List<Invitacion>>(HttpStatus.OK.value(),"", invitaciones);
    }

}
