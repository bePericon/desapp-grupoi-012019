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

  tarjetaForm: FormGroup;
  numeroEditado: string;
  CARD: any;
  card: any;
  types: any[];

  constructor(
    private _formBuilder: FormBuilder,
    public dialogRef: MatDialogRef<EditTarjetaComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Tarjeta) {}

  ngOnInit() {
    this.types = require('creditcards-types');
    this.CARD = require('creditcards/card');
    this.card = this.CARD(this.types);
    
    var datos = this.obtenerDatos();
    
    this.tarjetaForm = this._formBuilder.group({
      tipoCtrl: [datos.type, Validators.required],
      numeroCtrl: [datos.number, Validators.required]
    });
  }

  obtenerDatos(){
    var num = this.card.parse(this.data.numero)
    var nombreTipo = this.card.type(num) as String;
    nombreTipo = nombreTipo.replace(" ","-").toLowerCase();
    return {
      type: require(`creditcards-types/types/${nombreTipo}`),
      number: this.data.numero
    };
  }

  getTipo(){ return  this.tarjetaForm.controls['tipoCtrl'].value;}
  getNumero(){ return  this.tarjetaForm.controls['numeroCtrl'].value;}

  onChange() {
    var card = this.CARD([this.getTipo()]);

    var num = card.parse(this.getNumero());
    
    if(!card.isValid(num)){
      this.tarjetaForm.controls['numeroCtrl'].setErrors( {match: true} );
    }else{
      this.tarjetaForm.controls['numeroCtrl'].setErrors( null );
    }
  }

  cancelar(){
    this.dialogRef.close();
  }

  tarjetaValida(){
    return !this.tarjetaForm.invalid;
  }

}

// class DataTipoTarjeta {

//   Type = require('creditcards-types/type');

//   visaCredito = this.Type({
//     name: 'Visa Credito',
//     pattern: /\b8[0-9]{3}[-][0-9]{4}[-][0-9]{4}[-][0-9](?:[0-9]{3})?\b/,
//     eagerPattern: /^8/
//   });

//   mastercardCredito = this.Type({
//     name: 'Mastercard Credito',
//     pattern: /^(?:5[1-5][0-9]{2}|222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}$/,
//     eagerPattern: /^(2[3-7]|22[2-9]|5[1-5])/
//   });

//   listTypes = [this.visaCredito , this.mastercardCredito];
// }

