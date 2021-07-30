import { TestBed } from '@angular/core/testing';

import { MedicalEquipmentResourceService } from './medical-equipment-resource.service';

describe('MedicalEquipmentResourceService', () => {
  let service: MedicalEquipmentResourceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MedicalEquipmentResourceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
