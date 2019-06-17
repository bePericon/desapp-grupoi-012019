import { FormControl, Validators, FormGroup, FormBuilder } from '@angular/forms';
import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Tarjeta } from 'src/app/model/tarjeta.model';

@Component({
  selector: 'app-edit-tarjeta',
  templateUrl: './edit-tarjeta.component.html',
  styleUrls: ['./edit-tarjeta.component.scss']
})
export class EditTarjetaComponent implements OnInit {

  original: Tarjeta;
  tarjetaForm: FormGroup;
  numeroEditado: string;
  dataTipoTarjeta: DataTipoTarjeta;
  types: any[];

  constructor(
    private _formBuilder: FormBuilder,
    public dialogRef: MatDialogRef<EditTarjetaComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Tarjeta) { }

  ngOnInit() {
    this.original = new Tarjeta(this.data.numeroTarjeta, this.data.codigoSeguridad);
    this.dataTipoTarjeta = new DataTipoTarjeta();
    this.types = this.dataTipoTarjeta.listTypes;

    var datos = this.obtenerDatos();

    this.tarjetaForm = this._formBuilder.group(
      {
        tipoCtrl: [datos.type, Validators.required],
        numeroCtrl: [datos.number, Validators.required],
        codigoCtrl: [datos.cod, Validators.required]
      }
    );
  }

  obtenerDatos() {
    var num = this.data.numeroTarjeta;
    var tipo = this.dataTipoTarjeta.getType(num) as TypeCard;
    return {
      type: tipo,
      number: this.data.numeroTarjeta,
      cod: this.data.codigoSeguridad
    };
  }

  getTipo(){ return  this.tarjetaForm.controls['tipoCtrl'].value;}
  getNumero(){ return  this.tarjetaForm.controls['numeroCtrl'].value;}

  onChange() {
    if(!this.dataTipoTarjeta.isValid(this.getTipo(), this.getNumero())){
      this.tarjetaForm.controls['numeroCtrl'].setErrors( {match: true} );
    }else{
      this.tarjetaForm.controls['numeroCtrl'].setErrors( null );
    }
  }

  cancelar() {
    this.data = this.original;
    this.dialogRef.close();
  }

  tarjetaValida() {
    return !this.tarjetaForm.invalid;
  }

}

class TypeCard {

  constructor(n: string, p: string){
    this.name = n;
    this.pattern = p;
  }

  name: string;
  pattern: string;
}

class DataTipoTarjeta {

  visaCredito = new TypeCard(
    'Visa Credito', 
    '^4[0-9]{3}[-][0-9]{4}[-][0-9]{4}[-][0-9]{4}$'
  );

  mastercardCredito = new TypeCard(
    'American Express',
    '^3[47][0-9]{13}$'
  );

  listTypes = [this.visaCredito , this.mastercardCredito];

  isValid(type: TypeCard, num: string){
    return this.match(type.pattern, num)
  }

  getType(num: string){
    var retType = this.listTypes.find(t => this.match(t.pattern, num));
    return retType;
  }

  match(pattern: string, num: string){
    var 
      regex = new RegExp(pattern),
      test = regex.test(num);
    return test;
  }
}

