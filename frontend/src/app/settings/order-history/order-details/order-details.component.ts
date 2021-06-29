import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {OrderDetailsService} from '../../../_service/order-history/order-details.service';
import {Product} from '../../../_model/product';
import {Author} from '../../../_model/author';
import {CoverType} from '../../../_model/cover-type';
import {Genre} from '../../../_model/genre';
import {Language} from '../../../_model/Language';
import {Publisher} from '../../../_model/Publisher';
import {AuthorService} from '../../../_service/categories/author.service';
import {CoverTypeService} from '../../../_service/categories/cover-type.service';
import {GenreService} from '../../../_service/categories/genre.service';
import {LanguageService} from '../../../_service/categories/language.service';
import {PublisherService} from '../../../_service/categories/publisher.service';
import {ProductService} from '../../../_service/product/product.service';

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css']
})
export class OrderDetailsComponent implements OnInit {

  constructor(private route: ActivatedRoute,
              private router: Router,
              private orderDetailsService: OrderDetailsService,
              private authorService: AuthorService,
              private coverTypeService: CoverTypeService,
              private genreService: GenreService,
              private languageService: LanguageService,
              private publisherService: PublisherService) { }

  orderId: number;
  products: Product[] = [];
  count = [];
  authors: Author[] = [];
  coverTypes: CoverType[] = [];
  genres: Genre[] = [];
  languages: Language[] = [];
  publishers: Publisher[] = [];

  ngOnInit(): void {
    this.orderId = this.orderDetailsService.getOrderId();
    this.getAuthors();
    this.getCoverTypes();
    this.getGenres();
    this.getLanguages();
    this.getPublishers();
    this.getProducts();
    this.getCountOfProduct();
  }

  getAuthors(): any {
    this.authorService.getAllAuthors()
      .subscribe(authors => {
        this.authors = authors;
      });
  }

  getCoverTypes(): void {
    this.coverTypeService.getAllCoverTypes()
      .subscribe(coverTypes => {
        this.coverTypes = coverTypes;
      });
  }

  getGenres(): void {
    this.genreService.getAllGenres()
      .subscribe(genres => {
        this.genres = genres;
      });
  }

  getLanguages(): void {
    this.languageService.getAllLanguages()
      .subscribe(languages => {
        this.languages = languages;
      });
  }

  getPublishers(): void {
    this.publisherService.getAllPublishers()
      .subscribe(publishers => {
        this.publishers = publishers;
      });
  }

  private getProducts(): void {
    this.orderDetailsService.getAllProductInOrder(this.orderId)
      .subscribe(res => {
        this.products = res;
        console.log(res);
      });
  }

  private getCountOfProduct(): void {
    this.orderDetailsService.getCountOfProduct(this.orderId)
      .subscribe(res => {
        this.count = res;
        console.log(res);
      });
  }
}
