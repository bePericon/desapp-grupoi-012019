import { Component, OnInit, Input } from '@angular/core';
import { TemplateEvento } from '../model/template-evento.model';
import { MatDialog } from '@angular/material';
import { EventoService } from '../services/evento.service';

@Component({
  selector: 'app-mostrar-template',
  templateUrl: './mostrar-template.component.html',
  styleUrls: ['./mostrar-template.component.scss']
})
export class MostrarTemplateComponent implements OnInit {
  
  
  @Input() templates: TemplateEvento[];
  @Input() esPublico: Boolean

  constructor(private eventoSrv: EventoService) {
    // this.pidioQueSeaPublico=false
  }

  ngOnInit() {
  }

  hacerPublico(template: TemplateEvento){
    this.eventoSrv.hacerPublico(template.id)
      .subscribe(res => {
        console.log(res.message);
    });
  }


  elegirTemplate(t){
    alert("implementar elegir template")
    
  }

}