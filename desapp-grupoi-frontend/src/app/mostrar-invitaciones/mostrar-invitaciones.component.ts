import { Component, OnInit } from '@angular/core';
import { InvitacionesService } from './../services/invitaciones.service';
import { Invitacion } from '../model/invitacion.model';
@Component({
  selector: 'app-mostrar-invitaciones',
  templateUrl: './mostrar-invitaciones.component.html',
  styleUrls: ['./mostrar-invitaciones.component.scss']
})
export class MostrarInvitacionesComponent implements OnInit {

  invitaciones =[];

  constructor(private invitacionesService: InvitacionesService) {

    
  }

  ngOnInit() {
  }


  getPendientes(){
    this.invitaciones = this.invitacionesService.getPendientes();
    // .subscribe(res => {
    //   this.invitaciones = res as Invitacion[];
    // });
  }

  getPasadas(){
    this.invitaciones = this.invitacionesService.getPasadas();
  //   this.eventoService.getEventosPasados()
  //   .subscribe(res => {
  //     this.eventos = res as Evento[];
  //   });
  }

  getAceptadas(){
    this.invitaciones = this.invitacionesService.getAceptadas();
  //   this.eventoService.getEventosInvitaronEnCurso()
  //   .subscribe(res => {
  //     this.eventos = res as Evento[];
  //   });
  }

  getRechazadas(){
    this.invitaciones = this.invitacionesService.getRechazadas();
  //   this.eventoService.getEventos()
  //   .subscribe(res => {
  //     this.eventos = res as Evento[];
  //   });
  }


}
