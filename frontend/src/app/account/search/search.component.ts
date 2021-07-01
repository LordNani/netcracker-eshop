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
@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})

export class SearchComponent implements OnInit {
  // managers: Managers[] = [];
  clickedID: number;
  firstName: string;

  constructor(public rs: RestService,
              public dialog: MatDialog) {}
  @Inject(MAT_DIALOG_DATA) public data: User;
  ngOnInit(): void {
    this.getEmployee();
  }
  getEmployee(): void{
    this.rs.getManagers().subscribe((response: User[]) => {
      this.rs.users = response;
    });
  }
  Search(): void{
    if (this.firstName === ''){
      this.ngOnInit();
    }
    else{
      this.rs.managers = this.rs.managers.filter(res => {
        return res.firstName.toLocaleLowerCase().match(this.firstName.toLocaleLowerCase());
      });
    }
  }

  onEdit(id: number, user: User): void{
    console.log(id);
    const managerData = this.getUserDataById(id);
    console.log(managerData);
    managerData.isEdit = true;
    const dialogRef = this.dialog.open(ProfileComponent, {
      maxWidth: '300px',
      data: managerData
    });
    dialogRef.afterClosed().subscribe(result => {
      // TODO method update call
      this.rs.updateUser(id, result).subscribe((response) => {
        console.log(response);
        localStorage.setItem('Users', JSON.stringify(response));
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
      this.rs.addMember(result).subscribe((response) => {
        console.log(response);
        localStorage.setItem('Users', JSON.stringify(response));
      });
      setTimeout(() => {
        this.rs.getManagers().subscribe((response: User[]) => {
          this.rs.users = response;
          console.log(response);
        });
      }, 1000);
      console.log('DialogData = ' + result);
    });
  }
  onDelete(id: number): void{
    console.log(id);
    this.rs.deleteUser(id).subscribe( response => {
      this.rs.users = this.rs.users.filter(item => item.id !== id);
      console.log(this.rs.getManagers());
    });
    setTimeout(() => {
      this.rs.getManagers().subscribe((response: User[]) => {
        this.rs.users = response;
        console.log(response);
      });
    }, 1000);
    // console.log(response);
  }
  getID(id: number): number{
    return this.clickedID = id;

  }
  private getUserDataById(id: number): User {
    return this.rs.users.find(x => x.id === id);
  }


  getAllManager(): void {
    this.rs.getManager().subscribe((response) => {
      this.rs.managers = response;
    });
  }

  getAllCoriers(): void {
    this.rs.getCorier().subscribe((response) => {
      this.rs.managers = response;
    });
  }

  getOnDuty(): void {
    this.rs.getOnDutyNow().subscribe((response) => {
      this.rs.managers = response;
    });
  }
}
