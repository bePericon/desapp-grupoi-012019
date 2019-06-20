import { Component, OnInit, Input } from '@angular/core';
import { TemplateEvento } from '../model/templateEvento.model';

@Component({
  selector: 'app-mostrar-template',
  templateUrl: './mostrar-template.component.html',
  styleUrls: ['./mostrar-template.component.scss']
})
export class MostrarTemplateComponent implements OnInit {
  
  
  @Input() templates: TemplateEvento[];
  


  constructor() {
    //esto deberia venir desde un servicio
    // this.templates = [
    // { nombre: "Template uno", descripcion: "Este es un template loco",modalidad:"Fiesta" },
    // { nombre: "Otro loco", descripcion: "Este es otro template loco",modalidad:"Canasta" },
    // { nombre: "Locochón", descripcion: "Template locochón",modalidad:"Baquita con recoleccion previa" },
    // { nombre: "Nombre de tempalte", descripcion: "un cuarto template",modalidad:"Fiesta" },
    // { nombre: "aaaa", descripcion: "sarlanga",modalidad:"Canasta" }]
  }

  ngOnInit() {
  }

  elegirTemplate(t){
    console.log("Esto no le gusta al profe, pero: Felicitaciones, elegiste el template "+ t.nombre+ "que es "+ t.descripcion)
  }


  esMiTemplate(){
    // le pasa el tempalte y compara el id de usuario con el de session y elige
    return true;  
  }

}
