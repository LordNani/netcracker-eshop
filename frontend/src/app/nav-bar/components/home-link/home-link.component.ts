import {Component} from '@angular/core';
import {LinkService} from '../../services/link.service';
import {BaseLinkComponent} from '../base-link/base-link.component';

@Component({
  selector: 'app-home-link',
  templateUrl: '../base-link/base-link.component.html',
  styleUrls: ['./home-link.component.css', '../base-link/base-link.component.css']
})
export class HomeLinkComponent extends BaseLinkComponent{
  url = 'home';
  constructor(linkService: LinkService) {
    super(linkService);
  }
}

