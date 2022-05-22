import { Injectable } from '@angular/core';
import {ToastrService} from "ngx-toastr";

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor(private toastr: ToastrService) { }

  showError(message='Ceva nu a funtionat. Te rugam sa verifici datele si sa reincerci'){
    this.toastr.error(message)
  }

  showSuccess(message= 'Operatiune efectuata cu succes') {
    this.toastr.success(message);
  }

  showInfo(message= 'Nu ai nicio carte imprumutata in prezent') {
    this.toastr.info(message);
  }
}
