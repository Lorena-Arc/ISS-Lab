import {Component, Input, OnInit} from '@angular/core';
import {BookModel, BookStatus} from "../models/book.model";

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.scss']
})
export class BookComponent implements OnInit {

  @Input() book: BookModel = {
    id: '',
    titlu: '',
    autor: '',
    statusCarte: BookStatus.INDISPONIBIL
  }

  STATUS_DISP = BookStatus.DISPONIBIL;
  STATUS_INDISP = BookStatus.INDISPONIBIL;

  constructor() { }

  ngOnInit(): void {
  }

}
