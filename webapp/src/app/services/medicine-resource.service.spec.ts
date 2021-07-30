import { TestBed } from '@angular/core/testing';

import { MedicineResourceService } from './medicine-resource.service';

describe('MedicineResourceService', () => {
  let service: MedicineResourceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MedicineResourceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
