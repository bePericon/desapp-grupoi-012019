import { Usuario } from './usuario.model';

export class Evento {

  constructor() {
  };

  _id: string;
  nombre: String;
  organizador: Usuario;
  descripcion: String;
  asistentes: Usuario[];
}
