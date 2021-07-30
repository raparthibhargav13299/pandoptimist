import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SosRequestComponent } from './sos-request.component';

describe('SosRequestComponent', () => {
  let component: SosRequestComponent;
  let fixture: ComponentFixture<SosRequestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SosRequestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SosRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
