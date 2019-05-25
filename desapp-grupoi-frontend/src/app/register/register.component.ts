import { Component, OnInit } from '@angular/core';
import { FormControl, FormBuilder, FormGroup, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
import { Router } from '@angular/router';
// import {FormBuilder, FormGroup, Validators} from '@angular/forms';

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

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

  matcher = new MyErrorStateMatcher();

  constructor(private _formBuilder: FormBuilder) { }


  // emailFormControl = new FormControl('', [
  //   Validators.required,
  //   Validators.email
  // ]);


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
      contraseniaCtrl: ['', Validators.required]
    });
    this.fechaNacForm = this._formBuilder.group({
      fechaNacCtrl: ['', Validators.required]
    });
  }




}
