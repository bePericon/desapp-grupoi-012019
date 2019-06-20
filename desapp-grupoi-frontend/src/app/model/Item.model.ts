import { JSONclass } from './json.model';
import { Monto } from './monto.model';

export class Item extends JSONclass {

  constructor(nombre, personasPorUnidad, costo){
    super();
    this.nombreItem = nombre;
    this.personasPorUnidad = personasPorUnidad;
    this.costo = new Monto(costo);
  }

  id: string;
  nombreItem: String;
  personasPorUnidad: String;
  costo: Monto;
}
