export class Product{
  constructor(id: number,
              productCategory: string,
              productName: string,
              productAmount: number,
              productPrice: number,
              productDiscount: number,
              productDate: Date,
              productDescription: string,
              productStatus: boolean) {
    this.id = id;
    this.productCategory = productCategory;
    this.productName = productName;
    this.productAmount = productAmount;
    this.productPrice = productPrice;
    this.productDiscount = productDiscount;
    this.productDate = productDate;
    this.productDescription = productDescription;
    this.productStatus = productStatus;
  }

  id: number;
  productCategory: string;
  productName: string;
  productAmount: number;
  productPrice: number;
  productDiscount: number;
  productDate: Date;
  productDescription: string;
  productStatus: boolean;
}
