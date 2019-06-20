import { EventoService } from './../services/evento.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Evento } from '../model/evento.model';

@Component({
  selector: 'app-mostrar-eventos',
  templateUrl: './mostrar-eventos.component.html',
  styleUrls: ['./mostrar-eventos.component.scss']
})
export class MostrarEventosComponent implements OnInit {

  eventos: Evento[];

  constructor(private eventoService: EventoService) {
  }

  ngOnInit() {
  }


  getMasPopulares(){
    this.eventoService.getEventosPopulares()
    .subscribe(res => {
      this.eventos = res.result as Evento[];
    });
  }

  getPasados(){
    this.eventoService.getEventosPasados()
    .subscribe(res => {
      this.eventos = res.result as Evento[];
    });
  }

  getInvitaronEnCurso(){
    this.eventoService.getEventosInvitaronEnCurso()
    .subscribe(res => {
      this.eventos = res.result as Evento[];
    });
  }

  getCreadosPorMi(){
    this.eventoService.getEventos()
    .subscribe(res => {
      this.eventos = res.result as Evento[];
    });
  }

  
}
