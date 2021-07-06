import { Component, OnInit } from '@angular/core';
import {AuthService} from "../_service/auth/auth.service";

@Component({
  selector: 'app-workplace',
  templateUrl: './workplace.component.html',
  styleUrls: ['./workplace.component.css']
})
export class WorkplaceComponent implements OnInit {

  constructor(public authService: AuthService) { }

  ngOnInit(): void {
  }

}
