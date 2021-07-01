import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../_service/auth.service';
import {SettingsService} from '../../_service/settings/settings.service';
import {User} from '../../_model/user';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {

  constructor(private settingsService: SettingsService,
              public authService: AuthService) { }

  user: User;

  ngOnInit(): void {
    const login = localStorage.getItem('login');
    console.log(login);

    this.settingsService.getUserByLogin(login).subscribe(
      res => {
        this.user = res;
        console.log(this.user);
        console.log(this.user.userRole === 'USER');
      }
    );
  }

}
