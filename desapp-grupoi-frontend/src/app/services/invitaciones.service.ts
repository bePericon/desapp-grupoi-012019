import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { StorageService } from './storage.service';
import { Session } from '../model/session.model';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class InvitacionesService {

  invitaciones = [];
  usuarioId: number;

  readonly URL_API = 'http://localhost:8080/app/invitacion';

  constructor(
    private http: HttpClient, 
    private storageService: StorageService) {

    this.usuarioId = this.storageService.getCurrentUser().id;
  }

  getPendientes(): Observable<Session> {
    return this.http.get<Session>(this.URL_API+ '/pendiente/'+ this.usuarioId);
  };

  getAceptadas(): Observable<Session> {
    return this.http.get<Session>(this.URL_API+ '/aceptada/'+ this.usuarioId);
  };

  getRechazadas(){
    return this.http.get<Session>(this.URL_API+ '/rechazada/'+ this.usuarioId);
  };
  
  getPasadas(): Observable<Session> {
    return this.http.get<Session>(this.URL_API+ '/pasada/'+ this.usuarioId);
  };

}
