import { Component, OnInit, ViewChild, QueryList, ViewChildren } from '@angular/core';
import { InvitacionesService } from './../services/invitaciones.service';
import { MostrarInvitacionesComponent } from './../mostrar-invitaciones/mostrar-invitaciones.component';
import { ParentComponentApi } from '../crear-evento/crear-evento.component';

@Component({
  selector: 'app-invitaciones',
  templateUrl: './invitaciones.component.html',
  styleUrls: ['./invitaciones.component.scss']
})

export class InvitacionesComponent implements OnInit {

  @ViewChild('aPendientes') aPendientes: MostrarInvitacionesComponent;
  @ViewChild('aAceptados') aAceptados: MostrarInvitacionesComponent;
  @ViewChild('aPasados') aPasados: MostrarInvitacionesComponent;
  @ViewChild('aRechazados') aRechazados: MostrarInvitacionesComponent;

  constructor(private invitacionesService: InvitacionesService) { }

  ngOnInit() {
    this.actualizar();
  }
  
  actualizar(){
    this.aPendientes.getPendientes();
    this.aAceptados.getAceptadas();
    this.aPasados.getPasadas();
    this.aRechazados.getRechazadas();
  }

  getParentApi(): ParentComponentApi {
    return {
      callParentMethod: () => {
        this.actualizar()
      }
    }
  }
}
