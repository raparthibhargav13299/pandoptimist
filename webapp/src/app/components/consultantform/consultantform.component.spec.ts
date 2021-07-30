import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultantformComponent } from './consultantform.component';

describe('ConsultantformComponent', () => {
  let component: ConsultantformComponent;
  let fixture: ComponentFixture<ConsultantformComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConsultantformComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsultantformComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
