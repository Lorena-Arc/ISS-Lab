import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LibrarianWindowComponent } from './librarian-window.component';

describe('LibrarianWindowComponent', () => {
  let component: LibrarianWindowComponent;
  let fixture: ComponentFixture<LibrarianWindowComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LibrarianWindowComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LibrarianWindowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
