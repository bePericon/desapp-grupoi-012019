import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EventeandoComponent } from './eventeando.component';

describe('EventeandoComponent', () => {
  let component: EventeandoComponent;
  let fixture: ComponentFixture<EventeandoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EventeandoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EventeandoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
