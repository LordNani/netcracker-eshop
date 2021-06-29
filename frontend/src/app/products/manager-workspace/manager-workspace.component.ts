import { Component, OnInit } from '@angular/core';
import {CategoriesPartComponent} from '../categories-part/categories-part.component';
import {ProductService} from '../../_service/product/product.service';
import {Product} from '../../_model/product';
import {Author} from '../../_model/author';
import {AuthorService} from '../../_service/categories/author.service';
import {CoverTypeService} from '../../_service/categories/cover-type.service';
import {GenreService} from '../../_service/categories/genre.service';
import {LanguageService} from '../../_service/categories/language.service';
import {PublisherService} from '../../_service/categories/publisher.service';
import {CoverType} from '../../_model/cover-type';
import {Genre} from '../../_model/genre';
import {Language} from '../../_model/Language';
import {Publisher} from '../../_model/Publisher';
import {MatDialog, MatDialogConfig} from '@angular/material/dialog';
import {ProductCreateComponent} from '../product-create/product-create.component';
import {PageEvent} from '@angular/material/paginator';
import { Filters } from 'src/app/_model/filters';

@Component({
  selector: 'app-manager-workspace',
  templateUrl: './manager-workspace.component.html',
  styleUrls: ['./manager-workspace.component.css']
})
export class ManagerWorkspaceComponent implements OnInit {

  constructor(private categoriesPartComponent: CategoriesPartComponent,
              private productService: ProductService,
              private authorService: AuthorService,
              private coverTypeService: CoverTypeService,
              private genreService: GenreService,
              private languageService: LanguageService,
              private publisherService: PublisherService,
              private dialog: MatDialog) { }

  products: Product[] = [];
  authors: Author[] = [];
  coverTypes: CoverType[] = [];
  genres: Genre[] = [];
  languages: Language[] = [];
  publishers: Publisher[] = [];
  size = 6;
  amountOfProducts: number;
  page = 1;

  searchValue: string;
  filtersValue: Filters;
  orderValue: string;

  ngOnInit(): void {
    this.getAllProducts();
    this.searchValue = "";
    this.filtersValue = {author: [], coverType: [], genre: [], language: [], publisher: []} as Filters;
    this.orderValue = '';
    this.getProductsCount();
    this.getAuthors();
    this.getCoverTypes();
    this.getGenres();
    this.getLanguages();
    this.getPublishers();

  }

  getAllProducts(): void {
    this.productService.getAllProducts(this.page, this.size)
      .subscribe(products => {
        // console.log(products);
        this.products = products;
      }, error => console.log(error));

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

  update($event: string): void {
    this.getAllProducts();
    this.getProductsCount();
  }

  getSearchedProducts(value: string): void {
    this.productService.searchProducts(value)
      .subscribe(products => {
        console.log('inside subscribe');
        console.log(products);
        this.products = products;
      });
  }

  onCreate(): void {
    this.getAmountOfProducts();
    console.log(this.getAmountOfProducts());
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '40%';
    this.dialog.open(ProductCreateComponent, dialogConfig);
    this.dialog.afterAllClosed.subscribe(() => {
      this.getAllProducts();
      this.getProductsCount();
    });
  }

  getServerData(event?: PageEvent): PageEvent{
    this.getAllProducts();
    return event;
  }

  private getProductsCount(): void {
    this.productService.getProductsCount(this.searchValue, this.orderValue, this.filtersValue).subscribe(
      res => {
        this.amountOfProducts = res;
      }
    );
  }

  onPageChange(currentPage: number): void {
    this.page = currentPage;
    this.getAllProducts();
  }


  cancelFilters(): void{
    window.location.reload();
  }

  getOrderedProducts(value: string){
    this.orderValue = value;
    this.getSearchedOrderedFilteredProducts();
  }

  getFilteredProducts(filters: Filters): void{
    this.filtersValue = filters;
    this.getSearchedOrderedFilteredProducts();
  }

  getSearchedOrderedFilteredProducts(): void{
    this.productService.searchOrderFilterProducts(this.page, this.size, this.searchValue, this.orderValue, this.filtersValue)
      .subscribe(products => {
        this.products = products;
      });
    this.getAmountOfProducts();
  }

  getAmountOfProducts(){
    this.productService.getProductsCount(this.searchValue, this.orderValue, this.filtersValue)
      .subscribe(numb => {
        this.amountOfProducts = numb;
      });
  }
}
