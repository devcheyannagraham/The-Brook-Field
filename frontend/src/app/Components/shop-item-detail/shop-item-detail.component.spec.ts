import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShopItemDetailComponent } from './shop-item-detail.component';

describe('ShopItemDetailComponent', () => {
  let component: ShopItemDetailComponent;
  let fixture: ComponentFixture<ShopItemDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShopItemDetailComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShopItemDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
