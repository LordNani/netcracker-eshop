import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Role} from './_model/role';
import {AuthService} from './_service/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'frontend';
  loggedIn = false;
  role: Role;
  constructor(private router: Router,
              private authService: AuthService) {
  }
  login(): void{
    this.loggedIn = true;
  }
  logout(): void{
    this.loggedIn = false;
    this.router.navigate(['/main']);
  }

  ngOnInit(): void {
    this.authService.getUserRole().subscribe(
      res => this.role = res.role,
      error => this.role = Role.ADMIN
    );
  }
}
