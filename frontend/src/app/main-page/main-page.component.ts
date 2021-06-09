import { Component, OnInit } from '@angular/core';
import {AuthService} from '../_service/auth.service';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    // this.authService.globalRole = 'ANONYMOUS_USER';
  }

}
