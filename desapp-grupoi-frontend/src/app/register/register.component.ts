import { Component, OnInit } from '@angular/core';
import { FormControl, FormBuilder, FormGroup, FormGroupDirective, NgForm, Validators } from '@angular/forms';




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

  constructor(private _formBuilder: FormBuilder) { }


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
