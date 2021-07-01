import {Component, Input, OnInit} from '@angular/core';
import {OrderCard} from '../../../_model/orderCard';
import {OrderHistoryService} from '../../../_service/order-history/order-history.service';
import {User} from '../../../_model/user';
import {Router} from '@angular/router';
import {OrderDetailsService} from '../../../_service/order-history/order-details.service';

@Component({
  selector: 'app-order-history',
  templateUrl: './order-history.component.html',
  styleUrls: ['./order-history.component.css']
})
export class OrderHistoryComponent implements OnInit {

  constructor(private orderHistoryService: OrderHistoryService,
              private router: Router,
              private orderDetailsService: OrderDetailsService) { }

  @Input()
  user?: User;
  orderCards: OrderCard[] = [];
  size = 5;
  amountOfProducts: number;
  page = 1;

  ngOnInit(): void {
    this.getOrders();
    this.getOrderCount();
  }

  getOrders(): void {
    this.orderHistoryService.getAllOrderById(this.user.id, this.page, this.size).subscribe(
      res => {
        console.log(res);
        this.orderCards = res;
      }
    );
  }

  viewDetails(id: number): void {
    this.orderDetailsService.setOrderId(id);
    this.router.navigate(['/settings/order-details']);
  }

  onPageChange(currentPage: number): void {
    this.page = currentPage;
    this.ngOnInit();
  }

  private getOrderCount(): void {
    this.orderHistoryService.getOrderCount(this.user.id).subscribe(
      res => {
        this.amountOfProducts = res;
      }
    );
  }
}
