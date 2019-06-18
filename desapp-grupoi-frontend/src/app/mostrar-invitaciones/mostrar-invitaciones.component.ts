import { Component, OnInit } from '@angular/core';
import { InvitacionesService } from './../services/invitaciones.service';
import { Invitacion } from '../model/invitacion.model';
@Component({
  selector: 'app-mostrar-invitaciones',
  templateUrl: './mostrar-invitaciones.component.html',
  styleUrls: ['./mostrar-invitaciones.component.scss']
})
export class MostrarInvitacionesComponent implements OnInit {

  invitaciones: Invitacion[];

  constructor(private invitacionesService: InvitacionesService) {
  }

  ngOnInit() {
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
