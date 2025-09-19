import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShopPublicationDetailComponent } from './shop-publication-detail.component';

describe('ShopItemDetailComponent', () => {
  let component: ShopPublicationDetailComponent;
  let fixture: ComponentFixture<ShopPublicationDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShopPublicationDetailComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShopPublicationDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
