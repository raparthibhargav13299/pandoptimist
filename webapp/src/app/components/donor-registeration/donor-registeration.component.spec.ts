import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DonorRegisterationComponent } from './donor-registeration.component';

describe('DonorRegisterationComponent', () => {
  let component: DonorRegisterationComponent;
  let fixture: ComponentFixture<DonorRegisterationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DonorRegisterationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DonorRegisterationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
