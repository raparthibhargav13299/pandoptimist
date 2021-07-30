import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BedRequestComponent } from './bed-request.component';

describe('BedRequestComponent', () => {
  let component: BedRequestComponent;
  let fixture: ComponentFixture<BedRequestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BedRequestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BedRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
