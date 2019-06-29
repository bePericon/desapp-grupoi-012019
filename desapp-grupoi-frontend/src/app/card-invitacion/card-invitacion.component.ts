import { InvitacionesService } from './../services/invitaciones.service';
import { Component, OnInit, Input } from '@angular/core';
import { Invitacion } from '../model/invitacion.model';

@Component({
  selector: 'app-card-invitacion',
  templateUrl: './card-invitacion.component.html',
  styleUrls: ['./card-invitacion.component.scss']
})
export class CardInvitacionComponent implements OnInit {

  @Input() invitacion: Invitacion;

  constructor(private invitacionService: InvitacionesService) { }

  ngOnInit() {
  }

  confirmarAsistencia(){
    confirm("El id de la invitacion: "+ this.invitacion.id);
  }

  rechazarInvitacion(){

  }

}
