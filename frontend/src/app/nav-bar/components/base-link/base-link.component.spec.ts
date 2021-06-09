import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BaseLinkComponent } from './base-link.component';

describe('BaseLinkComponent', () => {
  let component: BaseLinkComponent;
  let fixture: ComponentFixture<BaseLinkComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BaseLinkComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BaseLinkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
