import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { StorageService } from './storage.service';
import { Session } from '../model/session.model';
import { Observable } from 'rxjs';
import { Item } from '../model/Item.model';

@Injectable({
  providedIn: 'root'
})

export class ItemService {

  items: Item[];
  usuarioId: number;
  itemSel : Item;
  // URL of th Rest API server
  readonly URL_API = 'http://localhost:8080/app/item';


  constructor(
    private httpClient: HttpClient,
    private storageService: StorageService) {
    this.usuarioId = this.storageService.getCurrentUser().id;
  }


  getItemsDisponibles(): Observable<Session>{
    return this.httpClient.get<Session>(this.URL_API + '/all'); 
  };

  crearItem(item): Observable<Session> {
    return this.httpClient.post<Session>(`${this.URL_API}/`, item);
  }
}
