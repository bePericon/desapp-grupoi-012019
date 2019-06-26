import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { MatTableDataSource, MatPaginator } from '@angular/material';
import { Movimiento } from '../model/movimiento.model';
import { CuentaService } from '../services/cuenta.service';
import { Cuenta } from '../model/cuenta.model';
import { Tarjeta } from '../model/tarjeta.model';

@Component({
  selector: 'app-administracion',
  templateUrl: './administracion.component.html',
  styleUrls: ['./administracion.component.scss']
})
export class AdministracionComponent implements OnInit {

  displayedColumns: string[] = ['tipoMovimiento', 'fecha', 'monto.monto'];
  dataSource: MatTableDataSource<Movimiento>;
  cuenta: Cuenta;
  saldo: number;
  situacion: string;
  tarjetaCredito: Tarjeta;
  movimientos: Movimiento[];


// @ViewChild (MatPaginator) 
paginator: MatPaginator;

  constructor( private cuentaService: CuentaService) { }

  ngOnInit() {

  }


  loadCuenta(){
    this.cuentaService.getCuenta()
      .subscribe(res => {
        this.cuenta = res.result as Cuenta;
        this.saldo = this.cuenta.saldo.monto;
        this.situacion = this.cuenta.situacionDeuda;
        this.movimientos = this.cuenta.movimientos;
        this.tarjetaCredito = this.cuenta.tarjetaCredito;

        this.dataSource = new MatTableDataSource<Movimiento>(this.movimientos);
        this.dataSource.paginator = this.paginator;
      });
  }

}
