import { Component, OnInit, Input } from '@angular/core';
import { TemplateEvento } from '../model/template-evento.model';
import { ParentComponentApi, ApiCargaTemplate } from '../crear-evento/crear-evento.component';
import { EventoService } from '../services/evento.service';

@Component({
  selector: 'app-mostrar-template',
  templateUrl: './mostrar-template.component.html',
  styleUrls: ['./mostrar-template.component.scss']
})
export class MostrarTemplateComponent implements OnInit {
  
  
  @Input() templates: TemplateEvento[];
  @Input() esPublico: Boolean
  @Input() templateApi: ApiCargaTemplate

  constructor(private eventoSrv: EventoService) {  }

  ngOnInit() {  }

  
  hacerPublico(template: TemplateEvento){
    this.eventoSrv.hacerPublico(template.id)
      .subscribe(res => {
        console.log(res.message);
    });
  }


  elegirTemplate(t){
    console.log(t)
    this.templateApi.cargarTemplate(t)
  }





}