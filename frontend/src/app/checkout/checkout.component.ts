import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {MAT_DIALOG_DATA} from '@angular/material/dialog';
import {User} from '../_model/user';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  test = [new Date(), new Date()];
  constructor() { }
  @Inject(MAT_DIALOG_DATA) public data: User;
  form: FormGroup;

  ngOnInit(): void {
    this.form = new FormGroup({
      firstName: new FormControl('', [Validators.required]),
      lastName: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.email]),
      phoneNumber: new FormControl('', Validators.required),
      myDatePicker: new FormControl('', Validators.required)
    });
  }

}
