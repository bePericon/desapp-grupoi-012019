import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-agregar-items',
  templateUrl: './agregar-items.component.html',
  styleUrls: ['./agregar-items.component.scss']
})
export class AgregarItemsComponent implements OnInit {

  items:any

  nombreItem : string
  personasPorUnidad: number
  monto: number
  cantidadItems: number

  itemsParaElEvento: any

  constructor() {

    this.items  = [
      {value: 'item1', viewValue: 'coca', rinde:2},
      {value: 'item2', viewValue: 'lala', rinde:4},
      {value: 'item3', viewValue: 'sarasa', rinde:3},
      {value: 'item4', viewValue: 'ema', rinde:1},
    ];

    this.itemsParaElEvento =[]
   }

  ngOnInit() {
  }


  agregarAlListado(){
    //   llamar al servicio, pasarle item
  }

  agregarAlEvento(){
    //construye item con los datos del form
    // y lo agrega a la lista
    this.itemsParaElEvento.push()

  }

  agAlListadoEsInvalido(){
    //   que esten los 2 datos cargados
  }
  agAlEventoEsInvalido(){

    // que esten los 3 datos cargados
    // return this.nombreItem && this.personasPorUnidad && this.monto
  }


}
