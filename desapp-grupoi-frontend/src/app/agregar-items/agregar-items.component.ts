import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ItemService } from '../services/item.service';
import { Item } from '../model/Item.model';

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

    this.getItems();

    this.itemsParaElEvento = []
  }

//esto en un servicio
  getItems(){
    this.itService.getItemsDisponibles()
    .subscribe(res => {
      console.log(res);
      this.items = res.result as Item[];

    });
  }

  ngOnInit() {
  }

//TODO: genera un item y lo manda al servicio para guardarlo
  agregarAlListado() {
    let item = {
      nombre: this.nombreItemNuevo,
      personasPorUnidad: this.rendimientoItemNuevo,
      monto: this.montoItemNuevo
    }

  }

  //guarda en una lista para que sea tomado por componente padre
  //TODO: implementar databinding
  agregarAlEvento() {
    let item = {
      nombre: this.itemModel,
      cantidad: this.cantidadItemModel
    }

    this.itemsParaElEvento.push(item)

  }


//VALIDACIONES DE BOTONES

  agAlListadoEsInvalido() {
    return !(this.nombreItemNuevo && this.rendimientoItemNuevo && this.montoItemNuevo)
  }

  agAlEventoEsInvalido() {
    return !(this.itemModel && this.cantidadItemModel)
  }


}
