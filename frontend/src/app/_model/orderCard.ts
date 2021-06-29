export interface OrderCard{
  id: number;
  userId: number;
  courierId: number;
  packageDescription: string;
  orderStatus: string;
  totalPrice: number;
  userName: string;
  deliveryTime: Date;
  fullAddress: string;
  dontDisturb: boolean;
}
