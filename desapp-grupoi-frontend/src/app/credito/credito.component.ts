import { Cuenta } from './../model/cuenta.model';
import { ErrorResponse } from './../model/error-response.model';
import { CuentaService } from './../services/cuenta.service';
import { Credito } from './../model/credito.model';
import { Component, OnInit, Input, ViewChild, EventEmitter, Output } from '@angular/core';
import { MatPaginator, MatTableDataSource } from '@angular/material';

@Component({
  selector: 'app-credito',
  templateUrl: './credito.component.html',
  styleUrls: ['./credito.component.scss']
})
export class CreditoComponent implements OnInit {

  monto: number;
  cuotas: number;
  totalCuotas: number;
  restante: number;
  montoCuota: number;
  estado: string;

  creditos: Credito[];
  hayCreditoEnCurso: Boolean;
  esCumplidor: Boolean;

  mostrarErrorUltimoCredito: Boolean;
  messageErrorUltimoCredito: string;

  mostrarErrorSituacion: Boolean;
  messageErrorEstadoCuenta: string;

  @ViewChild (MatPaginator) paginator: MatPaginator;
  displayedColumns: string[] = ['estado','monto.monto', 'cuotas', 'montoRestante.monto'];
  dataSource: MatTableDataSource<Credito>;

  // Notificacion para poder actualizar el estado de cuenta.
  @Output() notifyActualizarEstadoCuenta: EventEmitter<boolean> = new EventEmitter<boolean>();

  constructor(private cuentaService: CuentaService) { 
  }

  ngOnInit() {
    this.hayCreditoEnCurso = false;
    this.esCumplidor = false;

    this.mostrarErrorUltimoCredito = false;
    this.mostrarErrorSituacion = false;

    this.actualizar();
    this.getCreditosFinalizados();    
  }

  getCreditosFinalizados(){
    this.cuentaService.getCreditosFinalizados()
      .subscribe(res => {
        this.creditos = res.result as Credito[];

        //Table
        this.dataSource = new MatTableDataSource<Credito>(this.creditos);
        this.dataSource.paginator = this.paginator;
      });
  }

  pedirCredito(){
    this.cuentaService.putMovimientoCredito()
      .subscribe(
        res => {
          this.actualizar();
        }
      );
  }

  
  pagarCuota(){
    this.cuentaService.putMovimientoPagarCuota()
      .subscribe(res => {        
        this.actualizar();
      });
  }
  
  actualizar() {
    this.getUltimoCreditoEnCurso();

    this.cuentaService.getCuenta()
      .subscribe(res => {
        var cuenta = res.result as Cuenta;

        this.esCumplidor = (cuenta.situacionDeuda == 'CUMPLIDOR');
        this.mostrarErrorSituacion = (cuenta.situacionDeuda == 'MOROSO');
      });
      
    // Notificamos para actualizar el estado de cuenta
    this.notifyActualizarEstadoCuenta.emit(true);
  }

  getUltimoCreditoEnCurso(){
    this.cuentaService.getUltimoCreditoEnCurso()
      .subscribe(
        res => {
          var result = res.result as Credito[];
          if(result.length == 0){
            this.hayCreditoEnCurso = false;
            this.mostrarErrorUltimoCredito = true;
            return;
          }
          var credito = result[0];
          this.monto = credito.monto.monto;
          this.cuotas = credito.cuotas - credito.cuotasRestantes;
          this.totalCuotas = credito.cuotas;
          this.restante = credito.montoRestante.monto;
          this.montoCuota = credito.montoCuota.monto;
          this.estado = credito.estado;

          this.hayCreditoEnCurso = true;
          this.mostrarErrorUltimoCredito = false;
        },
        error => {
          var e = error as ErrorResponse;
          console.log(e.error.message);        
        });
  }

}
