import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-mostrar-template',
  templateUrl: './mostrar-template.component.html',
  styleUrls: ['./mostrar-template.component.scss']
})
export class MostrarTemplateComponent implements OnInit {
  templates;
  constructor() {
    //esto deberia venir desde un servicio
    this.templates = [
    { nombre: "t1", descripcion: "sarasa" },
    { nombre: "t2", descripcion: "sadsad" },
    { nombre: "t3", descripcion: "nombre de template" },
    { nombre: "t4", descripcion: "sasaadsadsadasrasa" },
    { nombre: "t5", descripcion: "sarasasdsadsadsaa" }]
  }

  ngOnInit() {
  }

}
