

export class Usuario {

  constructor(_id='', nombre='', apellido='', email='') {
    this._id = _id;
    this.nombre = nombre;
    this.apellido = apellido;
    this.email = email;
  };

  _id: string;
  nombre: string;
  apellido: string;
  email: string;

}