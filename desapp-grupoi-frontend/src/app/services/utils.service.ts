import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class UtilsService {

  constructor(private _snackBar: MatSnackBar) { }


  notificacion(mensaje, accion) {
    this._snackBar.open(mensaje, accion, {
      duration: 3500,
    });
  }



}
