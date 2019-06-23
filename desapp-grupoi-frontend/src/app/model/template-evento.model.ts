import { Modalidad } from './modalidad.model';
import { TemplateItem } from './template-item.model';
import { Usuario } from './usuario.model';

export class TemplateEvento {

  id: string;
  descripcion: string
  templateItems: TemplateItem[]
  modalidad: Modalidad;
  nombre: string
  organizador: Usuario
  visibilidad: string

  constructor(descripcion, items, modalidad, nombre, organizador, visibilidad) {
    this.descripcion = descripcion
    this.templateItems = items
    this.modalidad = modalidad
    this.nombre = nombre
    this.organizador = organizador
    this.visibilidad = visibilidad
  }

  esPublico: Boolean = this.visibilidad == 'PUBLICA';
  esPrivado: Boolean = this.visibilidad == 'PRIVADA';
}
