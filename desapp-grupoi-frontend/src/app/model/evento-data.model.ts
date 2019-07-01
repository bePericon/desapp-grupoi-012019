import { TemplateItem } from './template-item.model';

export class EventoData {
  mostrarSeleccionar: boolean;
  items: TemplateItem[];

  constructor(mostrar, items){
    this.mostrarSeleccionar = mostrar;
    this.items = items;
  }
}