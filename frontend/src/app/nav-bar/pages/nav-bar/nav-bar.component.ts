import {Component, Input, OnInit} from '@angular/core';
import {Role} from '../../../_model/role';
import {Router} from '@angular/router';
import {AuthService} from '../../../_service/auth.service';
import {Status} from '../../../_model/status';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

@Input()role: Role;
@Input()status: Status;

  constructor(private router: Router,
              public authService: AuthService) { }


  ngOnInit(): void {
  }

}
