import { Component, OnInit } from '@angular/core';
import {BaseLinkComponent} from '../base-link/base-link.component';
import {LinkService} from '../../services/link.service';

@Component({
  selector: 'app-workspace-link',
  templateUrl: './workspace-link.component.html',
  styleUrls: ['./workspace-link.component.css', '../base-link/base-link.component.css']
})
export class WorkspaceLinkComponent extends BaseLinkComponent {

  url = 'workplace';
  constructor(linkService: LinkService) {
    super(linkService);
  }

}
