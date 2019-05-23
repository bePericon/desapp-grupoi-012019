import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
// import { User } from '../model/user';
// import { Observable } from 'rxjs/Observable';

@Injectable({
  providedIn: 'root'
})
export class EventoService {


  private eventosUrl: string;
 
  constructor(private http: HttpClient) {
    this.eventosUrl = 'http://localhost:8080/app';
  }
 
  getUsuarios(){
    return this.http.get(this.eventosUrl+'/usuario/all');
  }


}
