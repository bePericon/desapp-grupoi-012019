import { Movimiento } from './movimiento.model';

export class Cuenta {
  
  id: string;
  saldo: {
    monto: number;
  };
  situacionDeuda: string;
  movimientos: Movimiento[];
}
