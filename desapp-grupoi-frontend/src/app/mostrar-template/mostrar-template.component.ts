import { Component, OnInit, Input } from '@angular/core';
import { TemplateEvento } from '../model/templateEvento.model';

@Component({
  selector: 'app-mostrar-template',
  templateUrl: './mostrar-template.component.html',
  styleUrls: ['./mostrar-template.component.scss']
})
export class MostrarTemplateComponent implements OnInit {
  
  
  @Input() templates: TemplateEvento[];
  @Input() esPublico: Boolean

  constructor() {

  }

  ngOnInit() {
  }

  elegirTemplate(t){
    alert("implementar elegir template")
    
  }




}
