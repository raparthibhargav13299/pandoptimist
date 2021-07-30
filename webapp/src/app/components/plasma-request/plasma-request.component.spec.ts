import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PlasmaRequestComponent } from './plasma-request.component';

describe('PlasmaRequestComponent', () => {
  let component: PlasmaRequestComponent;
  let fixture: ComponentFixture<PlasmaRequestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PlasmaRequestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PlasmaRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
