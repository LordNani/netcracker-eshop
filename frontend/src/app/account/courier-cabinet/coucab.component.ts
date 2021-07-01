import {Component, Input, OnInit} from '@angular/core';
import {RestService} from '../../_service/rest.service';
import {Coucab} from '../../_model/coucab';
import {CourierDto} from '../../_model/courierDto';
import {Filters} from '../../_model/filters';
import {Observable} from 'rxjs';
import {Product} from '../../_model/product';
import {HttpParams} from '@angular/common/http';
import {catchError} from 'rxjs/operators';

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
              // private dialog: MatDialog
  ) {
  }

  ngOnInit(): void {
    this.rs.getTask(localStorage.getItem('idUser')).subscribe((response) => {
      this.courierpackages = response;
    });
    console.log(this.orderstatus);
  }

  // getByCourierID(id: number) {
  //     return this.user.id = id;
  //  }
  setNewStatus(ids: number, cartid: boolean, date: string): void {
    const set = new CourierDto( localStorage.getItem('idUser') , ids , date);
    this.rs.setStatus(set).subscribe((response) => {
      this.courierpackages = response;
      console.log(this.orderstatus);
      this.ngOnInit();
    });
  }

}
