import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {SettingsService} from '../../../_service/settings/settings.service';
import {User} from '../../../_model/user';
import {Router} from '@angular/router';
import {AlertService} from '../../../_service/alert.service';

@Component({
  selector: 'app-edit-settings',
  templateUrl: './edit-settings.component.html',
  styleUrls: ['./edit-settings.component.css']
})
export class EditSettingsComponent implements OnInit {

  constructor(private formBuilder: FormBuilder,
              private settingsService: SettingsService,
              private router: Router,
              private alertService: AlertService) {
  }

  editForm: FormGroup;
  user: User;

  ngOnInit(): void {
    this.getUserInformation();
  }

  private initForm(): void {
    console.log('init form');
    this.editForm = this.formBuilder.group({
      userName: new FormControl(this.user?.userName, [Validators.required,
        Validators.maxLength(28),
        Validators.minLength(1)]),
      userSurname: new FormControl(this.user?.userSurname, [Validators.required,
        Validators.maxLength(28),
        Validators.minLength(1)]),
      userLogin:  new FormControl(this.user?.userLogin, [Validators.required, Validators.email]),
      userAddress: new FormControl(this.user?.userAddress, [Validators.required]),
      userNumber: new FormControl(this.user?.userNumber, [Validators.required,
        Validators.pattern('((\\+38)?\\(?\\d{3}\\)?[\\s\\.-]?(\\d{7}|\\d{3}[\\s\\.-]\\d{2}[\\s\\.-]\\d{2}|\\d{3}-\\d{4}))')])
    });
  }

  getUserInformation(): void {
    this.settingsService.getUserByLogin(localStorage.getItem('login')).subscribe(
      res => {
        this.user = res;
        this.initForm();
      }
    );
  }

  onSubmit(): void {
    if (this.editForm.invalid) {
      return;
    }
    const result = this.editForm.value;
    result.id = this.user.id;

    this.settingsService.updateUser(result).subscribe(
      res => {
        console.log(res);
        this.alertService.success('Information update success.', { keepAfterRouteChange: true });
        this.router.navigate(['/settings']);
      },
      error => {
        console.log(error);
        this.alertService.error(error, { autoClose: false });
      }
    );
  }
}
