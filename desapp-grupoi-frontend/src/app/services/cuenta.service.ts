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
  putTarjeta(tarjeta: Tarjeta){
    return this.httpClient.put(this.URL_API + `/tarjeta/${this.usuarioId}`, tarjeta);
  };

  putMovimientoIngresar(monto: number, codigo: number){
    // En la vida real hay que pegarle a un servicio de acreditacion a cuentas bancarias
    return this.httpClient.put(
      this.URL_API+ '/movimiento/deposito/'+ this.usuarioId, 
      { codigoSeguridad: codigo, monto: { monto: monto } }
    );
  }

  putMovimientoRetirar(monto: number, codigo: number){
    // En la vida real hay que pegarle a un servicio de acreditacion a cuentas bancarias
    return this.httpClient.put(
      this.URL_API+ '/movimiento/retiro/'+ this.usuarioId, 
      { codigoSeguridad: codigo, monto: { monto: monto } }
    );
  }

}
