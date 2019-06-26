import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EventoCanastaComponent } from './evento-canasta.component';

describe('EventoCanastaComponent', () => {
  let component: EventoCanastaComponent;
  let fixture: ComponentFixture<EventoCanastaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EventoCanastaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EventoCanastaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
