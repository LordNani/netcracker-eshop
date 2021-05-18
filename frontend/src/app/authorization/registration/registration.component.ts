import { Component, OnInit } from '@angular/core';
import {User} from '../../_model/user';
import {Router} from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  registerUserData = new User('', '', '', '');
  constructor(private router: Router) { }
  ngOnInit(): void {
  }
  register(): void{
    console.log(this.registerUserData);
    this.router.navigate(['/main']);
  }
}
