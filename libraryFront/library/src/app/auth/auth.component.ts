import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";
import { JwtHelperService } from '@auth0/angular-jwt';
import {NotificationService} from "../services/notification.service";

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss']
})
export class AuthComponent implements OnInit {

  loginForm: any;

  constructor(private service: AuthService, private fb: FormBuilder, private router: Router, private notifyService: NotificationService) { }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      username: '',
      password: '',
      role: ''
    })
  }

  tryLogin() {
    this.service.login(this.loginForm.getRawValue()).subscribe((resp) => {
      this.notifyService.showSuccess('Operatiune efectuata cu succes!');
      let token = resp.headers.get('acces_token');
      console.log(resp.headers)
      localStorage.setItem("access_token", token);

      const helper = new JwtHelperService();

      const decoded = helper.decodeToken(token);

      let role = decoded.role.toString().toLowerCase();
      localStorage.setItem("role", role);
      this.redirect();

    }, (_err) => {
      this.notifyService.showError('Ceva nu a functionat. Te rugam sa incerci din nou!')
    })
  }

  redirect() {
    let role = localStorage.getItem("role");
    if(role === "bibliotecar") {
      this.router.navigateByUrl('librarian');
    }

    if(role === "abonat") {
      this.router.navigateByUrl('subscriber');
    }
  }
}
