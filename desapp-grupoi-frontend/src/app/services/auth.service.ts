import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuario } from '../model/usuario.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

   // Variables
  // URL of th Rest API server
  readonly URL_API = 'http://localhost:8080/app';

  // Instancied contact with Rest API.
  constructor(private httpClient: HttpClient) { }

  // METHODS
  // Register a user
  registerUser(usuario: Usuario): Observable<Usuario> {    
    return this.httpClient.post<Usuario>(`${this.URL_API}/usuario`, usuario);
  }

  // Sing in a user
  // singIn(user: User): Observable<Session> {
  //   return this.httpClient.post<Session>(`${this.URL_API}/login`, user);
  // }

}
