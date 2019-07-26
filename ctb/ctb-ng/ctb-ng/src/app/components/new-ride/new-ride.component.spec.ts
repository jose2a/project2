import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewRideComponent } from './new-ride.component';

describe('NewRideComponent', () => {
  let component: NewRideComponent;
  let fixture: ComponentFixture<NewRideComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewRideComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewRideComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
