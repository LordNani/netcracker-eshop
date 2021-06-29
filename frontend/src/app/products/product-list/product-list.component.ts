import {Component, OnInit, Pipe} from '@angular/core';
import {ProductCategory} from "../../_model/productCategory";
import {Product} from "../../_model/product";
import {ProductService} from "../../_service/product/product.service";
import {typesOfCategories} from "../../_model/typesOfCategories";
import {Filters} from "../../_model/filters";
import { Author } from 'src/app/_model/author';
import { CoverType } from 'src/app/_model/cover-type';
import { Genre } from 'src/app/_model/genre';
import { Publisher } from 'src/app/_model/Publisher';
import { Language } from 'src/app/_model/Language';
import { AuthorService } from 'src/app/_service/categories/author.service';
import { CoverTypeService } from 'src/app/_service/categories/cover-type.service';
import { PublisherService } from 'src/app/_service/categories/publisher.service';
import { GenreService } from 'src/app/_service/categories/genre.service';
import { LanguageService } from 'src/app/_service/categories/language.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})


export class ProductListComponent implements OnInit {

  page: number = 1;
  size: number = 6;
  amountOfProducts: number;
  currentProducts: Product[] = [];
  authors: Author[] = [];
  coverTypes: CoverType[] = [];
  genres: Genre[] = [];
  languages: Language[] = [];
  publishers: Publisher[] = [];
  searchValue: string;
  filtersValue: Filters;
  orderValue: string;

  // @ts-ignore
  constructor(
    private productService: ProductService,
    private authorService: AuthorService,
    private coverTypeService: CoverTypeService,
    private genreService: GenreService,
    private languageService: LanguageService,
    private publisherService: PublisherService,
  ) {
  }

  ngOnInit(): void {
    this.getProducts();
    this.searchValue = "";
    this.filtersValue = {author: [], coverType: [], genre: [], language: [], publisher: []} as Filters;
    this.orderValue = '';
    this.getAmountOfProducts();
    this.getAuthors();
    this.getCoverTypes();
    this.getGenres();
    this.getLanguages();
    this.getPublishers();
  }

  getProducts(): void {
    this.productService.getAllProducts(this.page, this.size)
      .subscribe(products => {
        this.currentProducts = products;
      });
  }

  getOrderedProducts(value: string){
    this.orderValue = value;
    this.getSearchedOrderedFilteredProducts();
  }

  getSearchedProducts(value: string){
    if (value != ''){
      this.searchValue = value;
      this.getSearchedOrderedFilteredProducts();
    }
    else this.searchValue = '';
  }

  getFilteredProducts(filters: Filters): void{
    this.filtersValue = filters;
    this.getSearchedOrderedFilteredProducts();
  }

  getSearchedOrderedFilteredProducts(): void{
    this.productService.searchOrderFilterProducts(this.page, this.size, this.searchValue, this.orderValue, this.filtersValue)
      .subscribe(products => {
        this.currentProducts = products;
      });
    this.getAmountOfProducts();
  }

  onPageChange(currentPage: number): void{
    this.page = currentPage;
    this.getSearchedOrderedFilteredProducts();
  }

  cancelFilters(): void{
    window.location.reload();
  }

  getAmountOfProducts(){
    this.productService.getProductsCount(this.searchValue, this.orderValue, this.filtersValue)
      .subscribe(numb => {
        this.amountOfProducts = numb;
      });
  }

  getAuthors(): void {
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
}
