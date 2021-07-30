import { TestBed } from '@angular/core/testing';

import { ConsultantServiceService } from './consultant-service.service';

describe('ConsultantServiceService', () => {
  let service: ConsultantServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ConsultantServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
