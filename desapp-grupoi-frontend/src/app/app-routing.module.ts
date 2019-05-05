import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MDBBootstrapModule } from 'angular-bootstrap-md';

const routes: Routes = [];

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
    MDBBootstrapModule.forRoot()
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
