import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-mostrar-eventos',
  templateUrl: './mostrar-eventos.component.html',
  styleUrls: ['./mostrar-eventos.component.scss']
})
export class MostrarEventosComponent implements OnInit {

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
