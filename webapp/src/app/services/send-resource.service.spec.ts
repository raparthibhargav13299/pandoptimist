import { TestBed } from '@angular/core/testing';

import { SendResourceService } from './send-resource.service';

describe('SendResourceService', () => {
  let service: SendResourceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SendResourceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
