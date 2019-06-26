import { AgregarItemsComponent } from './../agregar-items/agregar-items.component';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EventoService } from './../services/evento.service';
import { TemplateEvento } from '../model/template-evento.model';

export interface Combo {
  nombre: string;
  desc: string;
}

export interface ParentComponentApi {
  callParentMethod: () => void
}

@Component({
  selector: 'app-crear-evento',
  templateUrl: './crear-evento.component.html',
  styleUrls: ['./crear-evento.component.scss']
})

export class CrearEventoComponent implements OnInit {

  

  infoFiesta: String;
  infoCanasta: String;
  infoBaquitav1: String;
  infoBaquitav2: String;

  templatesPublicos: TemplateEvento[]
  templatesPrivados: TemplateEvento[]

  panelOpenState = false;

  invitados;

  @ViewChild(AgregarItemsComponent) agregarItemsComponent: AgregarItemsComponent;

  constructor(private _formBuilder: FormBuilder, private eventoService: EventoService) {
    

  }

  ngOnInit() {
    this.infoFiesta = "Los gastos corren por cuenta del organizador.";
    this.infoCanasta = "se presenta la lista de gastos a realizar y cada asistente elige un ítem del cual hacerse cargo.";
    this.infoBaquitav1 = "Una o más personas realizan las compras y luego se divide con los demás asistentes.";
    this.infoBaquitav2 = "Crea una cuenta común a la cual deberán girar su parte los asistentes y así el organizador dispone de fondos para las compras.";

    this.templatesPrivados = []
    this.templatesPublicos = []

    this.getTemplatesPublicos();
    this.getTemplatesPrivados();
  }

  getTemplatesPublicos() {
    this.eventoService.getTemplatesPublicos()
      .subscribe(res => {
        this.templatesPublicos = res.result as TemplateEvento[];
      });
  }

  getTemplatesPrivados() {
    this.eventoService.getTemplatesPrivados()
      .subscribe(res => {
        this.templatesPrivados = res.result as TemplateEvento[];

      });
  }


  getParentApi(): ParentComponentApi {
    return {
      callParentMethod: () => {
        this.recargar()
      }
    }
  }


  recargar(){
    this.getTemplatesPrivados();
    this.getTemplatesPublicos();
  }

}
