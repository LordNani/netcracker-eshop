import {Component, Input, OnInit} from '@angular/core';
import {Role} from '../../../_model/role';
import {Router} from '@angular/router';
import {AuthService} from '../../../_service/auth.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  anonymous = Role.ANONYMOUS_USER;
  authorized = Role.AUTHORIZED_USER;
  admin = Role.ADMIN;
  @Input()role = Role.ANONYMOUS_USER;

  constructor(private router: Router,
              private authService: AuthService) { }

  ngOnInit(): void {
  }

}
