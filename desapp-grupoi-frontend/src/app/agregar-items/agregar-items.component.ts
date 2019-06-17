import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ItemService } from '../services/item.service';

@Component({
  selector: 'app-agregar-items',
  templateUrl: './agregar-items.component.html',
  styleUrls: ['./agregar-items.component.scss']
})
export class AgregarItemsComponent implements OnInit {

  nombreItem: string
  personasPorUnidad: number
  monto: number
  cantidadItems: number
  itemModel
  cantidadItemModel: number
  nombreItemNuevo: string
  rendimientoItemNuevo: number
  montoItemNuevo: number

  itemsParaElEvento: any
  items
  constructor(private itService: ItemService) {

    // this.itService.getItemsDisponibles()
    //   .subscribe(res => {
    //     this.items = res.result;
    //   });

    this.items = this.items = [ {
      value: 'item1',
      viewValue: 'coca',
      rinde: 2
    }]

    this.itemsParaElEvento = []
  }

  ngOnInit() {
  }


  agregarAlListado() {
    let item = {
      nombre: this.nombreItemNuevo,
      personasPorUnidad: this.rendimientoItemNuevo,
      monto: this.montoItemNuevo
    }

  }

  agregarAlEvento() {
    let item = {
      nombre: this.itemModel,
      cantidad: this.cantidadItemModel
    }

    this.itemsParaElEvento.push(item)

  }

  agAlListadoEsInvalido() {
    return !(this.nombreItemNuevo && this.rendimientoItemNuevo && this.montoItemNuevo)
  }

  agAlEventoEsInvalido() {
    return !(this.itemModel && this.cantidadItemModel)
  }


}
