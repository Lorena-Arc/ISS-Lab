 import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {BookModel} from "../models/book.model";
import {UrlConstants} from "../url.constants";

@Injectable({
  providedIn: 'root'
})
export class LibrarianService {
  constructor(private http: HttpClient) {
  }

  getBooks(): Observable<BookModel[]> {
    return this.http.get<BookModel[]>(`${UrlConstants.BASE_URL}/${UrlConstants.LIBRARIAN_URL}`);
  }

  addBook(book: BookModel): Observable<BookModel> {
    let body = {
      id: book.id,
      titlu: book.titlu,
      autor: book.autor,
      statusCarte: book.statusCarte
    }
    return this.http.post<BookModel>(`${UrlConstants.BASE_URL}/${UrlConstants.LIBRARIAN_URL}`, body);
  }

  modifyBook(book: BookModel): Observable<BookModel> {
    return this.http.put<BookModel>(`${UrlConstants.BASE_URL}/${UrlConstants.LIBRARIAN_URL}/${book.id}`, book);
  }

  deleteBook(book: BookModel): Observable<any> {
    return this.http.delete(`${UrlConstants.BASE_URL}/${UrlConstants.LIBRARIAN_URL}/${book.id}`);
  }

  returnBook(bookId: string): Observable<BookModel> {
    return this.http.put<BookModel>(`${UrlConstants.BASE_URL}/${UrlConstants.LIBRARIAN_URL}/${UrlConstants.LIBRARIAN_LOAN_URL}/${bookId}`, null);
  }
}
