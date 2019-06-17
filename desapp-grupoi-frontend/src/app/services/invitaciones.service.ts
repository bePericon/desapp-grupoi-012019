import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Evento } from '../model/evento.model';
import { StorageService } from './storage.service';
import { Session } from '../model/session.model';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class InvitacionesService {

  invitaciones = [];
  usuarioId: number;

  readonly URL_API = 'http://localhost:8080/app/invitacion';

  constructor(private http: HttpClient, private storageService: StorageService) {

    this.usuarioId = this.storageService.getCurrentUser().id;

    this.invitaciones = [
            {
              nombreEvento: "fiesta",
              descripcion: "sarasa",
              organizador: {
                nombre: "alejandro",
                apellido: "rossi"
              },
              cantidadAsistentes: 8,
              modalidad:"Fiesta"
            },
            {
              nombreEvento: "fiest2",
              descripcion: "sadsad",
              organizador: { nombre: "alejandro", apellido: "rossi" },
              cantidadAsistentes: 8,
              modalidad:"Fiesta"
            },
            {
              nombreEvento: "baquitaloca",
              descripcion: "baquita de prueba",
              organizador: { nombre: "Elto", apellido: "PuDeEma" },
              cantidadAsistentes: 8,
              modalidad:"Baquita"
            },
            {
              nombreEvento: "canastulli",
              descripcion: "sasaadsadsadasrasa",
              organizador: { nombre: "alejandro", apellido: "rossi" },
              cantidadAsistentes: 8,
              modalidad:"Canasta"
            },
            {
              nombreEvento: "una cosa",
              descripcion: "sarasasdsadsadsaa",
              organizador: { nombre: "alejandro", apellido: "rossi" },
              cantidadAsistentes: 8,
              modalidad:"Baquita"
            }
          ]
  }


  getPendientes(){
    // return this.http.get(this.URL_API+ '/aceptadas/'); 
    return this.invitaciones;
  };

  getAceptadas(){
    // return this.http.get(this.URL_API+ '/pendientes/'); 
    return this.invitaciones;
  };

  getRechazadas(){
    // return this.http.get(this.URL_API+ '/rechazadas/'); 
    return this.invitaciones;
  };
  getPasadas(){
    // return this.http.get(this.URL_API+ '/pasadas/'); 
    return this.invitaciones;
  };

}
