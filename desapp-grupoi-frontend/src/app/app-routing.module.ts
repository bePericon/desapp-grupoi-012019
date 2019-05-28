import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LandingComponent } from './landing/landing.component';
import { EventeandoComponent } from './eventeando/eventeando.component';
import { EventosComponent } from './eventos/eventos.component';
import { NosotrosComponent } from './nosotros/nosotros.component';
import { BilleteraComponent } from './billetera/billetera.component';
import { InvitacionesComponent } from './invitaciones/invitaciones.component';
import { CrearEventoComponent } from './crear-evento/crear-evento.component';
import { MiCuentaComponent } from './mi-cuenta/mi-cuenta.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {
    path: 'home',
    component: LandingComponent
  },
  {
    path: 'nosotros',
    pathMatch: 'full',
    component: NosotrosComponent
  },
  {
    path: 'eventeando',
    component: EventeandoComponent,
    pathMatch: 'prefix',
    children: [
      { path: '', redirectTo: 'eventos', pathMatch: 'full' },
      { path: 'eventos', component: EventosComponent, pathMatch: 'full' },
      { path: 'crear-evento', component: CrearEventoComponent, pathMatch: 'full' },
      { path: 'invitaciones', component: InvitacionesComponent, pathMatch: 'full' },
      { path: 'billetera', component: BilleteraComponent, pathMatch: 'full' },
      { path: 'miCuenta', component: MiCuentaComponent, pathMatch: 'full' }
    ]
  }


];
@NgModule({
  imports: [
    RouterModule.forChild(routes)
    // RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
