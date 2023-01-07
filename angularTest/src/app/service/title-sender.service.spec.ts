import { TestBed } from '@angular/core/testing';

import { TitleSenderService } from './title-sender.service';

describe('TitleSenderService', () => {
  let service: TitleSenderService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TitleSenderService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
