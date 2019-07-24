import { TestBed } from '@angular/core/testing';

import { InfoReqService } from './info-req.service';

describe('InfoReqService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: InfoReqService = TestBed.get(InfoReqService);
    expect(service).toBeTruthy();
  });
});
