import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PassengerListRideComponent } from './passenger-list-ride.component';

describe('PassengerListRideComponent', () => {
  let component: PassengerListRideComponent;
  let fixture: ComponentFixture<PassengerListRideComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PassengerListRideComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PassengerListRideComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
