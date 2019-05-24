import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';
import { Router } from '@angular/router';

import {MatDialog} from '@angular/material';

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}


@Component({
  selector: 'app-mi-cuenta',
  templateUrl: './mi-cuenta.component.html',
  styleUrls: ['./mi-cuenta.component.scss']
})



export class MiCuentaComponent implements OnInit {

  constructor(private router: Router, public dialog: MatDialog) { }

  ngOnInit() {
  }


  emailFormControl = new FormControl('', [
    Validators.required,
    Validators.email,
  ]);

  hide = true;
  hideR = true;
  matcher = new MyErrorStateMatcher();

}
