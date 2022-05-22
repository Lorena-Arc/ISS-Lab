import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {BookModel} from "../models/book.model";
import { UrlConstants } from "../url.constants";
import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class SubscriberService {
  constructor(private http: HttpClient) {
  }

  getBooks(): Observable<BookModel[]> {
    let params = new HttpParams().set('availability', 'disponibil');
    return this.http.get<BookModel[]>(`${UrlConstants.BASE_URL}/${UrlConstants.GENERAL_URL}`, {params});
  }

  borrowBook(book: BookModel): Observable<BookModel> {
    return this.http.put<BookModel>(`${UrlConstants.BASE_URL}/${UrlConstants.SUBSCRIBER_URL}/${book.id}`, null);
  }

  getLoans(): Observable<BookModel[]> {
    return this.http.get<BookModel[]>(`${UrlConstants.BASE_URL}/${UrlConstants.SUBSCRIBER_URL}`);
  }

}
