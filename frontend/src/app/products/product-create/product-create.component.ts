import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Product} from '../../_model/product';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';
import {ProductService} from '../../_service/product.service';
import {AuthorService} from '../../_service/categories/author.service';
import {CoverTypeService} from '../../_service/categories/cover-type.service';
import {GenreService} from '../../_service/categories/genre.service';
import {LanguageService} from '../../_service/categories/language.service';
import {PublisherService} from '../../_service/categories/publisher.service';
import {Author} from '../../_model/author';
import {Genre} from '../../_model/genre';
import {CoverType} from '../../_model/cover-type';
import {Language} from '../../_model/Language';
import {Publisher} from '../../_model/Publisher';

@Component({
  selector: 'app-product-create',
  templateUrl: './product-create.component.html',
  styleUrls: ['./product-create.component.css']
})
export class ProductCreateComponent implements OnInit {

  editForm: FormGroup;
  result: Product;
  authors: Author[] = [];
  genres: Genre[] = [];
  coverTypes: CoverType[] = [];
  languages: Language[] = [];
  publishers: Publisher[] = [];
  private formClose = false;

  constructor(private formBuilder: FormBuilder,
              private dialog: MatDialog,
              private dialogRef: MatDialogRef<ProductCreateComponent>,
              private productService: ProductService,
              private authorService: AuthorService,
              private coverTypeService: CoverTypeService,
              private genreService: GenreService,
              private languageService: LanguageService,
              private publisherService: PublisherService, ) { }

  ngOnInit(): void {
    this.initForm();
    this.getCategories();
  }

  private initForm(): void {
    this.editForm = this.formBuilder.group({
      productName: new FormControl('',
        [Validators.required,
          Validators.maxLength(28),
          Validators.minLength(1)]),
      productDescription: new FormControl('',
        [Validators.required,
          Validators.maxLength(100),
          Validators.minLength(1)]),
      productDiscount: new FormControl('',
        [Validators.required,
          Validators.pattern('^0*(?:[0-9][0-9]?|99)$')]),
      productStatus: new FormControl('', [Validators.required]),
      productPrice: new FormControl('',
        [Validators.required,
          Validators.pattern('^[1-9][0-9]*$')]),
      productAmount: new FormControl('',
        [Validators.required,
          Validators.pattern('^[1-9][0-9]*$')]),
      author: new FormControl('', [Validators.required, Validators.min(0)]),
      publisher: new FormControl('', [Validators.required, Validators.min(0)]),
      genre: new FormControl('', [Validators.required, Validators.min(0)]),
      coverType: new FormControl('', [Validators.required, Validators.min(0)]),
      language: new FormControl('', [Validators.required, Validators.min(0)])
    });
  }

  onClose(): void{
    this.formClose = true;
    this.dialogRef.close('close');
  }

  onSubmit(): void {
    if (this.editForm.invalid || this.formClose) {
      return;
    }
    const result = this.editForm.value;
    result.productPict = 'some url';
    console.log(result);

    this.productService.addProduct(result).subscribe(
      res => {
        console.log(res);
      },
      error => {
        console.log(error);
      }
    );

    console.log('end edit');
    this.onClose();
  }

  getCategories(): void {
    this.authorService.getAuthors().subscribe(
      res => {
        console.log('response now:' + res);
        this.authors = res;
      }
    );

    this.coverTypeService.getCoverTypes().subscribe(
      res => {
        console.log('response now:' + res);
        this.coverTypes = res;
      }
    );

    this.genreService.getCoverTypes().subscribe(
      res => {
        console.log('response now:' + res);
        this.genres = res;
      }
    );

    this.publisherService.getPublishers().subscribe(
      res => {
        console.log('response now:' + res);
        this.publishers = res;
      }
    );

    this.languageService.getLanguages().subscribe(
      res => {
        console.log('response now:' + res);
        this.languages = res;
      }
    );
  }
}
