import {Component, Input, OnInit} from '@angular/core';
import {BookModel} from "../../models/book.model";
import {SubscriberService} from "../../services/subscriber.service";
import {NotificationService} from "../../services/notification.service";

@Component({
  selector: 'app-book-subscriber',
  templateUrl: './book-subscriber.component.html',
  styleUrls: ['./book-subscriber.component.scss']
})
export class BookSubscriberComponent implements OnInit {

  @Input()
  book: BookModel | undefined;
  @Input()
  isCos: boolean = false;

  constructor(private service: SubscriberService, private notifyService: NotificationService) {
  }

  ngOnInit(): void {
  }

  borrowBook() {
    if (this.book) {
      this.service.borrowBook(this.book).subscribe(data => {
        this.notifyService.showSuccess('Cartea a fost adaugata in lista ta de imprumuturi');
        this.book = data;
      }, (error) => {
        this.notifyService.showError('Operatiunea nu a putut fi efectuata');
      });
    }
  }
}
