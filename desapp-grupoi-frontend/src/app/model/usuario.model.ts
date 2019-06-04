
export class Usuario {

  constructor(_id= 0, nombre= '', apellido= '', email='', pass='',fechaNac=null){
    this._id = _id;
    this.nombre= nombre;
    this.apellido= apellido;
    this.email= email;
    this.password= pass;
    this.fechaNac= fechaNac;
  }

  _id: number;
  nombre: string;
  apellido: string;
  email: string;
  password: string;
  fechaNac: Date;
}
