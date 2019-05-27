import { Component, OnInit } from '@angular/core';
import { FormControl, FormBuilder, FormGroup, FormGroupDirective, NgForm, Validators } from '@angular/forms';

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

  constructor(private _formBuilder: FormBuilder) { 
  }

  ngOnInit() {
    this.infoFiesta = "Descripcion de fiestas";
    this.infoCanasta = "Descripcion de canasta";
    this.infoBaquitav1 = "Descripcion de baquita";
    this.infoBaquitav2 = "Descripcion de baquitav2";
  
    this.modalidad = "";
    this.modalidadControl = new FormControl('', [Validators.required]);

    this.nombreForm = this._formBuilder.group({
      nombreCtrl: ['', Validators.required]
    });
    this.descripcionForm = this._formBuilder.group({
      descripcionCtrl: ['', Validators.required]
    });
  
    this.fechaForm = this._formBuilder.group({
      fechaCtrl: ['', Validators.required]
    });
  }
  
  modalidadControl:FormControl;
  nombreForm: FormGroup;
  descripcionForm: FormGroup;
  fechaForm: FormGroup;

  infoFiesta:String;
  infoCanasta:String;
  infoBaquitav1:String;
  infoBaquitav2:String;
  modalidad:String;
 

  elegirModalidad(mod:String){
    this.modalidad=mod;  
  }

}
