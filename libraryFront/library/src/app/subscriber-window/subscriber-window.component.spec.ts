import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubscriberWindowComponent } from './subscriber-window.component';

describe('SubscriberWindowComponent', () => {
  let component: SubscriberWindowComponent;
  let fixture: ComponentFixture<SubscriberWindowComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SubscriberWindowComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SubscriberWindowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
