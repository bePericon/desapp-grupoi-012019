import { BrowserModule } from '@angular/platform-browser';
import { NgModule, LOCALE_ID } from '@angular/core';
import { MatButtonModule, MatDialogModule, MatIconModule, MatNativeDateModule, MatPaginator } from '@angular/material';
import { MatCardModule } from '@angular/material/card';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LandingComponent } from './landing/landing.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule } from '@angular/material/input';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
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
import { MatSelectModule } from '@angular/material/select';
import { InvitacionesComponent } from './invitaciones/invitaciones.component';
import { BilleteraComponent } from './billetera/billetera.component';
import { MostrarInvitacionesComponent } from './mostrar-invitaciones/mostrar-invitaciones.component';
import { MatDatepickerModule, } from '@angular/material/datepicker';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatExpansionModule } from '@angular/material/expansion';
import { MiCuentaComponent } from './mi-cuenta/mi-cuenta.component';
import { CrearTemplateComponent } from './crear-template/crear-template.component';
import { MostrarTemplateComponent } from './mostrar-template/mostrar-template.component';
import { EstadoCuentaComponent } from './estado-cuenta/estado-cuenta.component';
import { MatListModule } from '@angular/material/list';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { AgregarItemsComponent } from './agregar-items/agregar-items.component';
import { IngresarDineroComponent } from './ingresar-dinero/ingresar-dinero.component';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { EditTarjetaComponent } from './edit-tarjeta/edit-tarjeta.component';
import localeEsAr from '@angular/common/locales/es-AR';
import { registerLocaleData } from '@angular/common';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { CreditoComponent } from './credito/credito.component';
import { LoginComponent } from './login/login.component';
import { AuthService } from './services/auth.service';
import { AdministracionComponent } from './administracion/administracion.component';
import { EventoFiestaComponent } from './evento-fiesta/evento-fiesta.component';
import { CardInvitacionComponent } from './card-invitacion/card-invitacion.component';
import { CardEventoComponent } from './card-evento/card-evento.component';
import { ItemsComponent } from './items/items.component';
import { InvitadosComponent } from './invitados/invitados.component';


registerLocaleData(localeEsAr, 'es-Ar');

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
    MostrarInvitacionesComponent,
    MiCuentaComponent,
    CrearTemplateComponent,
    MostrarTemplateComponent,
    EstadoCuentaComponent,
    AgregarItemsComponent,
    EditTarjetaComponent,
    IngresarDineroComponent,
    CreditoComponent,
    LoginComponent,
    AdministracionComponent,
    EventoFiestaComponent,
    CardInvitacionComponent,
    CardEventoComponent,
    ItemsComponent,
    InvitadosComponent
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
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatTooltipModule,
    MatExpansionModule,
    MatListModule,
    MatTableModule,
    MatPaginatorModule,
    MatCheckboxModule,
    MatProgressSpinnerModule,
    MatSnackBarModule
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
    MatSelectModule,
    MatDatepickerModule,
    MatTooltipModule,
    MatExpansionModule,
    MatListModule,
    MatTableModule,
    MatPaginatorModule,
    MatCheckboxModule,
    MatProgressSpinnerModule,
    MatSnackBarModule
  ],
  entryComponents: [
    RegisterComponent, 
    EditTarjetaComponent, 
    ItemsComponent, 
    InvitadosComponent
  ],
  providers: [
    { provide: LOCALE_ID, useValue: 'es-Ar' },
    AuthService
  ],
  bootstrap: [AppComponent]
})




export class AppModule {




}
