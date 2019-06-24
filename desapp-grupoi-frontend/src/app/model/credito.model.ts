import { Monto } from './monto.model';


export class Credito {

  // id: string;
  monto: Monto;
  cuotas: number;
  montoRestante: Monto;
  cuotasRestantes: number;
  estado: string;
  montoCuota: Monto;
}