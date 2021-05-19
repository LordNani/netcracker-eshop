import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../_services/auth.service';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-email',
  templateUrl: './email.component.html',
  styleUrls: ['./email.component.css']
})
export class EmailComponent implements OnInit {
  email: string;
  emailEntered = false;

  constructor(private authService: AuthService) {
  }

  ngOnInit(): void {
  }

  sendEmail(): void {
    this.authService.sendUserEmail(this.email).subscribe(
      res => this.emailEntered = true,
      error => {
        if (error instanceof HttpErrorResponse) {
          if (error.status === 401) {
            console.log(error); // ?????
          }
        }
      }
    );
  }
}
