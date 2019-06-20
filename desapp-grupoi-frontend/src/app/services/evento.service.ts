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

  // TEMPLATES
  //http://localhost:8080/app/evento/template/idUsuario --post
  crearTemplate(template): Observable<Session> {
    return this.http.post<Session>(`${this.URL_API}/template/${this.usuarioId}`, template);
  }

  //http://localhost:8080/app/evento/template/idTemplate --get
  //http://localhost:8080/app/evento/template/usuario/idUsuario --get



  getTemplatesPublicos(): Observable<Session>{

    return this.http.get<Session>(this.URL_API+ '/template/publico'); 
  };

  getTemplatesPrivados(): Observable<Session>{
    // /template/usuario/{id}
    return this.http.get<Session>(`${this.URL_API}/template/usuario/${this.usuarioId}`); 
  };

}
