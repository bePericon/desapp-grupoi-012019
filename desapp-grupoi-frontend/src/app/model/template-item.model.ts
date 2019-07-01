import { JSONclass } from './json.model';
import { Item } from './Item.model';


export class TemplateItem extends JSONclass {

  constructor(id, item, cantidad){
    super();
    this.id = id;
    this.item = item;
    this.cantidad = cantidad;
  }

  id: string;
  item: Item;
  cantidad: number;

}

