import {Component, Inject, Input, OnInit} from '@angular/core';
import {RestService} from '../../_service/rest.service';
import {Coucab} from '../../_model/coucab';
import {CourierDto} from '../../_model/courierDto';
import {Filters} from '../../_model/filters';
import {Observable} from 'rxjs';
import {Product} from '../../_model/product';
import {HttpParams} from '@angular/common/http';
import {catchError} from 'rxjs/operators';
import {ProfileComponent} from '../profile/profile.component';
import {CartdelivComponent} from '../cartdeliv/cartdeliv.component';
import {MAT_DIALOG_DATA, MatDialog} from '@angular/material/dialog';
import {User} from '../../_model/user';

class Couriercabinets {
}

@Component({
  selector: 'app-couriercabinet',
  templateUrl: './coucab.component.html',
  styleUrls: ['./coucab.component.css']
})

export class CoucabComponent implements OnInit {

  courierpackages: Coucab[] = [];
  hour: number;
  id: number;
  username: string;
  dontdisturb: boolean;
  fulladdress: string;
  deliverytime: string;
  totalprice: number;
  orderstatus: boolean;
  packagedescription: string;
  erzacModel: CourierDto;
  theCheckbox: any;

  constructor(public rs: RestService,
              private dialog: MatDialog
  ) {
  }
  @Inject(MAT_DIALOG_DATA) public data: User;
  ngOnInit(): void {
    this.rs.getTask(localStorage.getItem('idUser')).subscribe((response) => {
      this.courierpackages = response;
    });
    console.log(this.orderstatus);
  }

    setNewStatus(ids: number, status: string, date: string ): void {
    switch (status) {
     case 'DELIVERED':
       status = 'INDELIVERY';
       break;
      case 'INDELIVERY':
        status = 'DELIVERED';
    }
    const set = new CourierDto( localStorage.getItem('idUser') , ids , date, status);
    this.rs.setStatus(set).subscribe((response) => {
      this.courierpackages = response;
      console.log(this.orderstatus);
      this.ngOnInit();
    });
  }
  onCreate(): void{
    const dialogRef = this.dialog.open(CartdelivComponent, {
      maxWidth: '80%',
      data: {
        isEdit: false,
        userName: '',
        userSurname: '',
        userLogin: '',
        userNumber: '',
        userRole: '',
        userStatus: 'Active'
      }
    });
  }

  onEdit(ids: number, status: string, date: string, user: User): void{
    console.log(ids);
    const managerData = this.getUserDataById(ids);
    console.log(managerData);
    const dialogRef = this.dialog.open(ProfileComponent, {
      maxWidth: '300px',
      data: managerData
    });
    dialogRef.afterClosed().subscribe(result => {
      // TODO method update call
      this.rs.updateUser(ids, result).subscribe((response) => {
        console.log(response);
        localStorage.setItem('Users', JSON.stringify(response));
      });
      console.log('DialogData = ' + result);
    });

  }

  // tslint:disable-next-line:typedef
  private getUserDataById(id: any) {
    return ;
  }
}
