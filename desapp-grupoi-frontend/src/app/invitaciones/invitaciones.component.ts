import { Component, OnInit, ViewChild, QueryList, ViewChildren } from '@angular/core';
import { InvitacionesService } from './../services/invitaciones.service';
import { MostrarInvitacionesComponent } from './../mostrar-invitaciones/mostrar-invitaciones.component';

@Component({
  selector: 'app-invitaciones',
  templateUrl: './invitaciones.component.html',
  styleUrls: ['./invitaciones.component.scss']
})

export class InvitacionesComponent implements OnInit {

  variable: any;

  @ViewChild('aPendientes') aPendientes: MostrarInvitacionesComponent;
  @ViewChild('aAceptados') aAceptados: MostrarInvitacionesComponent;
  @ViewChild('aPasados') aPasados: MostrarInvitacionesComponent;
  @ViewChild('aRechazados') aRechazados: MostrarInvitacionesComponent;

  constructor(private invitacionesService: InvitacionesService) { }

  ngOnInit() {

    this.aPendientes.getPendientes();
    this.aAceptados.getAceptadas();
    this.aPasados.getPasadas();
    this.aRechazados.getRechazadas();
  }


}
