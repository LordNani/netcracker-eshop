import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from './_service/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'frontend';
  loggedIn = false;
  role = this.authService.role;
  status = this.authService.status;
  constructor(private router: Router,
              private authService: AuthService) {
  }
  login(): void{
    this.loggedIn = true;
  }
  logout(): void{
    this.loggedIn = false;
    console.log('logout');
    this.router.navigate(['/main']);
  }

  ngOnInit(): void {

  }
}
