import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Evento } from '../model/evento.model';

@Injectable({
  providedIn: 'root'
})
export class EventoService {

  // Variables
  selectedEvento : Evento;
  eventos: Evento[];
  // URL of th Rest API server
  readonly URL_API = 'http://localhost:8080/app/evento';


  constructor(private http: HttpClient) {
    this.selectedEvento = new Evento();
  }

  //SE HARDCODEARON LOS ID DE USUARIOS!!
  // Methods
  getEventosPopulares(){
    return this.http.get(this.URL_API+ '/populares/all'); 
  };

  getEventosPasados(){
    return this.http.get(this.URL_API+ '/pasados/usuario/2');
  };

  getEventosInvitaronEnCurso(){
    return this.http.get(this.URL_API+ '/encurso/usuario/2'); 
  };

  getEventos(){
    return this.http.get(this.URL_API+ '/cuenta/2'); 
  };

  postEvento(Evento: Evento){
    
  };

  putEvento(Evento: Evento){
    
  };

  deleteEvento(_id: string){
    
  };

}
