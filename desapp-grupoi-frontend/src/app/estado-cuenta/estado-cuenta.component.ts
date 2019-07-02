import { Tarjeta } from 'src/app/model/tarjeta.model';
import { CuentaService } from './../services/cuenta.service';
import { Cuenta } from './../model/cuenta.model';
import { Movimiento } from './../model/movimiento.model';
import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { MatTableDataSource, MatPaginator, MatDialog } from '@angular/material';
import { EditTarjetaComponent } from '../edit-tarjeta/edit-tarjeta.component';

@Component({
  selector: 'app-estado-cuenta',
  templateUrl: './estado-cuenta.component.html',
  styleUrls: ['./estado-cuenta.component.scss']
})
export class EstadoCuentaComponent implements OnInit {

  cuenta: Cuenta;
  saldo: number;
  situacion: string;
  tarjetaCredito: Tarjeta;
  movimientos: Movimiento[];

  infoSinTarjeta: string = "N/A";

  //Table
  @ViewChild (MatPaginator) paginator: MatPaginator; //cambiar esto no hace falta
  displayedColumns: string[] = ['tipoMovimiento', 'fecha', 'monto.monto'];
  dataSource: MatTableDataSource<Movimiento>;

  constructor(
    private cuentaService: CuentaService,
    public dialog: MatDialog) { }

  ngOnInit() {

    //crear inicializacion para el update
    this.tarjetaCredito = new Tarjeta("",0);

    this.loadCuenta();
  }

  loadCuenta(){
    this.cuentaService.getCuenta()
      .subscribe(res => {
        this.cuenta = res.result as Cuenta;
        this.saldo = this.cuenta.saldo.monto;
        this.situacion = this.cuenta.situacionDeuda;
        this.movimientos = this.cuenta.movimientos;
        this.tarjetaCredito = (this.cuenta.tarjetaCredito)? this.cuenta.tarjetaCredito : new Tarjeta("",0);
      
        //Table
        this.dataSource = new MatTableDataSource<Movimiento>(this.movimientos);
        this.dataSource.paginator = this.paginator;
      });
  }

  editarTarjeta(){
    const dialogRef = this.dialog.open(EditTarjetaComponent, {
      width: '250px',
      data: new Tarjeta(this.tarjetaCredito.numeroTarjeta, this.tarjetaCredito.codigoSeguridad),
      disableClose: true
    });

    dialogRef.afterClosed().subscribe(result => {
      (result)? this.saveTarjeta(result) : undefined;
    });
  }

  saveTarjeta(tarjeta){
    this.tarjetaCredito = tarjeta;

    this.cuentaService.putTarjeta(tarjeta)
      .subscribe(res => {
        console.log("Se actualizo la tarjeta! ");
      });
  }

  sinTarjeta(){
    return this.tarjetaCredito.numeroTarjeta == "";
  }
}
