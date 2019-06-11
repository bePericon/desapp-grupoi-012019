import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class InvitacionesService {

  invitaciones = [];

  readonly URL_API = 'http://localhost:8080/app/evento';

  constructor(private http: HttpClient) {
    // this.selectedEvento = new Evento();

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
    // return this.http.get(this.URL_API+ '/populares/all'); 
    return this.invitaciones;
  };

  getAceptadas(){
    // return this.http.get(this.URL_API+ '/populares/all'); 
    return this.invitaciones;
  };

  getRechazadas(){
    // return this.http.get(this.URL_API+ '/populares/all'); 
    return this.invitaciones;
  };
  getPasadas(){
    // return this.http.get(this.URL_API+ '/populares/all'); 
    return this.invitaciones;
  };

}
