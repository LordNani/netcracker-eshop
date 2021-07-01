export class CourierDto {
  courierId: string;
  cartId: number;
  date: string;


  constructor(courierId: string, cartId: number, date: string) {
    this.courierId = courierId;
    this.cartId = cartId;
    this.date = date;
  }
}
