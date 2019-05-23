import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MostrarInvitacionesComponent } from './mostrar-invitaciones.component';

describe('MostrarInvitacionesComponent', () => {
  let component: MostrarInvitacionesComponent;
  let fixture: ComponentFixture<MostrarInvitacionesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MostrarInvitacionesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MostrarInvitacionesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
