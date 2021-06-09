import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminWorkSpaceLinkComponent } from './admin-work-space-link.component';

describe('AdminWorkSpaceLinkComponent', () => {
  let component: AdminWorkSpaceLinkComponent;
  let fixture: ComponentFixture<AdminWorkSpaceLinkComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminWorkSpaceLinkComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminWorkSpaceLinkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
