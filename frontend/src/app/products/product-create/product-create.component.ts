import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Product} from '../../_model/product';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';
import {ProductService} from '../../_service/product/product.service';
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
import {ValidationMessages} from '../../_model/labels/validation.messages';

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

  nameErrorMessage = ValidationMessages.productName;
  descriptionErrorMessage = ValidationMessages.productDescription;
  discountErrorMessage = ValidationMessages.productDiscount;
  priceErrorMessage = ValidationMessages.productPrice;
  amountErrorMessage = ValidationMessages.productAmount;
  authorErrorMessage = ValidationMessages.productAuthor;
  publisherErrorMessage = ValidationMessages.productPublisher;
  genreErrorMessage = ValidationMessages.productGenre;
  coverTypeErrorMessage = ValidationMessages.productCoverType;
  languageErrorMessage = ValidationMessages.productLanguage;
  pictureErrorMessage = ValidationMessages.productProductPicture;

  constructor(private formBuilder: FormBuilder,
              private dialog: MatDialog,
              private dialogRef: MatDialogRef<ProductCreateComponent>,
              private productService: ProductService,
              private authorService: AuthorService,
              private coverTypeService: CoverTypeService,
              private genreService: GenreService,
              private languageService: LanguageService,
              private publisherService: PublisherService ) { }

  ngOnInit(): void {
    this.initForm();
    this.coverTypes = this.coverTypeService.getCoverTypes();
    this.authors = this.authorService.getAuthors();
    this.genres = this.genreService.getGenres();
    this.languages = this.languageService.getLanguages();
    this.publishers = this.publisherService.getPublishers();
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
          Validators.pattern('^[0-9][0-9]*$')]),
      author: new FormControl('', [Validators.required, Validators.min(0)]),
      publisher: new FormControl('', [Validators.required, Validators.min(0)]),
      genre: new FormControl('', [Validators.required, Validators.min(0)]),
      coverType: new FormControl('', [Validators.required, Validators.min(0)]),
      language: new FormControl('', [Validators.required, Validators.min(0)]),
      productPict: new FormControl('', [Validators.required,
        Validators.maxLength(100),
        Validators.minLength(1)])
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
    console.log(result);

    this.productService.addProduct(result).subscribe(
      res => {
        console.log(res);
      },
      error => {
        console.log(error);
      }
    );

    this.onClose();
  }

}
