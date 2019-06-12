import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-crear-template',
  templateUrl: './crear-template.component.html',
  styleUrls: ['./crear-template.component.scss']
})
export class CrearTemplateComponent implements OnInit {

  nombreFormGroup: FormGroup;
  descripcionFormGroup: FormGroup;
  fechaEventoForm: FormGroup;
  modalidadFormGroup: FormGroup;
  // descripcionFormGroup: FormGroup;


  modalidades: any;

  constructor(private _formBuilder: FormBuilder) {
    
  }

  ngOnInit() {
    
    this.modalidades  = [
      {value: 'FIESTA', viewValue: 'Fiesta'},
      {value: 'CANASTA', viewValue: 'Canasta'},
      {value: 'BAQIUTA-V1', viewValue: 'Baqiuta v1'},
      {value: 'BAQUITA-V2', viewValue: 'Baquita v2'},
    ];

    this.nombreFormGroup = this._formBuilder.group({
      nombreCtrl: ['', Validators.required]
    });
    this.descripcionFormGroup = this._formBuilder.group({
      descripcionCtrl: ['', Validators.required]
    });
    this.fechaEventoForm = this._formBuilder.group({
      fechaEventoCtrl: ['', Validators.required]
    });
    this.modalidadFormGroup = this._formBuilder.group({
      modalidadCtrl: ['', Validators.required]
    });
    // this.secondFormGroup = this._formBuilder.group({
    //   secondCtrl: ['', Validators.required]
    // });

  }

}
