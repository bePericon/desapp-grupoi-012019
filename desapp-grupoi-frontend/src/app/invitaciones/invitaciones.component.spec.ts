import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InvitacionesComponent } from './invitaciones.component';

describe('InvitacionesComponent', () => {
  let component: InvitacionesComponent;
  let fixture: ComponentFixture<InvitacionesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InvitacionesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InvitacionesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
