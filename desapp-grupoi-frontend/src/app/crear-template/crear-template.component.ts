import { EventoService } from './../services/evento.service';
import { AgregarItemsComponent } from './../agregar-items/agregar-items.component';
import { Component, OnInit, ViewChild } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { UtilsService } from '../services/utils.service';

@Component({
  selector: 'app-crear-template',
  templateUrl: './crear-template.component.html',
  styleUrls: ['./crear-template.component.scss']
})
export class CrearTemplateComponent implements OnInit {

  // Pasos
  nombreFormGroup: FormGroup;
  descripcionFormGroup: FormGroup;
  fechaFormGroup: FormGroup;
  modalidadFormGroup: FormGroup;
  visibilidad: boolean;

  modalidades: any[];

  estaTodoListo: boolean;

  @ViewChild(AgregarItemsComponent) agregarItemsComponent: AgregarItemsComponent;

  constructor(
    private _formBuilder: FormBuilder,
    private eventoService: EventoService) { }

  ngOnInit() {
    
    this.modalidades  = [
      {value: 'FIESTA', viewValue: 'Fiesta'},
      {value: 'CANASTA', viewValue: 'Canasta'},
      {value: 'BAQUITA_COMPRA', viewValue: 'Baquita v1'},
      {value: 'BAQUITA_RECOLECCION', viewValue: 'Baquita v2'},
    ];

    this.nombreFormGroup = this._formBuilder.group({
      nombreCtrl: ['', Validators.required]
    });
    this.descripcionFormGroup = this._formBuilder.group({
      descripcionCtrl: ['', Validators.required]
    });
    this.fechaFormGroup = this._formBuilder.group({
      fechaCtrl: ['', Validators.required]
    });
    this.modalidadFormGroup = this._formBuilder.group({
      modalidadCtrl: ['', Validators.required]
    });

    this.visibilidad = false;
  }

  getNombre(){ return this.nombreFormGroup.value.nombreCtrl; }
  getDescripcion(){ return this.descripcionFormGroup.value.descripcionCtrl; }
  getFecha(){ return this.fechaFormGroup.value.fechaCtrl; }
  getModalidad(){ return this.modalidadFormGroup.value.modalidadCtrl; }

  crearTemplate(){

    var template = {
      template: {
        nombre: this.getNombre(),
        descripcion: this.getDescripcion()
      },
      fechaLimite: this.getFecha(),
      tipoModalidad: this.getModalidad(),
      items: this.agregarItemsComponent.itemsParaUsar
    }

    this.eventoService.crearTemplate(template)
      .subscribe(res => {
        console.log("Se guardo correctamente el template!");
        
      });

  }

  creacionValida(){
    return !this.nombreFormGroup.invalid && !this.descripcionFormGroup.invalid 
    && !this.fechaFormGroup.invalid && !this.modalidadFormGroup.invalid; //&& this.estaTodoListo;
  }

  resetForm(){
    this.estaTodoListo = false;
  }

}
