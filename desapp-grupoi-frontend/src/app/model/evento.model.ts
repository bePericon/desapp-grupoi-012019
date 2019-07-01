import { TemplateEvento } from './template-evento.model';
import { Monto } from './monto.model';
import { Usuario } from './usuario.model';
import { Modalidad } from './modalidad.model';

export class Evento {

  constructor() {
  };

  id: string;
  nombre: String;
  organizador: Usuario;
  template: TemplateEvento;
  asistentes: Usuario[];
  cantidadAsistentes: number;
  totalAsistentes: number;
  cantidadItems: number;
  costoUsuario: Monto;
  costoTotal: Monto;
  cantidadItemsComprados: number;
  modalidad: Modalidad;
}
