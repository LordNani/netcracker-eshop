// import { Component, OnInit } from '@angular/core';
// import {Managers} from '../../_model/managers';
// import {RestService} from '../../_service/rest.service';
// import {MatDialog, MatDialogConfig} from '@angular/material/dialog';
// import {ProfileComponent} from '../profile/profile.component';
// import {MatDialogRef} from '@angular/material/dialog';
//
// // import {EditComponent} from "../edit/edit.component";
//
// @Component({
//   selector: 'app-search',
//   templateUrl: './search.component.html',
//   styleUrls: ['./search.component.css']
// })
// export class SearchComponent implements OnInit {
//   managers: Managers[] = [];
//   clickedID: number;
//   firstName: string;
//   constructor(public rs: RestService,
//               private dialog: MatDialog) {}
//
//   ngOnInit(): void {
//     this.rs.getManagers().subscribe((response) => {
//       this.managers = response;
//     });
//   }
//   getEmployee(): void{
//     this.rs.getManagers().subscribe((response) => {
//       this.managers = response;
//     });
//   }
//   Search(): void{
//     if (this.firstName === ''){
//       this.ngOnInit();
//     }
//     else{
//       this.managers = this.managers.filter(res => {
//         return res.firstName.toLocaleLowerCase().match(this.firstName.toLocaleLowerCase());
//       });
//     }
//   }
//
//   // onEdit(): void{
//   // this.dialog.open(EditComponent);
//   // }
//   onCreate(): void{
//     const dialogConfig = new MatDialogConfig();
//     dialogConfig.disableClose = true;
//     dialogConfig.autoFocus = true;
//     dialogConfig.width = '60%';
//     this.dialog.open(ProfileComponent, dialogConfig);
//   }
//   // deleteContact(id){
//   //   id = this.managers[].firstName;
//   //   this.service.deleteUser(id).subscribe(
//   //     ()=> console.log(`Employee with Id = ${this.managers.id}deleted`),
//   //     (err) => console.log(err)
//   //   );
//   // }
//
//   onDelete(id: number): void{
//    console.log(id);
//    this.rs.deleteUser(id) .subscribe(response => {
//      this.managers = this.managers.filter(item => item.id !== id);
//    });
//   }
//   getID(id: number): number{
//     return this.clickedID = id;
//
//   }
// }
