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

}
