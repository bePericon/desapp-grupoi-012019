import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-mostrar-invitaciones',
  templateUrl: './mostrar-invitaciones.component.html',
  styleUrls: ['./mostrar-invitaciones.component.scss']
})
export class MostrarInvitacionesComponent implements OnInit {

  eventos = [];

  constructor() { 

    //esto deberia venir desde un servicio
    this.eventos=[{ nombreEvento: "fiesta", descripcion: "sarasa" },
    { nombreEvento: "fiest2", descripcion: "sadsad" },
    { nombreEvento: "baquitaloca", descripcion: "baquita de prueba" },
    { nombreEvento: "canastulli", descripcion: "sasaadsadsadasrasa" },
    { nombreEvento: "una cosa", descripcion: "sarasasdsadsadsaa" }]
  }

  ngOnInit() {
  }

}
