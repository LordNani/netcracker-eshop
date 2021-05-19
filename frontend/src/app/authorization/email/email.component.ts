import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../_services/auth.service';
import {HttpErrorResponse} from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-email',
  templateUrl: './email.component.html',
  styleUrls: ['./email.component.css']
})
export class EmailComponent implements OnInit {
  userEmail: string;

  constructor(private authService: AuthService,
              private router: Router) {
  }

  ngOnInit(): void {
  }

  sendEmail(): void {
    this.authService.sendUserEmail(this.userEmail).subscribe(
      res => {
      },
      error => {
        if (error instanceof HttpErrorResponse) {
          if (error.status === 401) {
            console.log(error); // ?????
          }
        }
      }
    );
    this.router.navigate(['/main']);
  }
}
