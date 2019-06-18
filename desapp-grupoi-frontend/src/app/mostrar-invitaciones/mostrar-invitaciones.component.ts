import { Component, OnInit } from '@angular/core';
import { InvitacionesService } from './../services/invitaciones.service';
import { Invitacion } from '../model/invitacion.model';
@Component({
  selector: 'app-mostrar-invitaciones',
  templateUrl: './mostrar-invitaciones.component.html',
  styleUrls: ['./mostrar-invitaciones.component.scss']
})
export class MostrarInvitacionesComponent implements OnInit {

  invitacioness = [];
  // invitaciones = Invitacion[];

  constructor(private invitacionesService: InvitacionesService) {
  }

  ngOnInit() {
  }

  getPendientes(){
    this.invitacioness = this.invitacionesService.getPendientes()
    // this.invitacionesService.getPendientes()
    // .subscribe(res => {
      // habilitarBotones = true;
    //   this.invitaciones = res as Invitacion[];
    // });
  }

  getPasadas(){
    this.invitacioness = this.invitacionesService.getPasadas()
    // this.invitacionesService.getPasadas()
  //   .subscribe(res => {
  //     this.invitaciones = res as Invitacion[];
  //   });
  }

  getAceptadas(){
    this.invitacioness = this.invitacionesService.getAceptadas()
  //   this.invitacionesService.getAceptadas()
  //   .subscribe(res => {
  //     this.invitaciones = res as Invitacion[];
  //   });
  }

  getRechazadas(){
    this.invitacioness = this.invitacionesService.getRechazadas()
  //   this.invitacionesService.getRechazadas()
  //   .subscribe(res => {
  //     this.invitaciones = res as Invitacion[];
  //   });
  }

}
