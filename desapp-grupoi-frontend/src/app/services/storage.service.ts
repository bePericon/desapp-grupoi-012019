import { Injectable } from '@angular/core';
import { Usuario } from '../model/usuario.model';
import { Session } from '../model/session.model';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  private localStorageService;
  private currentSession: Session = null;

  constructor(private router: Router) {
    this.localStorageService = localStorage;
    this.currentSession = this.loadSessionData();
  }

  // METHODS
  /**
   * Set the currentSession and the currentUser in localStorage.
   */
  setCurrentSession(session: Session): void {
    this.currentSession = session;
    this.localStorageService.setItem('currentUser', JSON.stringify(session));
  }

  /**
   * Returns an object session, this contains the currentUser from the localStorage.
   */
  loadSessionData(): Session {
    const sessionStr = this.localStorageService.getItem('currentUser');
    return (sessionStr) ? <Session> JSON.parse(sessionStr) : null;
  }

  /**
   * Returns the current session.
   */
  getCurrentSession(): Session {
    return this.currentSession;
  }

  /**
   * Remove the current user from localStorage and set null currentSession.
   */
  removeCurrentSession(): void {
    this.localStorageService.removeItem('currentUser');
    this.currentSession = null;
  }

  /**
   * Returns the user from currentSession.
   */
  getCurrentUser(): Usuario {
    const session: Session = this.getCurrentSession();
    return (session && session.result) ? session.result : null;
  }

  /**
   * Returns true if the currentToken not is null.
   */
  isAuthenticated(): boolean {
    return (this.getCurrentToken() != null) ? true : false;
  }

  /**
   * Returns the token from the currentSession.
   */
  getCurrentToken(): any {
    const session = this.getCurrentSession();
    return (session && session.token) ? session.token : null;
  }

  /**
   * Remove the current session and redirect for route login.
   */
  singOut(): void {
    this.removeCurrentSession();
    this.router.navigateByUrl('/auth/login');
  }

}
