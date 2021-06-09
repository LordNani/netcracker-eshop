import { Component, OnInit } from '@angular/core';
import {LinkService} from '../../services/link.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-base-link',
  templateUrl: './base-link.component.html',
  styleUrls: ['./base-link.component.css']
})
export class BaseLinkComponent implements OnInit {
  url: string;
  link: string;
  content: string;
  image: string;
  constructor(protected linkService: LinkService) { }

  ngOnInit(): void {
    this.linkService.getLink(this.url).subscribe(
      (data: any) => {
        console.log(data);
        this.link = data.link;
        this.content = data.content;
        this.image = data.image;
      },
      error => console.log(error)
    );
  }

}
