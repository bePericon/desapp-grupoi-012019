import { Tarjeta } from './tarjeta.model';
import { Movimiento } from './movimiento.model';

export class Cuenta {
  
  id: string;
  saldo: {
    monto: number;
  };
  situacionDeuda: string;
  movimientos: Movimiento[];
  tarjetaCredito: Tarjeta;
}
