import { AuthService } from './../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { Usuario } from '../model/usuario.model';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  nombreForm: FormGroup;
  apellidoForm: FormGroup;
  emailForm: FormGroup;
  contraseniaForm: FormGroup;
  fechaNacForm: FormGroup;

  estaTodoListo: Boolean;

  constructor(
    private authService: AuthService,
    private _formBuilder: FormBuilder) { }


  ngOnInit() {
    this.nombreForm = this._formBuilder.group({
      nombreCtrl: ['', Validators.required]
    });
    this.apellidoForm = this._formBuilder.group({
      apellidoCtrl: ['', Validators.required]
    });
    this.emailForm = this._formBuilder.group({
      emailCtrl: ['', [Validators.required, Validators.email]]
    });
    this.contraseniaForm = this._formBuilder.group({
      contraseniaCtrl: ['', Validators.required],
      contraseniaDosCtrl: ['', Validators.required]
    },{validator: this.mustMatch('contraseniaCtrl', 'contraseniaDosCtrl')});
    this.fechaNacForm = this._formBuilder.group({
      fechaNacCtrl: ['', Validators.required]
    });

    this.estaTodoListo = false;
  }

  //Metodo para ver si las contraseñas son iguales
  mustMatch(controlName: string, matchingControlName: string) {
    return (formGroup: FormGroup) => {
        const control = formGroup.controls[controlName];
        const matchingControl = formGroup.controls[matchingControlName];
        if (matchingControl.errors && !matchingControl.errors.mustMatch) {
            // return if another validator has already found an error on the matchingControl
            return;
        }
        // set error on matchingControl if validation fails
        if (control.value !== matchingControl.value) {
            matchingControl.setErrors({ mustMatch: true });
        } else {
            matchingControl.setErrors(null);
        }
    }
  }

  ultimoPasoRealizado(){
    this.estaTodoListo = true;
  }

  resetForm(){
    this.estaTodoListo = false;
  }

  registroValido(){
    return !this.nombreForm.invalid && !this.apellidoForm.invalid && !this.emailForm.invalid 
    && !this.fechaNacForm.invalid && !this.contraseniaForm.invalid && this.estaTodoListo;
  }

  registro(){

    if (!this.registroValido()) { 
        console.log("vamos mal!");
        return; 
      }

    var usuario = {
      nombre: this.nombreForm.controls['nombreCtrl'].value,
      apellido: this.apellidoForm.controls['apellidoCtrl'].value,
      email: this.emailForm.controls['emailCtrl'].value,
      contrasenia: this.contraseniaForm.controls['contraseniaCtrl'].value,
      fechaNac:  this.fechaNacForm.controls['fechaNacCtrl'].value
    };
    
    console.log("Vamos bien! usuario:" + usuario);

    this.authService.registerUser(usuario).subscribe(
      res => {
        console.log("Se guardo correctamente");
      },
      error => {
        console.log(error);
      }
    );
  }


}
