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
    },{validator: this.match('tipoCtrl', 'numeroCtrl')});
  }

  obtenerDatos(){
    var num = this.data.numero; //this.card.parse(this.data.numero)
    var nombreTipo = this.card.type(num) as String;
    nombreTipo = nombreTipo.toLowerCase().replace(" ", "");
    return {
      type: require(`creditcards-types/types/${nombreTipo}`),
      number: num
    };
  }

  match(tipo: string, numero: string) {
    return (formGroup: FormGroup) => {
      const tipoCtrl = formGroup.controls[tipo];
      const numeroCtrl = formGroup.controls[numero];

      var card = this.CARD([tipoCtrl.value]);
      var num = this.data.numero; //this.card.parse(this.data.numero)

      (card.isValid(num))? numeroCtrl.setErrors(null) : numeroCtrl.setErrors({ match: true});

    }
  }

  onChange() {
    // console.log(this.tipoCtrl.value);
    // this.card = this.CARD([this.tipoCtrl.value]);

    this.match('tipoCtrl', 'numeroCtrl');   
  }

  cancelar(){
    this.dialogRef.close();
  }

  tarjetaValida(){
    return !this.tarjetaForm.invalid;
  }

}
