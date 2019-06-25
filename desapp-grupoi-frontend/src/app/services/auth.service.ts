import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuario } from '../model/usuario.model';
import { Session } from '../model/session.model';
import * as auth0 from 'auth0-js';
import { Router } from '@angular/router';
import { UtilsService } from './utils.service';

interface AuthConfig {
  clientID: string;
  domain: string;
  callbackURL: string;
}

export const AUTH_CONFIG: AuthConfig = {
  clientID: '4iURo14HZsM6u3ELPGDJTJ3650MwCql5',
  domain: 'dev-kgavav5n.auth0.com',
  callbackURL: 'http://localhost:4200/eventeando'
};

@Injectable({
  providedIn: 'root'
})

export class AuthService {

  private _idToken: string;
  private _accessToken: string;
  private _expiresAt: number;
  userProfile: any;


  auth0 = new auth0.WebAuth({
    clientID: AUTH_CONFIG.clientID,
    domain: AUTH_CONFIG.domain,
    responseType: 'token id_token',
    redirectUri: AUTH_CONFIG.callbackURL,
    scope: 'openid profile' //para obtener info del usuario
  });

  constructor(public router: Router, private httpClient: HttpClient, private utilsSrv: UtilsService) {
    this._idToken = '';
    this._accessToken = '';
    this._expiresAt = 0;
  }

  get accessToken(): string {
    return this._accessToken;
  }

  get idToken(): string {
    return this._idToken;
  }

  public login(): void {
    this.auth0.authorize();
  }

  public handleAuthentication(): void {
    this.auth0.parseHash((err, authResult) => {
      if (authResult && authResult.accessToken && authResult.idToken) {
        this.localLogin(authResult);
        this.router.navigate(['/eventeando']); 
      } else if (err) {
        this.router.navigate(['/home']); 
        console.log(err);
        this.utilsSrv.notificacion("Error al loguear, intente de nuevo","")
      }
    });
  }

  private localLogin(authResult): void {
    // Set the time that the access token will expire at
    const expiresAt = (authResult.expiresIn * 1000) + Date.now();
    this._accessToken = authResult.accessToken;
    this._idToken = authResult.idToken;
    this._expiresAt = expiresAt;
  }

  public renewTokens(): void {
    this.auth0.checkSession({}, (err, authResult) => {
       if (authResult && authResult.accessToken && authResult.idToken) {
         this.localLogin(authResult);
       } else if (err) {
         alert(`Could not get a new token (${err.error}: ${err.error_description}).`);
         this.logout();
       }
    });
  }

  public logout(): void {
    this._accessToken = '';
    this._idToken = '';
    this._expiresAt = 0;

    this.auth0.logout({
      returnTo: window.location.origin
    });
  }

  public isAuthenticated(): boolean {
    return this._accessToken && Date.now() < this._expiresAt;
  }

  //acceder al perfil
  public getProfile(cb): void {
    if (!this._accessToken) {
      throw new Error('Access Token must exist to fetch profile');
    }
  
    const self = this;
    this.auth0.client.userInfo(this._accessToken, (err, profile) => {
      if (profile) {
        self.userProfile = profile;
      }
      cb(err, profile);
    });
  }

//////////////////lo que estaba antes/////////////////////////////////////////

   // Variables
  // URL of th Rest API server
  readonly URL_API = 'http://localhost:8080/app';

  // METHODS
  // Register a user
  registerUser(usuario): Observable<Usuario> {
    return this.httpClient.post<Usuario>(`${this.URL_API}/usuario`, usuario);
  }

  // Sing in a user VAMOS A SACAR NUESTRO LOGIN capaz
  singIn(usuario): Observable<Session> {
    return this.httpClient.post<Session>(`${this.URL_API}/login`, usuario);
  }




}
