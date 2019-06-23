import { JSONclass } from './json.model';
import { Item } from './Item.model';


export class TemplateItem extends JSONclass {

  constructor(item, cantidad){
    super();
    this.item = item;
    this.cantidad = cantidad;
  }

  id: string;
  item: Item;
  cantidad: number;

}

