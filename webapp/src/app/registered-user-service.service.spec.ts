import { TestBed } from '@angular/core/testing';

import { RegisteredUserServiceService } from './registered-user-service.service';

describe('RegisteredUserServiceService', () => {
  let service: RegisteredUserServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RegisteredUserServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
