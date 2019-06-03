import { Movimiento } from './../model/movimiento.model';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatPaginator } from '@angular/material';

@Component({
  selector: 'app-estado-cuenta',
  templateUrl: './estado-cuenta.component.html',
  styleUrls: ['./estado-cuenta.component.scss']
})
export class EstadoCuentaComponent implements OnInit {

  saldo: any;
  situacion: any;
  tarjetaCredito: any;
  movimientos: Movimiento[];
  opcionesExpandidas: Boolean;

  //Table
  @ViewChild (MatPaginator) paginator: MatPaginator;
  displayedColumns: string[] = ['tipo', 'valor'];
  dataSource: MatTableDataSource<Movimiento>;

  constructor() { }

  ngOnInit() {
    this.saldo = 1000;
    this.situacion = 'NORMAL';
    this.tarjetaCredito = 'XXXX-XXXX-XXXX-XXXX';
    this.movimientos = [
      {_id: 0, tipo: 'Deposito', valor: '1000'},
      {_id: 0, tipo: 'Deposito', valor: '1000'},
      {_id: 0, tipo: 'Deposito', valor: '1000'},
      {_id: 0, tipo: 'Deposito', valor: '1000'},
      {_id: 0, tipo: 'Deposito', valor: '1000'},
      {_id: 0, tipo: 'Deposito', valor: '1000'},
      {_id: 0, tipo: 'Deposito', valor: '1000'},
      {_id: 0, tipo: 'Deposito', valor: '1000'},
      {_id: 0, tipo: 'Deposito', valor: '1000'},
      {_id: 0, tipo: 'Deposito', valor: '1000'},
      {_id: 0, tipo: 'Deposito', valor: '1000'},
      {_id: 1, tipo: 'Deposito', valor: '1000'}
    ];
    this.opcionesExpandidas = false;

    //Table
    this.dataSource = new MatTableDataSource<Movimiento>(this.movimientos);
    this.dataSource.paginator = this.paginator;
  }

  expandirOpciones(){
    this.opcionesExpandidas = !this.opcionesExpandidas;
  }

}
