import { TestBed } from '@angular/core/testing';

import { PlasmarequestService } from './plasmarequest.service';

describe('PlasmarequestService', () => {
  let service: PlasmarequestService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PlasmarequestService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
