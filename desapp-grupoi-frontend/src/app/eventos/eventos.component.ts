import { MostrarEventosComponent } from './../mostrar-eventos/mostrar-eventos.component';
import { EventoService } from './../services/evento.service';
import { Evento } from './../model/evento.model';
import { Component, OnInit, ViewChild, QueryList, ViewChildren } from '@angular/core';


@Component({
  selector: 'app-eventos',
  templateUrl: './eventos.component.html',
  styleUrls: ['./eventos.component.scss']
})
export class EventosComponent implements OnInit {

  variable: any;

  @ViewChild('ameMasPopulares') ameMasPopulares: MostrarEventosComponent;
  @ViewChild('amePasados') amePasados: MostrarEventosComponent;
  @ViewChild('ameInvitaronEnCurso') ameInvitaronEnCurso: MostrarEventosComponent;
  @ViewChild('ameCreadosPorMi') ameCreadosPorMi: MostrarEventosComponent;

  constructor(private eventoService: EventoService) { }

  ngOnInit() {
    this.ameMasPopulares.getMasPopulares();
    this.amePasados.getPasados();
    this.ameInvitaronEnCurso.getInvitaronEnCurso();
    this.ameCreadosPorMi.getCreadosPorMi();
  }

  getDatos() {
  }
  
  sas(){
    alert("sarasa")
  }
  // showConfig() {
  //   this.eventoSrv.getConfig()
  //     .subscribe((data: Config) => this.config = {
  //         heroesUrl: data['heroesUrl'],
  //         textfile:  data['textfile']
  //     });
  // }

}
