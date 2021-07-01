import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductCatalogLinkComponent } from './product-catalog-link.component';

describe('ProductCatalogLinkComponent', () => {
  let component: ProductCatalogLinkComponent;
  let fixture: ComponentFixture<ProductCatalogLinkComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProductCatalogLinkComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductCatalogLinkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
