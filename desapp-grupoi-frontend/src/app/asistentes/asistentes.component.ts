import { Usuario } from '../model/usuario.model';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'app-asistentes',
  templateUrl: './asistentes.component.html',
  styleUrls: ['./asistentes.component.scss']
})
export class AsistentesComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<AsistentesComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Usuario[]) { }

  ngOnInit() {
  }

}