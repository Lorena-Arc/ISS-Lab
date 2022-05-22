import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookSubscriberComponent } from './book-subscriber.component';

describe('BookSubscriberComponent', () => {
  let component: BookSubscriberComponent;
  let fixture: ComponentFixture<BookSubscriberComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BookSubscriberComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BookSubscriberComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
