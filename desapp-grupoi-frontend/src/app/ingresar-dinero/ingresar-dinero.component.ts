import { ErrorResponse } from './../model/error-response.model';
import { Cuenta } from './../model/cuenta.model';
import { CuentaService } from './../services/cuenta.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';

@Component({
  selector: 'app-ingresar-dinero',
  templateUrl: './ingresar-dinero.component.html',
  styleUrls: ['./ingresar-dinero.component.scss']
})
export class IngresarDineroComponent implements OnInit {

  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;

  saldo: number;
  mostrar: Boolean = true;
  
  mostrarIngresar: Boolean = false;
  mostrarRetirar: Boolean = false;

  mostrarSuccess: Boolean = false;
  mostrarFailure: Boolean = false;

  textoCardTitulo: string;
  textoCard: string;

  // Notificacion para poder actualizar el estado de cuenta.
  @Output() notifyActualizarEstadoCuenta: EventEmitter<boolean> = new EventEmitter<boolean>();
  @Output() notifyActualizarCreditos: EventEmitter<boolean> = new EventEmitter<boolean>();

  constructor(
    private fb: FormBuilder,
    private cuentaService: CuentaService) {

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
    this.loadCuenta();
  }

  private loadCuenta() {
    this.cuentaService.getCuenta()
      .subscribe(res => {
        this.saldo = (res.result as Cuenta).saldo.monto;
      });
  }

  getMonto(){ return  this.firstFormGroup.controls['montoCtrl'].value;}
  getCodigo(){ return  this.secondFormGroup.controls['codigoCtrl'].value;}

  confirmar(nombreAccion){
    this.ingresarMas();

    switch (nombreAccion) {
      case 'ingreso':
        this.ingresarDinero();
        break;
      default:
        this.retirarDinero();
        break;
    }
    
  }

  private retirarDinero() {
    this.cuentaService.putMovimientoRetirar(this.getMonto(), this.getCodigo())
      .subscribe(res => {
        this.success('retiro');
      },error => {
        this.failure(error);
      });
  }

  
  private ingresarDinero() {
    this.cuentaService.putMovimientoIngresar(this.getMonto(), this.getCodigo())
    .subscribe(res => {
      this.success('ingreso');
      this.actualizar();
    },error => {
      var e = error as ErrorResponse;
      this.failure(e);
    });
  }
  
  actualizar() {
    this.loadCuenta();
    
    // Notificamos para actualizar el estado de cuenta
    this.notifyActualizarEstadoCuenta.emit(true);
    // Notificamos para actualizar los creditos
    this.notifyActualizarCreditos.emit(true);
  }

  private failure(error: any) {
    this.textoCardTitulo = "Que mal!";
    this.textoCard = error.message;
    this.switchIcon('failure');
  }

  private success(accion) {
    this.textoCardTitulo = "Perfecto!";
    this.textoCard = `El ${accion} fue correcto!`;
    this.switchIcon('success');
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

    this.mostrar = true;
  }

  switchIcon(icon){
    if(icon == 'success'){
      this.mostrarFailure = false;
      this.mostrarSuccess = true;
    }

    if(icon == 'failure'){
      this.mostrarFailure = true;
      this.mostrarSuccess = false;
    }
  }

}