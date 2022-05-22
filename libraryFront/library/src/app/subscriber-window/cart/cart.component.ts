import {Component, OnDestroy, OnInit} from '@angular/core';
import {BookModel} from "../../models/book.model";
import {Subscription, timer} from "rxjs";
import {SubscriberService} from "../../services/subscriber.service";
import {NotificationService} from "../../services/notification.service";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit, OnDestroy {

  books: BookModel[] | undefined;
  private updateSubscription: Subscription | undefined;

  constructor(private service: SubscriberService, private notify: NotificationService) { }

  ngOnInit(): void {
    this.getBooks();
    this.updateSubscription = timer(0,5000).subscribe(_val => {
      this.getBooks();
    });
  }

  getBooks() {
    this.service.getLoans().subscribe(data => {
      this.books = data;
      if(this.books.length === 0) {
        this.notify.showInfo();
      }
    },(error) => {
      this.notify.showError();
    })
  }

  ngOnDestroy(): void {
    this.updateSubscription?.unsubscribe();
  }

}
