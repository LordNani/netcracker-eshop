import { Component, OnInit } from '@angular/core';
import {CategoriesPartComponent} from '../../products/categories-part/categories-part.component';
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
import {ProductCreateComponent} from '../../products/product-create/product-create.component';
import {PageEvent} from '@angular/material/paginator';
import {Filters} from '../../_model/filters';

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
  size = 5;
  amountOfProducts: number;
  page = 1;
  private searchValue: string;
  private orderValue: string;
  private filtersValue: Filters;
  isActive: boolean;

  ngOnInit(): void {
    this.getAllProducts();
    this.searchValue = '';
    this.filtersValue = {author: [], coverType: [], genre: [], language: [], publisher: []} as Filters;
    this.orderValue = '';
    this.isActive = false;

    this.getProductsCount();

    this.authors = this.authorService.getAuthors();
    this.coverTypes = this.coverTypeService.getCoverTypes();
    this.genres = this.genreService.getGenres();
    this.languages = this.languageService.getLanguages();
    this.publishers = this.publisherService.getPublishers();
  }

  getAllProducts(): void {
    this.productService.getAllProducts(this.page, this.size)
      .subscribe(products => {
        console.log(products);
        this.products = products;
      }, error => console.log(error));

  }

  getOrderedProducts(value: string): void{
    this.orderValue = value;
    this.getSearchedOrderedFilteredProducts();
  }

  getSearchedProducts(value: string): void{
    if (value !== ''){
      this.searchValue = value;
      this.getSearchedOrderedFilteredProducts();
    }
    else { this.searchValue = ''; }
  }

  getFilteredProducts(filters: Filters): void{
    this.filtersValue = filters;
    this.getSearchedOrderedFilteredProducts();
  }

  update($event: string): void {
    this.getSearchedOrderedFilteredProducts();
    this.getProductsCount();
  }

  onCreate(): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '50%';
    this.dialog.open(ProductCreateComponent, dialogConfig);
    this.dialog.afterAllClosed.subscribe(() => {
      this.update('update');
    });
  }
  private getProductsCount(): void {
    this.productService.getProductsCount(this.searchValue, this.orderValue, this.filtersValue, this.isActive).subscribe(
      res => {
        this.amountOfProducts = res;
      }
    );
  }

  onPageChange(currentPage: number): void {
    this.page = currentPage;
    this.getAllProducts();
  }

  getSearchedOrderedFilteredProducts(): void{
    this.productService.searchOrderFilterProducts(this.page, this.size, this.searchValue, this.orderValue, this.filtersValue, this.isActive)
      .subscribe(products => {
        this.products = products;
        console.log(this.filtersValue);
      });
    this.getProductsCount();
  }

  cancelFilters(): void{
    window.location.reload();
  }
}
