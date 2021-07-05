export class CourierDto {
  courierId: string;
  cartId: number;
  date: string;
  status: string;


  constructor(courierId: string, cartId: number, date: string, status: string) {
    this.courierId = courierId;
    this.cartId = cartId;
    this.date = date;
    this.status = status;
  }
}
