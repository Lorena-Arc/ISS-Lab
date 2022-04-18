export interface BookModel {
  id: string;
  titlu: string;
  autor: string;
  statusCarte: BookStatus;
}

export enum BookStatus {
  DISPONIBIL = 'Disponibilă',
  INDISPONIBIL = 'Imprumutată'
}
