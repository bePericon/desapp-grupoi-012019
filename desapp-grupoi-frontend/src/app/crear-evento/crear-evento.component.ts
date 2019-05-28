import { Component, OnInit } from '@angular/core';
import { FormControl, FormBuilder, FormGroup, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';

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
  panelOpenState = false;
  // invitado:string;
  invitados;
  
  constructor(private _formBuilder: FormBuilder) { 
    this.invitados=[];
  }

  ngOnInit() {
    this.infoFiesta = "Descripcion de fiestas";
    this.infoCanasta = "Descripcion de canasta";
    this.infoBaquitav1 = "Descripcion de baquita";
    this.infoBaquitav2 = "Descripcion de baquitav2";
  
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
      emailCtrl: ['', [ Validators.email]]
    });
  }
  
  modalidadControl:FormControl;
  nombreForm: FormGroup;
  descripcionForm: FormGroup;
  fechaForm: FormGroup;
  emailForm: FormGroup;

  infoFiesta:String;
  infoCanasta:String;
  infoBaquitav1:String;
  infoBaquitav2:String;
  modalidad:String;
 

invitar(){
  //los forms en angular ahora son reactivos, asi que tuve que hacer un workarround para manipular la data
  let invitado = this.emailForm.value.emailCtrl; //agarra el dato
  this.invitados.push(invitado);  //agrega a una lista
  
  this.emailForm.get('emailCtrl').setValue(''); //lo blanquea
  this.emailForm.get('emailCtrl').markAsPristine(); //limpiamos las validaciones
  this.emailForm.get('emailCtrl').markAsUntouched(); // limpiamos las validaciones
}

  elegirModalidad(mod:String){
    this.modalidad=mod;  
  }

  removerInvitado(inv){
    // recorre lista de invitados, compara y lo saca de la lista
    alert("implementar remover usuario")
  }


  cancelarCreacion(stepper){
    this.invitados = [];

    stepper.reset();
    this.modalidad = null;
  }
}
