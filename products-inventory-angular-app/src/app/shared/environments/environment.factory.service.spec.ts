import { TestBed } from '@angular/core/testing';

import { Environment.FactoryService } from './environment.factory.service';

describe('Environment.FactoryService', () => {
  let service: Environment.FactoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Environment.FactoryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
