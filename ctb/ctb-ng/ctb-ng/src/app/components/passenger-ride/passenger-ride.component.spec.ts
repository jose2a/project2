import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PassengerRideComponent } from './passenger-ride.component';

describe('PassengerRideComponent', () => {
  let component: PassengerRideComponent;
  let fixture: ComponentFixture<PassengerRideComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PassengerRideComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PassengerRideComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
