import { Component} from '@angular/core';
import {BaseLinkComponent} from '../base-link/base-link.component';
import {LinkService} from '../../services/link.service';

@Component({
  selector: 'app-shopping-cart-link',
  templateUrl: '../base-link/base-link.component.html',
  styleUrls: ['./shopping-cart-link.component.css', '../base-link/base-link.component.css']
})
export class ShoppingCartLinkComponent extends BaseLinkComponent{
  url = 'basket';
  constructor(linkService: LinkService) {
    super(linkService);
  }
}
