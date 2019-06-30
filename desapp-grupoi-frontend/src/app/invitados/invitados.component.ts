import { Usuario } from './../model/usuario.model';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'app-invitados',
  templateUrl: './invitados.component.html',
  styleUrls: ['./invitados.component.scss']
})
export class InvitadosComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<InvitadosComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Usuario[]) { }

  ngOnInit() {
  }

}