import { Component } from '@angular/core';
import {BaseLinkComponent} from '../base-link/base-link.component';
import {LinkService} from '../../services/link.service';

@Component({
  selector: 'app-product-catalog-link',
  templateUrl: '../base-link/base-link.component.html',
  styleUrls: ['./product-catalog-link.component.css', '../base-link/base-link.component.css']
})
export class ProductCatalogLinkComponent extends BaseLinkComponent{
  url = 'product-catalog';
  constructor(linkService: LinkService) {
    super(linkService);
  }

}
