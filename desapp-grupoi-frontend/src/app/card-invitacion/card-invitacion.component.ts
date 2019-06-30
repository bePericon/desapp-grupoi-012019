import { UtilsService } from './../services/utils.service';
import { InvitacionesService } from './../services/invitaciones.service';
import { Component, OnInit, Input } from '@angular/core';
import { Invitacion } from '../model/invitacion.model';
import { ParentComponentApi } from '../crear-evento/crear-evento.component';

@Component({
  selector: 'app-card-invitacion',
  templateUrl: './card-invitacion.component.html',
  styleUrls: ['./card-invitacion.component.scss']
})
export class CardInvitacionComponent implements OnInit {

  @Input() invitacion: Invitacion;

  @Input() mostrarAcciones: boolean;

  @Input() parentApi: ParentComponentApi

  constructor(
    private invitacionService: InvitacionesService,
    private uService: UtilsService) { }

  ngOnInit() {
  }

  confirmarAsistencia(){
    this.invitacionService.confirmarInvitacion(this.invitacion)
      .subscribe(res => {
        this.uService.notificacion("Se confirmo la invitación!", "");
        this.parentApi.callParentMethod();
      });    
  }

  rechazarInvitacion(){
    this.invitacionService.rechazarInvitacion(this.invitacion)
      .subscribe(res => {
        this.uService.notificacion("Se rechazó la invitación!", "");
        this.parentApi.callParentMethod();
      });
  }

}
