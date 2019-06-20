import { Component, OnInit, Input } from '@angular/core';
import { TemplateEvento } from '../model/templateEvento.model';

@Component({
  selector: 'app-mostrar-template',
  templateUrl: './mostrar-template.component.html',
  styleUrls: ['./mostrar-template.component.scss']
})
export class MostrarTemplateComponent implements OnInit {
  
  
  @Input() templates: TemplateEvento[];
  // templates

  constructor() {

  }

  ngOnInit() {
    // this.templates = [];
  }

  elegirTemplate(t){
    console.log("Esto no le gusta al profe, pero: Felicitaciones, elegiste el template "+ t.nombre+ "que es "+ t.descripcion)
  }

  mostrarTemplates(){
    console.log("MOSTRAR:")
    console.log(this.templates)
  }

  esMiTemplate(){
    // le pasa el tempalte y compara el id de usuario con el de session y elige
    return true;  
  }

}
