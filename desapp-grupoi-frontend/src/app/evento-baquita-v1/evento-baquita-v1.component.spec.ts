import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EventoBaquitaV1Component } from './evento-baquita-v1.component';

describe('EventoBaquitaV1Component', () => {
  let component: EventoBaquitaV1Component;
  let fixture: ComponentFixture<EventoBaquitaV1Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EventoBaquitaV1Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EventoBaquitaV1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
