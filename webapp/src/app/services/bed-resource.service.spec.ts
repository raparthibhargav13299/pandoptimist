import { TestBed } from '@angular/core/testing';

import { BedResourceService } from './bed-resource.service';

describe('BedResourceService', () => {
  let service: BedResourceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BedResourceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
