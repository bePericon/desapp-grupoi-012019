import { AgregarItemsComponent } from './../agregar-items/agregar-items.component';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormBuilder, FormGroup, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';

export interface Combo {
  nombre: string;
  desc: string;
}

@Component({
  selector: 'app-crear-evento',
  templateUrl: './crear-evento.component.html',
  styleUrls: ['./crear-evento.component.scss']
})

export class CrearEventoComponent implements OnInit {

  modalidadControl: FormControl;
  nombreForm: FormGroup;
  descripcionForm: FormGroup;
  fechaForm: FormGroup;
  itemForm: FormGroup;
  emailForm: FormGroup;

  infoFiesta: String;
  infoCanasta: String;
  infoBaquitav1: String;
  infoBaquitav2: String;
  modalidad: String;

  panelOpenState = false;
  // invitado:string;
  invitados;

  @ViewChild(AgregarItemsComponent) agregarItemsComponent: AgregarItemsComponent;

  constructor(private _formBuilder: FormBuilder) {
    this.invitados = [];
  }

  ngOnInit() {
    this.infoFiesta = "Los gastos corren por cuenta del organizador.";
    this.infoCanasta = "se presenta la lista de gastos a realizar y cada asistente elige un ítem del cual hacerse cargo.";
    this.infoBaquitav1 = "Una o más personas realizan las compras y luego se divide con los demás asistentes.";
    this.infoBaquitav2 = "Crea una cuenta común a la cual deberán girar su parte los asistentes y así el organizador dispone de fondos para las compras.";

    this.modalidad = "";

    // this.modalidadControl = new FormControl('', [Validators.required]);

    //formgroup
    this.nombreForm = this._formBuilder.group({
      nombreCtrl: ['', Validators.required] //formControl
    });
    this.descripcionForm = this._formBuilder.group({
      descripcionCtrl: ['', Validators.required]
    });

    this.fechaForm = this._formBuilder.group({
      fechaCtrl: ['', Validators.required]
    });

    this.emailForm = this._formBuilder.group({
      emailCtrl: ['', [Validators.email]]
    });
  }

  invitar() {
    //los forms en angular ahora son reactivos, asi que tuve que hacer un workarround para manipular la data
    let invitado = this.emailForm.value.emailCtrl; //agarra el dato
    this.invitados.push(invitado);  //agrega a una lista

    this.emailForm.get('emailCtrl').setValue(''); //lo blanquea
    this.emailForm.get('emailCtrl').markAsPristine(); //limpiamos las validaciones
    this.emailForm.get('emailCtrl').markAsUntouched(); // limpiamos las validaciones
  }

  elegirModalidad(mod: String) {
    this.modalidad = mod;
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
    this.modalidad = null;
  }

  //agregar validacion sobre mails que ya existen?
  emailInvalido() {
    return this.emailForm.get('emailCtrl').errors || this.emailForm.get('emailCtrl').pristine
  }

}
