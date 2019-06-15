import { CuentaService } from './../services/cuenta.service';
import { Cuenta } from './../model/cuenta.model';
import { Movimiento } from './../model/movimiento.model';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatPaginator, MatDialog } from '@angular/material';
import { EditTarjetaComponent } from './edit-tarjeta/edit-tarjeta.component';

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

  constructor(
    private cuentaService: CuentaService,
    public dialog: MatDialog) { }

  ngOnInit() {
    this.loadData();
    this.opcionesExpandidas = false;
  }

  loadData(){
    this.cuentaService.getCuenta()
      .subscribe(res => {
        this.cargarCuenta(res);
      });
  }

  cargarCuenta(res){
    this.cuenta = res.result as Cuenta;
    this.saldo = this.cuenta.saldo.monto;
    this.situacion = this.cuenta.situacionDeuda;
    this.movimientos = this.cuenta.movimientos;

    //Table
    this.dataSource = new MatTableDataSource<Movimiento>(this.movimientos);
    this.dataSource.paginator = this.paginator;

    this.tarjetaCredito = '4242 4242 4242 4242';
  }

  // expandirOpciones(){
  //   this.opcionesExpandidas = !this.opcionesExpandidas;
  // }

  editarTarjeta(){
    const dialogRef = this.dialog.open(EditTarjetaComponent, {
      width: '300px',
      data: {numero: this.tarjetaCredito},
      disableClose: true
    });

    dialogRef.afterClosed().subscribe(result => {
      (result)? this.tarjetaCredito = result : undefined;
    });
  }

}
