import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ItemService } from '../services/item.service';
import { Item } from '../model/Item.model';
import { UtilsService } from '../services/utils.service';
import { TemplateItem } from '../model/template-item.model';

@Component({
  selector: 'app-agregar-items',
  templateUrl: './agregar-items.component.html',
  styleUrls: ['./agregar-items.component.scss']
})
export class AgregarItemsComponent implements OnInit {
  
  items: Item[];

  // Variables agregado de items
  itemModel: Item;
  cantidadItemModel: number;
  // Variables item nuevo
  nombreItemNuevo: string
  rendimientoItemNuevo: number
  montoItemNuevo: number

  itemsParaUsar: TemplateItem[];

  constructor(
    private itemService: ItemService, 
    private utilsService: UtilsService) {

    this.getItems();

    this.itemsParaUsar = []
  }

//esto en un servicio
  getItems(){
    this.itemService.getItemsDisponibles()
      .subscribe(res => {
        this.items = res.result as Item[];
      });
  }

  ngOnInit() {
  }

  crearItemNuevo() {
    var item = new Item(this.nombreItemNuevo, this.rendimientoItemNuevo, this.montoItemNuevo);
    let message = this.nombreItemNuevo + " creado. "
    let action = "$"+ this.montoItemNuevo;
    this.itemService.crearItem(item.toJSON())
      .subscribe(res => {
        this.getItems();
        this.limpiarFormCreacion();
        this.utilsService.notificacion(message, action);
      });

  }

  limpiarFormCreacion(){
    this.nombreItemNuevo = undefined;
    this.rendimientoItemNuevo = undefined;
    this.montoItemNuevo = undefined;
  }

  //guarda en una lista para que sea tomado por componente padre
  //TODO: implementar databinding
  agregarAlEvento() {
    // Agregamos a la lista
    var i = this.cantidadItemModel;
    var templateItem = new TemplateItem(this.itemModel, i);
    this.itemsParaUsar.push(templateItem);
    // Mostramos notificacion
    let message = this.itemModel.nombreItem + " agregado "
    let action = this.cantidadItemModel+" unidades"
    this.utilsService.notificacion(message, action);
    
    this.limpiarFormAgregar();
  }

  limpiarFormAgregar(){
    this.itemModel = undefined;
    this.cantidadItemModel= undefined;
  }

  // VALIDACIONES DE BOTONES
  isItemNuevoValido() {
    return this.nombreItemNuevo && this.rendimientoItemNuevo && this.montoItemNuevo;
  }

  isItemValido() {
    return this.itemModel && this.cantidadItemModel;
  }


}
