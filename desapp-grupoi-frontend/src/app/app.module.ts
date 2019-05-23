import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MatButtonModule, MatDialogModule, MatIconModule, } from '@angular/material';
import { MatCardModule } from '@angular/material/card';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LandingComponent } from './landing/landing.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule } from '@angular/material/input';
import { FormsModule, ReactiveFormsModule, FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { EventosComponent } from './eventos/eventos.component';
import { NosotrosComponent } from './nosotros/nosotros.component';
import { RegisterComponent } from './register/register.component';
import { CdkStepperModule } from '@angular/cdk/stepper';
import { MatStepperModule } from '@angular/material/stepper';
import { MenuComponent } from './menu/menu.component';
import { MatTabsModule } from '@angular/material/tabs';
import { MatRadioModule } from '@angular/material/radio';
import { HttpClientModule } from '@angular/common/http';
import { Routes, RouterModule } from '@angular/router';
import { EventeandoComponent } from './eventeando/eventeando.component';
import { MostrarEventosComponent } from './mostrar-eventos/mostrar-eventos.component';
import { CrearEventoComponent } from './crear-evento/crear-evento.component';
import {MatSelectModule} from '@angular/material/select';
import { InvitacionesComponent } from './invitaciones/invitaciones.component';
import { BilleteraComponent } from './billetera/billetera.component';
import { MostrarInvitacionesComponent } from './mostrar-invitaciones/mostrar-invitaciones.component';

@NgModule({
  declarations: [
    AppComponent,
    LandingComponent,
    EventosComponent,
    NosotrosComponent,
    RegisterComponent,
    MenuComponent,
    EventeandoComponent,
    MostrarEventosComponent,
    CrearEventoComponent,
    InvitacionesComponent,
    BilleteraComponent,
    MostrarInvitacionesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatDialogModule,
    MatIconModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatStepperModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatTabsModule,
    MatRadioModule,
    HttpClientModule,
    RouterModule.forRoot([]),
    RouterModule.forChild([]),
    MatSelectModule


  ],
  exports: [
    BrowserModule,
    AppRoutingModule,
    MatDialogModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    CdkStepperModule,
    MatStepperModule,
    MatTabsModule,
    MatRadioModule,
    HttpClientModule,
    MatSelectModule
  ],
  entryComponents: [RegisterComponent],
  providers: [],
  bootstrap: [AppComponent]
})




export class AppModule {




}
