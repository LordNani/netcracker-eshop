import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {typesOfCategories} from '../../_model/typesOfCategories';
import {GenreService} from '../../_service/categories/genre.service';
import {Genre} from '../../_model/genre';
import {AuthorService} from '../../_service/categories/author.service';
import {CoverTypeService} from '../../_service/categories/cover-type.service';
import {LanguageService} from '../../_service/categories/language.service';
import {PublisherService} from '../../_service/categories/publisher.service';
import {Author} from '../../_model/author';
import {CoverType} from '../../_model/cover-type';
import {Language} from '../../_model/Language';
import {Publisher} from '../../_model/Publisher';
import {Filters} from '../../_model/filters';

@Component({
  selector: 'app-categories-part',
  templateUrl: './categories-part.component.html',
  styleUrls: ['./categories-part.component.css']
})
export class CategoriesPartComponent implements OnInit {

  typesOfCategories = typesOfCategories;
  authors: Author[] = [];
  coverTypes: CoverType[] = [];
  genres: Genre[] = [];
  languages: Language[] = [];
  publishers: Publisher[] = [];
  authorsCheckedSet;
  coverTypesCheckedSet;
  genresCheckedSet;
  languagesCheckedSet;
  publishersCheckedSet;
  filters: Filters;
  @Output() filterValue = new EventEmitter<Filters>();


  constructor(
    private authorService: AuthorService,
    private coverTypeService: CoverTypeService,
    private genreService: GenreService,
    private languageService: LanguageService,
    private publisherService: PublisherService,
  ) {
  }

  ngOnInit(): void {
    this.publishers = this.publisherService.getPublishers();
    this.authors = this.authorService.getAuthors();
    this.coverTypes = this.coverTypeService.getCoverTypes();
    this.genres = this.genreService.getGenres();
    this.languages = this.languageService.getLanguages();
    this.authorsCheckedSet = new Set();
    this.coverTypesCheckedSet = new Set();
    this.genresCheckedSet = new Set();
    this.languagesCheckedSet = new Set();
    this.publishersCheckedSet = new Set();
    this.filters = {author: [], coverType: [], genre: [], language: [], publisher: []} as Filters;
  }

  filter(): void {
    this.filters.author = [];
    this.filters.coverType = [];
    this.filters.genre = [];
    this.filters.language = [];
    this.filters.publisher = [];

    for (const currentNumber of this.authorsCheckedSet) {
      this.filters.author.push(currentNumber);
    }

    for (const currentNumber of this.coverTypesCheckedSet) {
      this.filters.coverType.push(currentNumber);
    }

    for (const currentNumber of this.genresCheckedSet) {
      this.filters.genre.push(currentNumber);
    }

    for (const currentNumber of this.languagesCheckedSet) {
      this.filters.language.push(currentNumber);
    }

    for (const currentNumber of this.publishersCheckedSet) {
      this.filters.publisher.push(currentNumber);
    }

    this.filterValue.emit(this.filters);
  }

  authorChecked(id: number): void {
    if (this.authorsCheckedSet.has(id)) {
      this.authorsCheckedSet.delete(id);
    } else {
      this.authorsCheckedSet.add(id);
    }
  }

  coverTypeChecked(id: number): void {
    if (this.coverTypesCheckedSet.has(id)) {
      this.coverTypesCheckedSet.delete(id);
    } else {
      this.coverTypesCheckedSet.add(id);
    }
  }

  genreChecked(id: number): void {
    if (this.genresCheckedSet.has(id)) {
      this.genresCheckedSet.delete(id);
    } else {
      this.genresCheckedSet.add(id);
    }
  }

  languageChecked(id: number): void {
    if (this.languagesCheckedSet.has(id)) {
      this.languagesCheckedSet.delete(id);
    } else {
      this.languagesCheckedSet.add(id);
    }
  }

  publisherChecked(id: number): void {
    if (this.publishersCheckedSet.has(id)) {
      this.publishersCheckedSet.delete(id);
    } else {
      this.publishersCheckedSet.add(id);
    }
  }

  create(): void {

  }
}
