import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShopAccessoryDetailComponent } from './shop-accessory-detail.component';

describe('ShopAccessoryDetailComponent', () => {
  let component: ShopAccessoryDetailComponent;
  let fixture: ComponentFixture<ShopAccessoryDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShopAccessoryDetailComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShopAccessoryDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
