import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CatalogueLinkComponent } from './catalogue-link.component';

describe('CatalogueLinkComponent', () => {
  let component: CatalogueLinkComponent;
  let fixture: ComponentFixture<CatalogueLinkComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CatalogueLinkComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CatalogueLinkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
