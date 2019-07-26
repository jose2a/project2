import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DriverShowRideComponent } from './driver-show-ride.component';

describe('DriverShowRideComponent', () => {
  let component: DriverShowRideComponent;
  let fixture: ComponentFixture<DriverShowRideComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DriverShowRideComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DriverShowRideComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
