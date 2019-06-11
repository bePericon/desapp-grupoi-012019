import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Evento } from '../model/evento.model';
import { StorageService } from './storage.service';
import { Session } from '../model/session.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EventoService {

  // Variables
  usuarioId: number;
  selectedEvento : Evento;
  eventos: Evento[];
  // URL of th Rest API server
  readonly URL_API = 'http://localhost:8080/app/evento';


  constructor(
    private http: HttpClient,
    private storageService: StorageService) {
    this.selectedEvento = new Evento();
    this.usuarioId = this.storageService.getCurrentUser().id;
  }

  //SE HARDCODEARON LOS ID DE USUARIOS!!
  // Methods
  getEventosPopulares(): Observable<Session>{
    return this.http.get<Session>(this.URL_API+ '/populares/all'); 
  };

  getEventosPasados(): Observable<Session>{
    return this.http.get<Session>(this.URL_API+ '/pasados/cuenta/'+ this.usuarioId);
  };

  getEventosInvitaronEnCurso(): Observable<Session>{
    return this.http.get<Session>(this.URL_API+ '/encurso/usuario/'+ this.usuarioId);
  };

  getEventos(): Observable<Session>{
    return this.http.get<Session>(this.URL_API+ '/cuenta/'+ this.usuarioId);
  };

  postEvento(Evento: Evento){
    
  };

  putEvento(Evento: Evento){
    
  };

  deleteEvento(_id: string){
    
  };

}
