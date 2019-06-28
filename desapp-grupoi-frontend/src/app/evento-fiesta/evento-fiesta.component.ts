import { AgregarItemsComponent } from './../agregar-items/agregar-items.component';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import { EventoService } from '../services/evento.service';
import { UtilsService } from '../services/utils.service';

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

  invitados: string[];

  mostrar: boolean;

  @ViewChild(AgregarItemsComponent) agregarItemsComponent: AgregarItemsComponent;

  constructor(
    private _formBuilder: FormBuilder, 
    private eventoService: EventoService,
    private uService: UtilsService) {
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

      this.mostrar = true;
  }

  getNombre() { return this.nombreForm.value.nombreCtrl; }
  getDescripcion() { return this.descripcionForm.value.descripcionCtrl; }
  getFecha() { return this.fechaForm.value.fechaCtrl; }
  
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

  creacionValida() {
    return !this.nombreForm.invalid && !this.descripcionForm.invalid
      && !this.fechaForm.invalid && (this.invitados.length > 0);
  }

  crearEvento(stepper){
    var evento = {
      nuevoTemplate: {
        template: {
          nombre: this.getNombre(),
          descripcion: this.getDescripcion()
        },
        fechaLimite: this.getFecha(),
        tipoModalidad: 'FIESTA',// Este valor depende de la modalidad seleccionada.
        items: this.agregarItemsComponent.itemsParaUsar
      },
      invitados: this.invitados
    }

    this.eventoService.crearEvento(evento)
      .subscribe(res => {
        this.uService.notificacion("Se creó correctamente el evento!", "");
        stepper.reset();
      });
  }

}
