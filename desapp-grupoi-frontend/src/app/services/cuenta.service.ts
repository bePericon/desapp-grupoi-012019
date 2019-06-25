import { Cuenta } from './../model/cuenta.model';
import { Tarjeta } from './../model/tarjeta.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Session } from '../model/session.model';
import { StorageService } from './storage.service';

@Injectable({
  providedIn: 'root'
})
export class CuentaService {

  usuarioId: number;
  cuentaActual: Cuenta;

  readonly URL_API = 'http://localhost:8080/app/cuenta';

  constructor(
    private httpClient: HttpClient,
    private storageService: StorageService) {
      this.usuarioId = this.storageService.getCurrentUser().id;
  }

  // METHODS
  // Obtener Cuenta del usuario actual
  getCuenta(): Observable<Session> {
    return this.httpClient.get<Session>(this.URL_API+ '/usuario/'+ this.usuarioId);
  }

  // Guardamos la tarjeta de credito
  putTarjeta(tarjeta: Tarjeta): Observable<Session> {
    return this.httpClient.put<Session>(this.URL_API + `/tarjeta/${this.usuarioId}`, tarjeta);
  };

  putMovimientoIngresar(monto: number, codigo: number): Observable<Session> {
    // En la vida real hay que pegarle a un servicio de acreditacion a cuentas bancarias
    return this.httpClient.put<Session>(
      this.URL_API+ '/movimiento/deposito/'+ this.usuarioId, 
      { codigoSeguridad: codigo, monto: { monto: monto } }
    );
  }

  putMovimientoRetirar(monto: number, codigo: number): Observable<Session> {
    // En la vida real hay que pegarle a un servicio de acreditacion a cuentas bancarias
    return this.httpClient.put<Session>(
      this.URL_API+ '/movimiento/retiro/'+ this.usuarioId, 
      { codigoSeguridad: codigo, monto: { monto: monto } }
    );
  }

  // Metodo para crear el credito fijo de la app.
  putMovimientoCredito(): Observable<Session> {
    return this.httpClient.put<Session>(`${this.URL_API}/movimiento/credito/${this.usuarioId}`, {});
  }

  // Trae todos lo creditos del usuario, el historico.
  getCreditos(): Observable<Session> {
    return this.httpClient.get<Session>(this.URL_API+ '/credito/'+ this.usuarioId);
  }

  // Trae todos lo creditos del usuario finalizados.
  getCreditosFinalizados(): Observable<Session> {
    return this.httpClient.get<Session>(this.URL_API+ '/credito/finalizado/'+ this.usuarioId);
  }

  // Obtener ultimo credito en curso, si no hay devuelve error con message.
  getUltimoCreditoEnCurso(): Observable<Session> {
    return this.httpClient.get<Session>(this.URL_API+ '/credito/ultimo/'+ this.usuarioId);
  }

  // Pagar cuota de Credito.
  // TODO: hacer proceso en backend.
  putMovimientoPagarCuota(): Observable<Session> {
    return this.httpClient.put<Session>(`${this.URL_API}/movimiento/pagarcuota/${this.usuarioId}`, {});
  }

}
