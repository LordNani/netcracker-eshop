import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagerWorkspaceComponent } from './manager-workspace.component';

describe('ManagerWorkspaceComponent', () => {
  let component: ManagerWorkspaceComponent;
  let fixture: ComponentFixture<ManagerWorkspaceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManagerWorkspaceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManagerWorkspaceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
