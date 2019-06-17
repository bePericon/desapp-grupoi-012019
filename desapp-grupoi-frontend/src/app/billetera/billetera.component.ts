import { EstadoCuentaComponent } from './../estado-cuenta/estado-cuenta.component';
import { Component, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-billetera',
  templateUrl: './billetera.component.html',
  styleUrls: ['./billetera.component.scss']
})
export class BilleteraComponent implements OnInit {

  @ViewChild(EstadoCuentaComponent) estadoCuentaComp: EstadoCuentaComponent;

  constructor() { }

  ngOnInit() {
  }

  onNotifyActualizarEstadoCuenta(notifyActualizarEstadoCuenta: boolean){
    if(notifyActualizarEstadoCuenta){ this.estadoCuentaComp.loadData(); }
  }

}
