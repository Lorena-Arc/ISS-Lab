import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {BookModel} from "../models/book.model";

@Injectable({
  providedIn: 'root'
})
export class LibrarianService {
  constructor(private http: HttpClient) {
  }

  getBooks(): Observable<BookModel[]> {
    return this.http.get<BookModel[]>("http://localhost:8080/api/librarian/books");
  }

  addBook(book: BookModel): Observable<BookModel> {
    console.log(book)
    let body = {
      id: book.id,
      titlu: book.titlu,
      autor: book.autor,
      statusCarte: book.statusCarte
    }
    return this.http.post<BookModel>("http://localhost:8080/api/librarian/books", body);
  }

  modifyBook(book: BookModel): Observable<BookModel> {
    return this.http.put<BookModel>(`http://localhost:8080/api/librarian/books/${book.id}`, book);
  }

  deleteBook(book: BookModel): Observable<any> {
    return this.http.delete(`http://localhost:8080/api/librarian/books/${book.id}`);
  }
}
