import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {LoginCredentialsModel} from "../models/login-credentials.model";
import {UrlConstants} from "../url.constants";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient) {
  }

  login(credentials: LoginCredentialsModel): Observable<any> {
    return this.http.post(`${UrlConstants.BASE_URL}/${UrlConstants.AUTH_URL}`,
      credentials, { observe: 'response' });
  }
}
