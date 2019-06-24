import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroupDirective, NgForm, Validators, FormGroup, FormBuilder} from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';
import { Router } from '@angular/router';

import {MatDialog} from '@angular/material';

import { RegisterComponent } from '../register/register.component';
import { AuthService } from '../services/auth.service';
import { StorageService } from '../services/storage.service';


// export class MyErrorStateMatcher implements ErrorStateMatcher {
//   isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
//     const isSubmitted = form && form.submitted;
//     return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
//   }
// }


@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.scss']
})
export class LandingComponent implements OnInit {

  myForm: FormGroup;
  submitted: Boolean = false;
  error_password: Boolean = false;
  error_not_found: Boolean = false;
  // Variable para visibilidad de contraseña.
  hide = true;

  constructor(
    private authService: AuthService,
    private storageService: StorageService,
    private router: Router,
    private formBuilder: FormBuilder, 
    public dialog: MatDialog
  ) { }

  ngOnInit() {
    this.myForm = this.formBuilder.group({
      emailCtrl: ['', [Validators.required, Validators.email]],
      contraseniaCtrl: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(20)]]
    });
  }

  // Get the form fields.
  get getEmail() { return this.myForm.get('emailCtrl'); }
  get getContrasenia() { return this.myForm.get('contraseniaCtrl'); }

  // signIn() {
  //   this.submitted = true;

  //   if (this.myForm.invalid) { return; }

  //   var usuario = {
  //     email: this.myForm.controls['emailCtrl'].value,
  //     contrasenia: this.myForm.controls['contraseniaCtrl'].value,
  //   }

  //   this.authService.singIn(usuario)
  //     .subscribe(
  //       res => {
  //         this.storageService.setCurrentSession(res);
  //         this.goto('eventos');
  //       },
  //       error => {
  //         if(error.error.status == 404)
  //           this.error_not_found = true;
  //         else if (error.error.status == 400)
  //           this.error_password = true;
            
  //         console.log('Error: '+ error.error.message);
  //       }
  //     );
  // }

  goto(ruta){
    this.router.navigate(['eventeando/'+ruta]);
  }

  gotoNosotros(){
    this.router.navigate(['nosotros']);
  }

  openDialog() {
    const dialogRef = this.dialog.open(RegisterComponent);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }


}
