import { Component, OnInit } from '@angular/core';
import {BaseLinkComponent} from '../base-link/base-link.component';
import {LinkService} from '../../services/link.service';


@Component({
  selector: 'app-admin-work-space-link',
  templateUrl: '../base-link/base-link.component.html',
  styleUrls: ['./admin-work-space-link.component.css', '../base-link/base-link.component.css']
})
export class AdminWorkSpaceLinkComponent extends BaseLinkComponent {
  url = 'adminWork';

  constructor(linkService: LinkService) {
    super(linkService);
  }

}





