import { Component, OnInit, ViewChild } from '@angular/core';
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

  // displayedColumns: string[] = ['tipoMovimiento', 'fecha', 'monto.monto'];
  // dataSource: MatTableDataSource<Movimiento>;
  cuenta: Cuenta;
  saldo: number;
  situacion: string;
  tarjetaCredito: Tarjeta;
  movimientos: Movimiento[];

  constructor(private cuentaService: CuentaService) { }

  ngOnInit() {
    this.loadCuenta()
  }


  loadCuenta() {

    let movs: Movimiento[] = [];

    this.cuentaService.getCuentas()
      .subscribe(res => {

        console.log(res)
        for (let entry of res.result) {
          for (let m of entry.movimientos) 
            movs.push(m);
        }
        this.movimientos = movs;
      });


  }

}
