import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { StorageService } from './storage.service';
import { Session } from '../model/session.model';
import { Observable } from 'rxjs';
import { Invitacion } from '../model/invitacion.model';
@Injectable({
  providedIn: 'root'
})
export class InvitacionesService {

  readonly URL_API = 'http://localhost:8080/app/invitacion';

  constructor(
    private http: HttpClient, 
    private storageService: StorageService) {
  }

  getIdUsuario(): number{
    return this.storageService.getCurrentUser().id;
  }

  getPendientes(): Observable<Session> {
    return this.http.get<Session>(this.URL_API+ '/pendiente/'+ this.getIdUsuario());
  };

  getAceptadas(): Observable<Session> {
    return this.http.get<Session>(this.URL_API+ '/aceptada/'+ this.getIdUsuario());
  };

  getRechazadas(){
    return this.http.get<Session>(this.URL_API+ '/rechazada/'+ this.getIdUsuario());
  };
  
  getPasadas(): Observable<Session> {
    return this.http.get<Session>(this.URL_API+ '/pasada/'+ this.getIdUsuario());
  };

  confirmarInvitacion(inv: Invitacion): Observable<Session> {
    return this.http.put<Session>(
      `${this.URL_API}/confirmar`, { idInvitacion: inv.id, idUsuario: this.getIdUsuario() }
    );
  }

  rechazarInvitacion(inv: Invitacion): Observable<Session> {
    return this.http.put<Session>(
      `${this.URL_API}/rechazar`, { idInvitacion: inv.id, idUsuario: this.getIdUsuario() }
    );
  }

}
