import { Usuario } from './usuario.model';

export class TemplateEvento {


  descripcion: string
  items: []
  modalidad: string
  nombre: string
  organizador: Usuario
  visibilidad: string

  constructor(descripcion: string, items: [], modalidad: string, nombre: string, organizador: Usuario, visibilidad) {
    this.descripcion = descripcion
    this.items = items
    this.modalidad = modalidad
    this.nombre = nombre
    this.organizador = organizador
    this.visibilidad = visibilidad
  }


}
