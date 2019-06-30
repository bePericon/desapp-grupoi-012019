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

  cuentaActual: Cuenta;

  readonly URL_API = 'http://localhost:8080/app/cuenta';

  constructor(
    private httpClient: HttpClient,
    private storageService: StorageService) {
  }

  getIdUsuario(): number{
    return this.storageService.getCurrentUser().id;
  }

  // METHODS
  // Obtener Cuenta del usuario actual
  getCuenta(): Observable<Session> {
    return this.httpClient.get<Session>(this.URL_API+ '/usuario/'+ this.getIdUsuario());
  }

  getCuentas(): Observable<Session> {
    return this.httpClient.get<Session>(this.URL_API+ '/cuentas');
  }

  // Guardamos la tarjeta de credito
  putTarjeta(tarjeta: Tarjeta): Observable<Session> {
    return this.httpClient.put<Session>(this.URL_API + `/tarjeta/${this.getIdUsuario()}`, tarjeta);
  };

  putMovimientoIngresar(monto: number, codigo: number): Observable<Session> {
    // En la vida real hay que pegarle a un servicio de acreditacion a cuentas bancarias
    return this.httpClient.put<Session>(
      this.URL_API+ '/movimiento/deposito/'+ this.getIdUsuario(), 
      { codigoSeguridad: codigo, monto: { monto: monto } }
    );
  }

  putMovimientoRetirar(monto: number, codigo: number): Observable<Session> {
    // En la vida real hay que pegarle a un servicio de acreditacion a cuentas bancarias
    return this.httpClient.put<Session>(
      this.URL_API+ '/movimiento/retiro/'+ this.getIdUsuario(), 
      { codigoSeguridad: codigo, monto: { monto: monto } }
    );
  }

  // Metodo para crear el credito fijo de la app.
  putMovimientoCredito(): Observable<Session> {
    return this.httpClient.put<Session>(`${this.URL_API}/movimiento/credito/${this.getIdUsuario()}`, {});
  }

  // Trae todos lo creditos del usuario, el historico.
  getCreditos(): Observable<Session> {
    return this.httpClient.get<Session>(this.URL_API+ '/credito/'+ this.getIdUsuario());
  }

  // Trae todos lo creditos del usuario finalizados.
  getCreditosFinalizados(): Observable<Session> {
    return this.httpClient.get<Session>(this.URL_API+ '/credito/finalizado/'+ this.getIdUsuario());
  }

  // Obtener ultimo credito en curso, si no hay devuelve error con message.
  getUltimoCreditoEnCurso(): Observable<Session> {
    return this.httpClient.get<Session>(this.URL_API+ '/credito/ultimo/'+ this.getIdUsuario());
  }

  // Pagar cuota de Credito.
  // TODO: hacer proceso en backend.
  putMovimientoPagarCuota(): Observable<Session> {
    return this.httpClient.put<Session>(`${this.URL_API}/movimiento/pagarcuota/${this.getIdUsuario()}`, {});
  }

}
