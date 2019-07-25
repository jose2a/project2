import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DriverListRideComponent } from './driver-list-ride.component';

describe('DriverListRideComponent', () => {
  let component: DriverListRideComponent;
  let fixture: ComponentFixture<DriverListRideComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DriverListRideComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DriverListRideComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
