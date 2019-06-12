import { CuentaService } from './../services/cuenta.service';
import { Cuenta } from './../model/cuenta.model';
import { Movimiento } from './../model/movimiento.model';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatPaginator } from '@angular/material';

@Component({
  selector: 'app-estado-cuenta',
  templateUrl: './estado-cuenta.component.html',
  styleUrls: ['./estado-cuenta.component.scss']
})
export class EstadoCuentaComponent implements OnInit {

  cuenta: Cuenta;
  saldo: number;
  situacion: string;
  tarjetaCredito: any;
  movimientos: Movimiento[];
  opcionesExpandidas: Boolean;

  //Table
  @ViewChild (MatPaginator) paginator: MatPaginator;
  displayedColumns: string[] = ['tipoMovimiento', 'fecha', 'monto.monto'];
  dataSource: MatTableDataSource<Movimiento>;

  constructor(private cuentaService: CuentaService) { }

  ngOnInit() {
    this.cargarCuenta();
    this.tarjetaCredito = 'XXXX-XXXX-XXXX-XXXX';
    this.opcionesExpandidas = false;
  }

  cargarCuenta(){
    this.cuentaService.getCuenta()
      .subscribe(res => {
        this.cuenta = res.result as Cuenta;
        this.saldo = this.cuenta.saldo.monto;
        this.situacion = this.cuenta.situacionDeuda;
        this.movimientos = this.cuenta.movimientos;

        //Table
        this.dataSource = new MatTableDataSource<Movimiento>(this.movimientos);
        this.dataSource.paginator = this.paginator;
        
      });
  }

  expandirOpciones(){
    this.opcionesExpandidas = !this.opcionesExpandidas;
  }

}
