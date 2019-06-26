import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import { EventoService } from '../services/evento.service';

@Component({
  selector: 'app-evento-fiesta',
  templateUrl: './evento-fiesta.component.html',
  styleUrls: ['./evento-fiesta.component.scss']
})
export class EventoFiestaComponent implements OnInit {

  modalidadControl: FormControl;
  nombreForm: FormGroup;
  descripcionForm: FormGroup;
  fechaForm: FormGroup;
  itemForm: FormGroup;
  emailForm: FormGroup;

  invitados;

  constructor(private _formBuilder: FormBuilder, private eventoService: EventoService) {
    this.invitados = [];
   }

  ngOnInit() {
       //formgroup
       this.nombreForm = this._formBuilder.group({
        nombreCtrl: ['', Validators.required] //formControl
      });
      this.descripcionForm = this._formBuilder.group({
        descripcionCtrl: ['', Validators.required]
      });
  
      this.fechaForm = this._formBuilder.group({
        fechaCtrl: ['', Validators.required]
      });
  
      this.emailForm = this._formBuilder.group({
        emailCtrl: ['', [Validators.email]]
      });
  }

  
  invitar() {
    let invitado = this.emailForm.value.emailCtrl; //agarra el dato
    this.invitados.push(invitado);  //agrega a una lista

    this.emailForm.get('emailCtrl').setValue(''); //lo blanquea
    this.emailForm.get('emailCtrl').markAsPristine(); //limpiamos las validaciones
    this.emailForm.get('emailCtrl').markAsUntouched(); // limpiamos las validaciones
  }


  removerInvitado(inv) {
    const index = this.invitados.indexOf(inv, 0);
    if (index > -1) {
      this.invitados.splice(index, 1);
    }
  }

  cancelarCreacion(stepper) {
    this.invitados = [];

    stepper.reset();
  }

  emailInvalido() {
    return this.emailForm.get('emailCtrl').errors || this.emailForm.get('emailCtrl').pristine;
  }


}
