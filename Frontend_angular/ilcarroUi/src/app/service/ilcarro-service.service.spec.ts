import { TestBed } from '@angular/core/testing';

import { IlcarroServiceService } from './ilcarro-service.service';

describe('IlcarroServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: IlcarroServiceService = TestBed.get(IlcarroServiceService);
    expect(service).toBeTruthy();
  });
});
