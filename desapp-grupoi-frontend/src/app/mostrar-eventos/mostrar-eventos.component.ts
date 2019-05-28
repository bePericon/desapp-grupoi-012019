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

    //esto deberia venir desde un servicio
    // this.eventos=[{ nombreEvento: "fiesta", descripcion: "sarasa" },
    // { nombreEvento: "fiest2", descripcion: "sadsad" },
    // { nombreEvento: "baquitaloca", descripcion: "baquita de prueba" },
    // { nombreEvento: "canastulli", descripcion: "sasaadsadsadasrasa" },
    // { nombreEvento: "una cosa", descripcion: "sarasasdsadsadsaa" }]

  }

  ngOnInit() {
    
  }


  getMasPopulares(){
    this.eventoService.getEventosPopulares()
    .subscribe(res => {
      this.eventos = res as Evento[];
    });
  }

  getPasados(){
    this.eventoService.getEventosPasados()
    .subscribe(res => {
      this.eventos = res as Evento[];
    });
  }

  getInvitaronEnCurso(){
    this.eventoService.getEventosInvitaronEnCurso()
    .subscribe(res => {
      this.eventos = res as Evento[];
    });
  }

  getCreadosPorMi(){
    this.eventoService.getEventos()
    .subscribe(res => {
      this.eventos = res as Evento[];
    });
  }

  
}
