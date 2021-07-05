export class Coucab {
  hour: number;
  id: number;
  username: string;
  dontdisturb: boolean;
  fulladdress: string;
  deliverytime: string;
  totalprice: number;
  orderstatus: string;
  packagedescription: string;


  constructor(hour: number, id: number, username: string, dontdisturb: boolean, fulladdress: string, deliverytime: string,
              totalprice: number, orderstatus: string, packagedescription: string) {
    this.hour = hour;
    this.id = id;
    this.username = username;
    this.dontdisturb = dontdisturb;
    this.fulladdress = fulladdress;
    this.deliverytime = deliverytime;
    this.totalprice = totalprice;
    this.orderstatus = orderstatus;
    this.packagedescription = packagedescription;
  }
}
