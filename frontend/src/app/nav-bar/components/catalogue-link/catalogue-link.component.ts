import { Component } from '@angular/core';
import {LinkService} from '../../services/link.service';
import {BaseLinkComponent} from '../base-link/base-link.component';

@Component({
  selector: 'app-catalogue-link',
  templateUrl: '../base-link/base-link.component.html',
  styleUrls: ['./catalogue-link.component.css', '../base-link/base-link.component.css']
})
export class CatalogueLinkComponent extends BaseLinkComponent {
  url = 'catalogue';
  constructor(linkService: LinkService) {
    super(linkService);
  }

}
