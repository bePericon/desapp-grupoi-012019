import { AgregarItemsComponent } from './../agregar-items/agregar-items.component';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EventoService } from './../services/evento.service';
import { TemplateEvento } from '../model/template-evento.model';
import { UtilsService } from '../services/utils.service';

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

  modalidadControl: FormControl;
  nombreForm: FormGroup;
  descripcionForm: FormGroup;
  fechaForm: FormGroup;
  itemForm: FormGroup;
  emailForm: FormGroup;

  invitados: string[];

  modalidadSeleccionada: string;

  @ViewChild(AgregarItemsComponent) agregarItemsComponent: AgregarItemsComponent;

  panelOpenState = false;

  constructor(
    private _formBuilder: FormBuilder, 
    private eventoService: EventoService,
    private uService: UtilsService) { }

  ngOnInit() {
    this.infoFiesta = "Los gastos corren por cuenta del organizador.";
    this.infoCanasta = "Se presenta la lista de gastos a realizar y cada asistente elige un ítem del cual hacerse cargo.";
    this.infoBaquitav1 = "Una o más personas realizan las compras y luego se divide con los demás asistentes.";
    this.infoBaquitav2 = "Crea una cuenta común a la cual deberán girar su parte los asistentes y así el organizador dispone de fondos para las compras.";

    this.templatesPrivados = []
    this.templatesPublicos = []

    this.getTemplatesPublicos();
    this.getTemplatesPrivados();

    this.nombreForm = this._formBuilder.group({
      nombreCtrl: ['', Validators.required]
    });
    this.descripcionForm = this._formBuilder.group({
      descripcionCtrl: ['', Validators.required]
    });
    this.fechaForm = this._formBuilder.group({
      fechaCtrl: ['', Validators.required]
    });
    this.emailForm = this._formBuilder.group({
      emailCtrl: ['', Validators.email]
    });

    this.invitados = [];
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

  ////////////////////////////////////////
  ///////// Creacion de Eventos //////////
  getNombre() { return this.nombreForm.value.nombreCtrl; }
  getDescripcion() { return this.descripcionForm.value.descripcionCtrl; }
  getFecha() { return this.fechaForm.value.fechaCtrl; }
  
  invitar() {
    let invitado = this.emailForm.value.emailCtrl; //agarra el dato
    this.invitados.push(invitado);  //agrega a una lista

    this.emailForm.get('emailCtrl').setValue(''); //lo blanquea
    this.emailForm.get('emailCtrl').markAsPristine(); //limpiamos las validaciones
    this.emailForm.get('emailCtrl').markAsUntouched(); // limpiamos las validaciones
  }

  removerInvitado(inv) {
    const index = this.invitados.indexOf(inv, 0);
    if (index > -1) {
      this.invitados.splice(index, 1);
    }
  }

  cancelarCreacion(stepper) {
    this.invitados = [];

    stepper.reset();
  }

  emailInvalido() {
    return this.emailForm.get('emailCtrl').errors || this.emailForm.get('emailCtrl').pristine;
  }

  creacionValida() {
    return !this.nombreForm.invalid && !this.descripcionForm.invalid
      && !this.fechaForm.invalid && (this.invitados.length > 0);
  }

  crearEvento(stepper){
    var evento = {
      nuevoTemplate: {
        template: {
          nombre: this.getNombre(),
          descripcion: this.getDescripcion()
        },
        fechaLimite: this.getFecha(),
        tipoModalidad: this.modalidadSeleccionada,
        items: this.agregarItemsComponent.itemsParaUsar
      },
      invitados: this.invitados
    }

    this.eventoService.crearEvento(evento)
      .subscribe(res => {
        this.uService.notificacion("Se creó correctamente el evento!", "");
        stepper.reset();
        this.recargar();
      });
  }

}
