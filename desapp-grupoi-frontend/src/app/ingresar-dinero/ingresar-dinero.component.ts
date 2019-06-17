import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-ingresar-dinero',
  templateUrl: './ingresar-dinero.component.html',
  styleUrls: ['./ingresar-dinero.component.scss']
})
export class IngresarDineroComponent implements OnInit {

  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;

  saldo: number = 0;
  mostrar: Boolean = true;
  
  mostrarIngresar: Boolean = false;
  mostrarRetirar: Boolean = false;

  constructor(fb: FormBuilder) {
    this.firstFormGroup = fb.group({
      hideRequired: false,
      floatLabel: 'auto',
      montoCtrl: ['', Validators.required]
    });
    this.secondFormGroup = fb.group({
      hideRequired: false,
      floatLabel: 'auto',
      codigoCtrl: ['', Validators.required]
    });
  }

  ngOnInit() {
  }

  confirmar(){
    this.mostrar = !this.mostrar;
    
    //servicio!
  }

  ingresarMas(){
    this.mostrar = !this.mostrar;
  }

  switchAccion(nombreAccion){
    if(nombreAccion == 'retirar'){
      this.mostrarIngresar = false;
      this.mostrarRetirar = true;
    }

    if(nombreAccion == 'ingresar'){
      this.mostrarIngresar = true;
      this.mostrarRetirar = false;
    }
  }

}