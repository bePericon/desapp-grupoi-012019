import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material';

@Injectable({
  providedIn: 'root'
})
export class UtilsService {

  constructor(
    private _snackBar: MatSnackBar,
    private _dialog: MatDialog) { }


  notificacion(mensaje, accion) {
    this._snackBar.open(mensaje, accion, {
      duration: 3500,
    });
  }

  getDialog(component, data, width){
    return this._dialog.open(component, {
      width: width,
      data: data,
      disableClose: true
    });
  }

}
