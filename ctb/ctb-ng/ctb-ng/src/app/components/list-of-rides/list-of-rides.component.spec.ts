import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListOfRidesComponent } from './list-of-rides.component';

describe('ListOfRidesComponent', () => {
  let component: ListOfRidesComponent;
  let fixture: ComponentFixture<ListOfRidesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListOfRidesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListOfRidesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
