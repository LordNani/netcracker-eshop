import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {MatDialog, MatDialogConfig, MatDialogRef} from '@angular/material/dialog';
import {Managers} from '../../_model/managers';
import {RestService} from '../../_service/rest.service';
import {HttpClient} from '@angular/common/http';
import { Model, SurveyNG, JsonObject } from 'survey-angular';

const surveyJSON = {};
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  form: FormGroup;
  emailRegx = /^(([^<>+()\[\]\\.,;:\s@"-#$%&=]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,3}))$/;
  managers: Managers[] = [];
  newPerson = new FormGroup({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    email: new FormControl(''),
    phoneNumber: new FormControl(''),
    role: new FormControl('')
  });
  constructor(
    private formBuilder: FormBuilder,
    private dialog: MatDialog,
    private dialogRef: MatDialogRef<ProfileComponent>,
    private service: RestService,
    private http: HttpClient){}


  ngOnInit(): void {
    this.form = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', Validators.required],
      phoneNumber: ['', Validators.required],
      role: ['', Validators.required]
     });
  }

  // submit(): void {
  //
  //   if (!this.form.valid) {
  //     return;
  //   }
  //   console.log(this.form.value);
  // }

  Submit(): void{
    // console.log('Hello');
    // console.log(this.form.valid);
    const controls = this.form.controls;

    /** Проверяем форму на валидность */
    if (this.form.invalid) {
      Object.keys(controls)
        .forEach(controlName => controls[controlName].markAsTouched());

      return;
    }

    /** TODO: Обработка данных формы */
    console.warn(this.form.value);
    this.service.addMember(this.form.value).subscribe((result) =>{
    console.warn(result);
    this.onClose();
    });
  }
  onClose(): void{
    this.dialogRef.close();
  }
  }





