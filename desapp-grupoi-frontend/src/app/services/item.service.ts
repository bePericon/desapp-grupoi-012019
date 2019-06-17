import { Injectable } from '@angular/core';
import { Session } from '../model/session.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  items: any

  constructor() {
    this.items = [ {
        value: 'item1',
        viewValue: 'coca',
        rinde: 2
      }, {
        value: 'item2',
        viewValue: 'lala',
        rinde: 4
      }, {
        value: 'item3',
        viewValue: 'sarasa',
        rinde: 3
      } 
    ];

  }
  getItemsDisponibles(): Observable<Session> {
    return this.items
  }

}
