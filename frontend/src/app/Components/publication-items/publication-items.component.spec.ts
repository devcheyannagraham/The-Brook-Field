import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PublicationItemsComponent } from './publication-items.component';

describe('PublicationItemsComponent', () => {
  let component: PublicationItemsComponent;
  let fixture: ComponentFixture<PublicationItemsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PublicationItemsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PublicationItemsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
