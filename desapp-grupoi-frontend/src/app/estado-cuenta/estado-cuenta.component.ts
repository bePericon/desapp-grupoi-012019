import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-estado-cuenta',
  templateUrl: './estado-cuenta.component.html',
  styleUrls: ['./estado-cuenta.component.scss']
})
export class EstadoCuentaComponent implements OnInit {

  saldo: any;
  situacion: any;
  tarjetaCredito: any;

  constructor() { }

  ngOnInit() {
    this.saldo = 1000;
    this.situacion = 'NORMAL';
    this.tarjetaCredito = 'XXXX-XXXX-XXXX-XXXX';
  }

}
