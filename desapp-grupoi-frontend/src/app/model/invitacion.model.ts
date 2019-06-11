import { Usuario } from './usuario.model';
import { Evento } from './evento.model';

export class Invitacion {

  constructor() {
  };

  _id: string;
  nombre: String;
  evento: Evento;
  descripcion: String;
  asistentes: Usuario[];
  
}
