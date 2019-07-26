import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PassengerBookingsComponent } from './passenger-bookings.component';

describe('PassengerBookingsComponent', () => {
  let component: PassengerBookingsComponent;
  let fixture: ComponentFixture<PassengerBookingsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PassengerBookingsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PassengerBookingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
