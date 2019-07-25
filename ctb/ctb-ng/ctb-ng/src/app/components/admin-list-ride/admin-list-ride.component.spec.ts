import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminListRideComponent } from './admin-list-ride.component';

describe('AdminListRideComponent', () => {
  let component: AdminListRideComponent;
  let fixture: ComponentFixture<AdminListRideComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminListRideComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminListRideComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
