import { MostrarEventosComponent } from './../mostrar-eventos/mostrar-eventos.component';
import { EventoService } from './../services/evento.service';
import { Evento } from './../model/evento.model';
import { Component, OnInit, ViewChild, QueryList, ViewChildren } from '@angular/core';
import { ParentComponentApi } from '../crear-evento/crear-evento.component';


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
    this.actualizar();
  }

  private actualizar() {
    this.ameMasPopulares.getMasPopulares();
    this.amePasados.getPasados();
    this.ameInvitaronEnCurso.getInvitaronEnCurso();
    this.ameCreadosPorMi.getCreadosPorMi();
  }

  getParentApi(): ParentComponentApi {
    return {
      callParentMethod: () => {
        this.actualizar()
      }
    }
  }

}
