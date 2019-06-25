import { CuentaService } from './../services/cuenta.service';
import { CreditoComponent } from './../credito/credito.component';
import { EstadoCuentaComponent } from './../estado-cuenta/estado-cuenta.component';
import { Component, OnInit, ViewChild } from '@angular/core';
import { IngresarDineroComponent } from '../ingresar-dinero/ingresar-dinero.component';
import { Cuenta } from '../model/cuenta.model';

@Component({
  selector: 'app-billetera',
  templateUrl: './billetera.component.html',
  styleUrls: ['./billetera.component.scss']
})
export class BilleteraComponent implements OnInit {

  @ViewChild(EstadoCuentaComponent) estadoCuentaComp: EstadoCuentaComponent;
  // @ViewChild(IngresarDineroComponent) ingresarDineroComp: IngresarDineroComponent;
  @ViewChild(CreditoComponent) creditoComponent: CreditoComponent;

  constructor() {
  }

  ngOnInit() {
  }

  onNotifyActualizarEstadoCuenta(notifyActualizarEstadoCuenta: boolean){
    if(notifyActualizarEstadoCuenta){ this.estadoCuentaComp.loadCuenta(); }
  }

  onNotifyActualizarCreditos(notifyActualizarEstadoCuenta: boolean){
    if(notifyActualizarEstadoCuenta){ this.creditoComponent.actualizar(); }
  }

}
