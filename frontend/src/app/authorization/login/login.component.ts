import { Component, OnInit } from '@angular/core';
import {User} from '../../_model/user';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginUserData = new User('', '', '', '');
  constructor(private router: Router) { }
  ngOnInit(): void {
  }

  login(): void {
    console.log(this.loginUserData);
    this.router.navigate(['/main']);
  }

}
