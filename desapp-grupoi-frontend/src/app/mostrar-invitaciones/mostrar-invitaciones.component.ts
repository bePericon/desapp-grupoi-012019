import { Component, OnInit } from '@angular/core';
import { InvitacionesService } from './../services/invitaciones.service';
import { Invitacion } from '../model/invitacion.model';
@Component({
  selector: 'app-mostrar-invitaciones',
  templateUrl: './mostrar-invitaciones.component.html',
  styleUrls: ['./mostrar-invitaciones.component.scss']
})
export class MostrarInvitacionesComponent implements OnInit {

  invitaciones = [];
  // invitaciones = Invitacion[];

  constructor(private invitacionesService: InvitacionesService) {
  }

  ngOnInit() {
  }

  getPendientes(){
    this.invitaciones = this.invitacionesService.getPendientes()
    // this.invitacionesService.getPendientes()
    // .subscribe(res => {
      // habilitarBotones = true;
    //   this.invitaciones = res as Invitacion[];
    // });
  }

  getPasadas(){
    this.invitaciones = this.invitacionesService.getPasadas()
    // this.invitacionesService.getPasadas()
  //   .subscribe(res => {
  //     this.invitaciones = res as Invitacion[];
  //   });
  }

  getAceptadas(){
    this.invitaciones = this.invitacionesService.getAceptadas()
  //   this.invitacionesService.getAceptadas()
  //   .subscribe(res => {
  //     this.invitaciones = res as Invitacion[];
  //   });
  }

  getRechazadas(){
    this.invitaciones = this.invitacionesService.getRechazadas()
  //   this.invitacionesService.getRechazadas()
  //   .subscribe(res => {
  //     this.invitaciones = res as Invitacion[];
  //   });
  }

}
