import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BidComponent } from './bid/bid.component';
import { AuctionListComponent } from './auction-list/auction-list.component';
import { AuctionComponent } from './auction/auction.component';
import { AppRoutingModule } from '../app-routing.module';

@NgModule({
  declarations: [
    AuctionComponent,
    AuctionListComponent,
    BidComponent,
  ],
  imports: [
    CommonModule,
    AppRoutingModule
  ]
})
export class AuctionsModule { }
