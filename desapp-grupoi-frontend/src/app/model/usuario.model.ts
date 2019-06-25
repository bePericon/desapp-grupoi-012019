
export class Usuario {

  id: number;
  nombre: string;
  apellido: string;
  email: string;
  password: string;
  fechaNac: Date;


  getNombreYApellido(){
    return this.nombre+" "+this.apellido
  }


}
