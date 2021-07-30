import { TestBed } from '@angular/core/testing';

import { SOSRequestService } from './sosrequest.service';

describe('SOSRequestService', () => {
  let service: SOSRequestService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SOSRequestService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
