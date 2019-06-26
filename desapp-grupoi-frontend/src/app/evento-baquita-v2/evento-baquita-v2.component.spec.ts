import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EventoBaquitaV2Component } from './evento-baquita-v2.component';

describe('EventoBaquitaV2Component', () => {
  let component: EventoBaquitaV2Component;
  let fixture: ComponentFixture<EventoBaquitaV2Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EventoBaquitaV2Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EventoBaquitaV2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
