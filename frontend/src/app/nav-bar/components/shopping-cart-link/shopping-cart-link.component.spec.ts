import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShoppingCartLinkComponent } from './shopping-cart-link.component';

describe('ShoppingCartLinkComponent', () => {
  let component: ShoppingCartLinkComponent;
  let fixture: ComponentFixture<ShoppingCartLinkComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShoppingCartLinkComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShoppingCartLinkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
