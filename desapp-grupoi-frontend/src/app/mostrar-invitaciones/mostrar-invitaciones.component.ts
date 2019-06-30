import { Component, OnInit, Input } from '@angular/core';
import { InvitacionesService } from './../services/invitaciones.service';
import { Invitacion } from '../model/invitacion.model';
import { ParentComponentApi } from '../crear-evento/crear-evento.component';
@Component({
  selector: 'app-mostrar-invitaciones',
  templateUrl: './mostrar-invitaciones.component.html',
  styleUrls: ['./mostrar-invitaciones.component.scss']
})
export class MostrarInvitacionesComponent implements OnInit {

  invitaciones: Invitacion[];

  @Input() mostrarAcciones: boolean;

  @Input() parentApi: ParentComponentApi

  constructor(private invitacionesService: InvitacionesService) {
  }

  ngOnInit() {
  }

  getParentApi(): ParentComponentApi {
    return {
      callParentMethod: () => {
        this.parentApi.callParentMethod();
      }
    }
  }

  getPendientes(){
    this.invitacionesService.getPendientes()
      .subscribe(res => {
        this.invitaciones = res.result as Invitacion[];
      });
  }

  getPasadas(){
    this.invitacionesService.getPasadas()
      .subscribe(res => {
        this.invitaciones = res.result as Invitacion[];
      });
  }

  getAceptadas(){
    this.invitacionesService.getAceptadas()
      .subscribe(res => {
        this.invitaciones = res.result as Invitacion[];
      });
  }

  getRechazadas(){
    this.invitacionesService.getRechazadas()
      .subscribe(res => {
        this.invitaciones = res.result as Invitacion[];
      });
  }

}
