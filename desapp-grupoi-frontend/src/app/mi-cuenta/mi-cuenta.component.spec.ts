import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MiCuentaComponent } from './mi-cuenta.component';

describe('MiCuentaComponent', () => {
  let component: MiCuentaComponent;
  let fixture: ComponentFixture<MiCuentaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MiCuentaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MiCuentaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
