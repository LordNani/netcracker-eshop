import {
  Component, Input, OnInit, ViewChild,
  ViewContainerRef,
  ComponentFactoryResolver,
  ComponentRef,
  ComponentFactory, Inject
} from '@angular/core';
import {Managers} from '../../_model/managers';
import {RestService} from '../../_service/rest.service';
import {MAT_DIALOG_DATA, MatDialog, MatDialogConfig} from '@angular/material/dialog';
import {ProfileComponent} from '../profile/profile.component';
import {MatDialogRef} from '@angular/material/dialog';
import {User} from '../../_model/user';
import {Admin} from '../../_model/admin';
import {NgForm} from '@angular/forms';
import {Subscription} from "rxjs";
@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})

export class SearchComponent implements OnInit {
   managers: Managers[] = [];
  clickedID: number;
  firstName: string;
  subscription: Subscription;
  admin = new Admin();
  constructor(public rs: RestService,
              public dialog: MatDialog,
  ) {}
  @Inject(MAT_DIALOG_DATA) public data: User;

  ngOnInit(): void {
    this.admin.NS = '';
    this.admin.job = 'ALL';
    this.admin.status = 'ALL';
    this.getEmployee();
  }
//   ngOnDestroy() {
//     this.subscription.unsubscribe()
//   }
// }

  getEmployee(): void{
    this.rs.getManagers().subscribe((response: User[]) => {
      this.rs.users = response;
    });
  }

  onEdit(id: number): void{
    console.log(id);
    const managerData = this.getUserDataById(id);
    console.log(managerData);
    managerData.isEdit = true;
    const dialogRef = this.dialog.open(ProfileComponent, {
      maxWidth: '300px',
      data: {
        userName: managerData.userName,
        userSurname: managerData.userSurname ,
        userLogin: managerData.userLogin,
        userNumber: managerData.userNumber,
        userRole: managerData.userRole,
        userStatus: managerData.userStatus,
        id: managerData.id
      }
    });
    dialogRef.afterClosed().subscribe(result => {
      // TODO method update call
      this.rs.updateUser(id, result).subscribe((response) => {
        console.log(response);
        localStorage.setItem('Users', JSON.stringify(response));
        this.ngOnInit();
      });
      console.log('DialogData = ' + result);
    });

  }
  onCreate(): void{
    const dialogRef = this.dialog.open(ProfileComponent, {
      maxWidth: '300px',
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
    dialogRef.afterClosed().subscribe(result => {
      // TODO method creation call
      console.log(result);
      this.rs.addMember(result).subscribe((response) => {
        console.log(response);
        localStorage.setItem('Users', JSON.stringify(response));
        this.rs.users.push(response);
        });
      });
  }
  onDelete(id: number): void{
    console.log(id);
    this.rs.deleteUser(id).subscribe( response => {
      this.rs.users = this.rs.users.filter(item => item.id !== id);
      console.log(this.rs.getManagers());
    });
  }
  getID(id: number): number{
    return this.clickedID = id;

  }
  private getUserDataById(id: number): User {
    return this.rs.users.find(x => x.id === id);
  }
  // @ts-ignore
  onFormSubmit(form: NgForm): void{
    if (form.controls['job'].value === 'ALL') {
      this.admin.job = 'COURIER,MANAGER';
    }else {
      this.admin.job = form.controls['job'].value + ', ';
    }

    if (form.controls['status'].value === 'ALL'){
      this.admin.status = 'ACTIVE,INACTIVE,TERMINATED';
    }
    else {
      this.admin.status = form.controls['status'].value + ', , ';
    }

    if ( form.controls['NS'].value == '')  {
      this.admin.NS = '';
  }else { this.admin.NS = form.controls['NS'].value ;
    }

    this.rs.getFromUsers(this.admin).subscribe((response: User[]) => {
      this.rs.users = response;
      console.log(response);
    });

  }
}
