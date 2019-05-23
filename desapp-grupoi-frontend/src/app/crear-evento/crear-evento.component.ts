import { Component, OnInit } from '@angular/core';
import {FormControl, Validators} from '@angular/forms';

export interface Combo {
  nombre: string;
  desc: string;
}

@Component({
  selector: 'app-crear-evento',
  templateUrl: './crear-evento.component.html',
  styleUrls: ['./crear-evento.component.scss']
})


export class CrearEventoComponent implements OnInit {

  modalidadControl = new FormControl('', [Validators.required]);
  selectFormControl = new FormControl('', Validators.required);

  modalidades: Combo[] = [
    {nombre: 'Fiesta', desc: 'en ua fiesta pasa bla bla'},
    {nombre: 'Vaquita C', desc: 'assaas!'},
    {nombre: 'Baquita R', desc: '123446'},
    {nombre: 'Canasta', desc: 'Wa-pa-pa-pa-pa-pa-pow!'},
  ];


  constructor() { }

  ngOnInit() {
  }

}
